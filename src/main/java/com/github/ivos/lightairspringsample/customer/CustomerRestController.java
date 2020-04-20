package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.config.Logged;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoDetail;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoList;
import com.github.ivos.lightairspringsample.customer.dto.CustomerDtoUpdate;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.ivos.lightairspringsample.utils.RestUtils.eTag;
import static com.github.ivos.lightairspringsample.utils.RestUtils.location;
import static com.github.ivos.lightairspringsample.utils.RestUtils.version;

@RestController
@RequestMapping("/api/customers")
@Logged(Logged.LogLevel.info)
public class CustomerRestController {

	private final CustomerService customerService;
	private final MapperFacade mapper;

	public CustomerRestController(CustomerService customerService, MapperFacade mapper) {
		this.customerService = customerService;
		this.mapper = mapper;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(
			@RequestBody CustomerDtoUpdate dto) {
		Customer customer = customerService.create(dto);
		return ResponseEntity
				.created(location(customer.getId()))
				.build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CustomerDtoList> list(
			@RequestParam(name = "search", required = false) String search) {
		List<Customer> customers = customerService.list(search);
		return mapper.mapAsList(customers, CustomerDtoList.class);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerDtoDetail> get(
			@PathVariable(name = "id") Long id) {
		Customer customer = customerService.get(id);
		CustomerDtoDetail dto = mapper.map(customer, CustomerDtoDetail.class);
		return ResponseEntity
				.ok()
				.headers(eTag(customer.getVersion()))
				.body(dto);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(
			@PathVariable(name = "id") Long id,
			@RequestHeader(name = HttpHeaders.IF_MATCH, required = false) String ifMatch,
			@RequestBody CustomerDtoUpdate dto) {
		customerService.update(id, version(ifMatch), dto);
	}
}
