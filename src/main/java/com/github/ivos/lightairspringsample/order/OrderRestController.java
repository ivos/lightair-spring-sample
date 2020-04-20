package com.github.ivos.lightairspringsample.order;

import com.github.ivos.lightairspringsample.order.dto.OrderDtoCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ivos.lightairspringsample.utils.RestUtils.location;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderRestController {

	private final OrderService orderService;

	public OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(
			@RequestBody OrderDtoCreate dto) {
		log.info("Create order, {}", dto);
		Order order = orderService.create(dto);
		return ResponseEntity
				.created(location(order.getId()))
				.build();
	}
}
