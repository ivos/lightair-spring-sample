package com.github.ivos.lightairspringsample.order_number;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_numbers")
@Data
@EqualsAndHashCode(of = "year")
public class OrderNumber {

	@Id
	private Integer year;

	@Version
	private Long version;

	@NotNull
	private Integer lastSequence;
}
