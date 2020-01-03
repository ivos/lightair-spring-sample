package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoCreate;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoDetail;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoList;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.ivos.lightairspringsample.utils.RestUtils.eTag;
import static com.github.ivos.lightairspringsample.utils.RestUtils.location;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MapperFacade mapper;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody CustomerDtoCreate dto) {
		log.info("Create customer, {}", dto);
		Customer customer = customerService.create(dto);
		return ResponseEntity
				.created(location(customer.getId()))
				.build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CustomerDtoList> list(
			@RequestParam(name = "search", required = false) String search) {
		log.info("List customers, search: {}", search);
		List<Customer> customers = customerService.list(search);
		return mapper.mapAsList(customers, CustomerDtoList.class);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerDtoDetail> get(
			@PathVariable(name = "id") Long id) {
		log.info("Get customer, id: {}", id);
		Customer customer = customerService.get(id);
		CustomerDtoDetail dto = mapper.map(customer, CustomerDtoDetail.class);
		return ResponseEntity
				.ok()
				.headers(eTag(customer.getVersion()))
				.body(dto);
	}
}
