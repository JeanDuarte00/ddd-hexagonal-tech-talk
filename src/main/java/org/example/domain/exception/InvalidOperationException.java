package org.example.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidOperationException extends BusinessException {
	public InvalidOperationException (String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}