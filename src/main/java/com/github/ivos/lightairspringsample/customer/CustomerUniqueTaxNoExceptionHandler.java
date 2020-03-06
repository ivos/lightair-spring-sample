package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.validation.DataIntegrityExceptionHandler;
import com.github.ivos.lightairspringsample.validation.ValidationError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUniqueTaxNoExceptionHandler implements DataIntegrityExceptionHandler {

	@Override
	public ValidationError handle(DataIntegrityViolationException exception) {
		if (exception.getMostSpecificCause().getMessage().contains("\"uc_customers_tax_no\"")) {
			return new ValidationError("taxNumber", null, "duplicate");
		}
		return null;
	}
}
