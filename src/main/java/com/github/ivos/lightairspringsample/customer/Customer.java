package com.github.ivos.lightairspringsample.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(of = "id")
@ScriptAssert(lang = "javascript",
		script = "_this.mobile !== null || _this.email !== null",
		message = "customer mobile or email required")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@NotNull
	@Size(min = 1, max = 100)
	private String name;

	@Size(min = 4, max = 14)
	private String taxNumber;

	@NotNull
	@Min(0)
	private Integer maturityInterval;

	@Size(min = 1, max = 30)
	private String mobile;

	@Size(min = 1, max = 50)
	private String email;

	@Size(min = 1, max = 50)
	private String web;

	@NotNull
	private LocalDateTime updated;
}
