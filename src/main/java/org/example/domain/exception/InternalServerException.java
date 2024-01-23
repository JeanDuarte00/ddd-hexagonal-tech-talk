package org.example.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerException extends BusinessException {
	public InternalServerException (String message) {
		super(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}