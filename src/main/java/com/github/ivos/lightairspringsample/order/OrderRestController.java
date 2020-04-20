package com.github.ivos.lightairspringsample.order;

import com.github.ivos.lightairspringsample.config.Logged;
import com.github.ivos.lightairspringsample.order.dto.OrderDtoCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ivos.lightairspringsample.utils.RestUtils.location;

@RestController
@RequestMapping("/api/orders")
@Logged(Logged.LogLevel.info)
public class OrderRestController {

	private final OrderService orderService;

	public OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(
			@RequestBody OrderDtoCreate dto) {
		Order order = orderService.create(dto);
		return ResponseEntity
				.created(location(order.getId()))
				.build();
	}
}
