package com.github.ivos.lightairspringsample.customer.dto;

import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
public class CustomerDtoCreate {

	@NotNull
	@Size(min = 1, max = 100)
	public String name;

	@Size(min = 4, max = 100)
	public String taxNumber;

	@NotNull
	@Min(0)
	public Integer maturityInterval;

	@Size(min = 1, max = 30)
	public String mobile;

	@Size(min = 1, max = 50)
	public String email;

	@Size(min = 1, max = 50)
	public String web;
}
