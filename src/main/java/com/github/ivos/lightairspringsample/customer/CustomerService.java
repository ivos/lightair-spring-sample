package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoCreate;
import com.github.ivos.lightairspringsample.validation.Validation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
}
