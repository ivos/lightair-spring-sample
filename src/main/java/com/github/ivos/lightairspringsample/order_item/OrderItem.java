package com.github.ivos.lightairspringsample.order_item;

import com.github.ivos.lightairspringsample.order.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@EqualsAndHashCode(of = "id")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Order order;

	@NotNull
	@Size(min = 1, max = 100)
	private String product;

	@NotNull
	@Min(0)
	private BigDecimal unitPrice;

	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal quantity;

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", order=" + ((order == null) ? null : order.getId()) +
				", product='" + product + '\'' +
				", unitPrice=" + unitPrice +
				", quantity=" + quantity +
				'}';
	}
}
