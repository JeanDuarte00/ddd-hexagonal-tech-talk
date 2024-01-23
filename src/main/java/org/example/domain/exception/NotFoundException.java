package org.example.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends BusinessException {
	public NotFoundException (String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}