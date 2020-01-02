package com.github.ivos.lightairspringsample.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class Validation {

	@Autowired
	private Validator validator;

	/**
	 * Perform bean validations (JSR-303).
	 *
	 * @param validationErrors validation errors
	 * @param data             bean to be validated, annotated with JSR-303 annotations
	 */
	public void validateBean(ValidationErrors validationErrors, Object data) {
		processViolations(validationErrors, validator.validate(data));
	}

	/**
	 * Perform bean validations (JSR-303).
	 * <p>
	 * Calls verify method to stop processing.
	 *
	 * @param data bean to be validated, annotated with JSR-303 annotations
	 */
	public void verifyBean(Object data) {
		ValidationErrors validationErrors = new ValidationErrors();
		validateBean(validationErrors, data);
		verify(validationErrors);
	}

	/**
	 * Rejects further processing due to a validation error.
	 * <p>
	 * Calls verify method to stop processing.
	 *
	 * @param validationError validation error
	 */
	public void reject(ValidationError validationError) {
		ValidationErrors validationErrors = new ValidationErrors();
		validationErrors.add(validationError);
		verify(validationErrors);
	}

	/**
	 * Verify that no errors have been collected during previous validations.
	 * <p>
	 * If there are any errors, throw {@link ValidationException}.
	 *
	 * @param validationErrors errors holder
	 */
	public void verify(ValidationErrors validationErrors) {
		validationErrors.verify();
	}

	private void processViolations(ValidationErrors validationErrors, Set<ConstraintViolation<Object>> violations) {
		violations.stream()
				.map(violation -> new ValidationError(
						violation.getPropertyPath().toString(),
						violation.getInvalidValue(),
						violation.getMessage()))
				.forEach(validationErrors::add);
	}
}
