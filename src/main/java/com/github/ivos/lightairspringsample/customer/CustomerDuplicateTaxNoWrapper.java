package com.github.ivos.lightairspringsample.customer;

import com.github.ivos.lightairspringsample.validation.Validation;
import com.github.ivos.lightairspringsample.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Wrap call to repo, translating exception from duplicate taxNo into a validation error.
 * <p></p>
 * This prevents calling the DB to check if a taxNo exists before trying to insert it.
 * (There might be a race condition anyway, when the taxNo does not exist on select,
 * but then exists on insert, so we have to process the exception nicely anyway.)
 */
@Component
public class CustomerDuplicateTaxNoWrapper {

	@Autowired
	private Validation validation;

	public <T> T wrap(Customer customer, Supplier<T> lambda) {
		try {
			return lambda.get();
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage() != null &&
					e.getMessage().contains("constraint [uc_customers_tax_no]")) {
				validation.reject(new ValidationError("taxNumber", customer.getTaxNumber(), "duplicate"));
			}
			throw e;
		}
	}
}
