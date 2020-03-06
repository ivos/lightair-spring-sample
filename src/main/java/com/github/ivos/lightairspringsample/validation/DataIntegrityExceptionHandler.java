package com.github.ivos.lightairspringsample.validation;

import org.springframework.dao.DataIntegrityViolationException;

public interface DataIntegrityExceptionHandler {

	ValidationError handle(DataIntegrityViolationException exception);
}
