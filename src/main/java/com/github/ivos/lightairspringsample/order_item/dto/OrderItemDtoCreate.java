package com.github.ivos.lightairspringsample.order_item.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class OrderItemDtoCreate {

	@NotNull
	@Size(min = 1, max = 100)
	private String product;

	@NotNull
	@Min(0)
	private BigDecimal unitPrice;

	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal quantity;
}
