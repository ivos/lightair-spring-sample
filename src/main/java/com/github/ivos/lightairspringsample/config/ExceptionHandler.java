package com.github.ivos.lightairspringsample.config;

import com.github.ivos.lightairspringsample.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice("com.github.ivos.lightairspringsample")
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ValidationException.class})
	@ResponseBody
	ResponseEntity<ErrorResponse> handle(HttpServletRequest request, ValidationException exception) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ErrorResponse errorResponse = createErrorResponse(request, status);
		errorResponse.setErrors(exception.getValidationErrors());
		return new ResponseEntity<>(errorResponse, status);
	}

	private ErrorResponse createErrorResponse(HttpServletRequest request, HttpStatus status) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(new Date());
		errorResponse.setStatus(status.value());
		errorResponse.setError(status.getReasonPhrase());
		errorResponse.setPath(request.getRequestURI());
		return errorResponse;
	}
}
