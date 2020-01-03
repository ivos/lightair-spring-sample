package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoCreate;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.ivos.lightairspringsample.utils.RestUtils.location;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

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
		return customerService.list(search);
	}
}
