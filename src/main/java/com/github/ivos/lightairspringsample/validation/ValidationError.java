package com.github.ivos.lightairspringsample.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ValidationError implements Comparable<ValidationError> {

	private String path;
	private Object value;
	private String errorCode;

	@Override
	public int compareTo(ValidationError o) {
		int byPath = path.compareTo(o.path);
		int byErrorCode = errorCode.compareTo(o.errorCode);
		return (0 == byPath) ? byErrorCode : byPath;
	}
}
