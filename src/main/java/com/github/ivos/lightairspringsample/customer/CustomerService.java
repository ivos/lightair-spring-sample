package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoUpdate;
import com.github.ivos.lightairspringsample.validation.Validation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
	private EntityManager entityManager;

	@Transactional
	public Customer create(CustomerDtoUpdate dto) {
		validation.verifyBean(dto);
		Customer customer = mapper.map(dto, Customer.class);

		customer.setUpdated(LocalDateTime.now());

		return repo.saveAndFlush(customer);
	}

	@Transactional(readOnly = true)
	public List<Customer> list(String search) {
		return repo.list(search, PageRequest.of(0, LIST_ROW_COUNT));
	}

	@Transactional(readOnly = true)
	public Customer get(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Customer id " + id + " not found"));
	}

	@Transactional
	public void update(Long id, Long version, CustomerDtoUpdate dto) {
		validation.verifyBean(dto);
		Customer customer = get(id);
		mapper.map(dto, customer);

		customer.setUpdated(LocalDateTime.now());

		entityManager.detach(customer);
		customer.setVersion(version);
		entityManager.merge(customer);
		repo.flush();
	}
}
