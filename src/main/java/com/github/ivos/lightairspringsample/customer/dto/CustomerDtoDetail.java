package com.github.ivos.lightairspringsample.customer.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CustomerDtoDetail {

	private String name;
	private String taxNumber;
	private Integer maturityInterval;
	private String mobile;
	private String email;
	private String web;
	private ZonedDateTime updated;
}
