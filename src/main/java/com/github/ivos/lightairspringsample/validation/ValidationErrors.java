package com.github.ivos.lightairspringsample.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationErrors {

	private final List<ValidationError> validationErrors = new ArrayList<>();

	public boolean hasErrors() {
		return !validationErrors.isEmpty();
	}

	/**
	 * Verify that no errors have been collected during previous validations.
	 * <p>
	 * If there are any errors, throw {@link ValidationException}.
	 */
	public void verify() {
		if (!validationErrors.isEmpty()) {
			Collections.sort(validationErrors);
			throw new ValidationException(validationErrors);
		}
	}

	public void add(ValidationError validationError) {
		validationErrors.add(validationError);
	}

	@Override
	public String toString() {
		return validationErrors.stream()
				.map(Object::toString)
				.collect(Collectors.joining(","));
	}
}
