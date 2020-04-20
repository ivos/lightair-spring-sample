package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.config.Logged;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoUpdate;
import com.github.ivos.lightairspringsample.time.TimeService;
import com.github.ivos.lightairspringsample.validation.Validation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.ivos.lightairspringsample.utils.Constants.LIST_ROW_COUNT;

@Service
@Logged
public class CustomerService {

	private final CustomerRepository repo;
	private final MapperFacade mapper;
	private final Validation validation;
	private final EntityManager entityManager;

	public CustomerService(CustomerRepository repo, MapperFacade mapper, Validation validation,
			EntityManager entityManager) {
		this.repo = repo;
		this.mapper = mapper;
		this.validation = validation;
		this.entityManager = entityManager;
	}

	@Transactional
	public Customer create(CustomerDtoUpdate dto) {
		LocalDateTime now = TimeService.now();

		validation.verifyBean(dto);
		Customer customer = mapper.map(dto, Customer.class);

		customer.setUpdated(now);

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
		LocalDateTime now = TimeService.now();

		validation.verifyBean(dto);
		Customer customer = get(id);
		mapper.map(dto, customer);

		customer.setUpdated(now);

		entityManager.detach(customer);
		customer.setVersion(version);
		entityManager.merge(customer);
		repo.flush();
	}
}
