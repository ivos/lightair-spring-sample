package com.github.ivos.lightairspringsample.customer.dto;

import lombok.Data;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ScriptAssert(lang = "javascript",
		script = "_this.mobile !== null || _this.email !== null",
		message = "customer mobile or email required")
public class CustomerDtoUpdate {

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
}
