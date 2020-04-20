package com.github.ivos.lightairspringsample.order;

import com.github.ivos.lightairspringsample.customer.Customer;
import com.github.ivos.lightairspringsample.order_item.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = "orderItems")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@NotNull
	@Size(min = 1, max = 30)
	private String orderNumber;

	@ManyToOne
	@NotNull
	private Customer customer;

	@NotNull
	private LocalDate dueDate;

	@Enumerated(EnumType.STRING)
	@NotNull
	private OrderStatus status;

	@Column(name = "comment_")
	@Size(min = 1)
	private String comment;

	@NotNull
	private LocalDateTime created;

	@NotNull
	private LocalDateTime updated;

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	@NotNull
	private List<OrderItem> orderItems;
}
