package com.github.ivos.lightairspringsample.order.dto;

import com.github.ivos.lightairspringsample.order_item.dto.OrderItemDtoCreate;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDtoCreate {

	@NotNull
	private Long customerId;

	@NotNull
	@FutureOrPresent
	private LocalDate dueDate;

	@Size(min = 1)
	private String comment;

	@NotNull
	@Valid
	private List<OrderItemDtoCreate> orderItems;
}
