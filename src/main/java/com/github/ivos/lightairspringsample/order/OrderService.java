package com.github.ivos.lightairspringsample.order;

import com.github.ivos.lightairspringsample.customer.Customer;
import com.github.ivos.lightairspringsample.customer.CustomerRepository;
import com.github.ivos.lightairspringsample.order.dto.OrderDtoCreate;
import com.github.ivos.lightairspringsample.order_number.OrderNumberService;
import com.github.ivos.lightairspringsample.time.TimeService;
import com.github.ivos.lightairspringsample.validation.Validation;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {

	private final OrderRepository repo;
	private final MapperFacade mapper;
	private final Validation validation;
	private final OrderNumberService orderNumberService;
	private final CustomerRepository customerRepository;

	public OrderService(OrderRepository repo, MapperFacade mapper, Validation validation,
			OrderNumberService orderNumberService, CustomerRepository customerRepository) {
		this.repo = repo;
		this.mapper = mapper;
		this.validation = validation;
		this.orderNumberService = orderNumberService;
		this.customerRepository = customerRepository;
	}

	@Transactional
	public Order create(OrderDtoCreate dto) {
		LocalDateTime now = TimeService.now();

		validation.verifyBean(dto);
		Order order = mapper.map(dto, Order.class);
		Customer customer = customerRepository.getOne(order.getCustomer().getId());
		order.setCustomer(customer);

		order.setStatus(OrderStatus.created);
		order.setCreated(now);
		order.setUpdated(now);

		int year = now.getYear();
		Integer sequence = orderNumberService.getNextSequence(year);
		String paddedSequence = StringUtils.leftPad(String.valueOf(sequence), 5, '0');
		String orderNumber = "PO-" + year + "-" + paddedSequence;
		order.setOrderNumber(orderNumber);

		order.getOrderItems().forEach(item -> item.setOrder(order));

		return repo.saveAndFlush(order);
	}
}
