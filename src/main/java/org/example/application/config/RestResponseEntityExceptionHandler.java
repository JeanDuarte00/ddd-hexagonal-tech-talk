package org.example.application.config;

import org.example.domain.exception.BusinessException;
import org.example.domain.exception.InternalServerException;
import org.example.domain.exception.InvalidOperationException;
import org.example.domain.exception.NoOperationLeftException;
import org.example.domain.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { InternalServerException.class, NoOperationLeftException.class, InvalidOperationException.class, NotFoundException.class })
	protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getStatus(), request);
	}

}