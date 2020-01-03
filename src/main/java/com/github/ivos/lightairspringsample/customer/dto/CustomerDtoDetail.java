package com.github.ivos.lightairspringsample.customer.dto;

import lombok.ToString;

import java.time.ZonedDateTime;

@ToString
public class CustomerDtoDetail {

	public String name;
	public String taxNumber;
	public Integer maturityInterval;
	public String mobile;
	public String email;
	public String web;
	public ZonedDateTime updated;
}
