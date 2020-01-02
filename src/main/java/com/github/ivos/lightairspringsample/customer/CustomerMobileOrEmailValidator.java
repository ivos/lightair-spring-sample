package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.validation.Validation;
import com.github.ivos.lightairspringsample.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMobileOrEmailValidator {

	@Autowired
	private Validation validation;

	public void validate(Customer customer) {
		if (customer.getMobile() == null && customer.getEmail() == null) {
			validation.reject(new ValidationError(null, null, "either mobile or email is required"));
		}
	}
}
