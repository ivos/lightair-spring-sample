package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoCreate;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoList;
import com.github.ivos.lightairspringsample.validation.Validation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.ivos.lightairspringsample.utils.Constants.LIST_ROW_COUNT;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private MapperFacade mapper;

	@Autowired
	private Validation validation;

	@Autowired
	private CustomerMobileOrEmailValidator customerMobileOrEmailValidator;

	@Autowired
	private CustomerDuplicateTaxNoWrapper customerDuplicateTaxNoWrapper;

	@Transactional
	public Customer create(CustomerDtoCreate dto) {
		validation.verifyBean(dto);
		Customer customer = mapper.map(dto, Customer.class);
		customerMobileOrEmailValidator.validate(customer);
		customer.setUpdated(LocalDateTime.now());
		return customerDuplicateTaxNoWrapper.wrap(
				customer,
				() -> repo.save(customer)
		);
	}

	@Transactional(readOnly = true)
	public List<CustomerDtoList> list(String search) {
		List<Customer> customers = repo.list(search, PageRequest.of(0, LIST_ROW_COUNT));
		return mapper.mapAsList(customers, CustomerDtoList.class);
	}
}
