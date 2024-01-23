package org.example.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoOperationLeftException extends BusinessException {
	public NoOperationLeftException (String message) {
		super(message, HttpStatus.ALREADY_REPORTED);
	}
}
