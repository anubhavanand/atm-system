package com.neueda.test.account.controller.errorHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl {

	@ExceptionHandler(ValidationFailedException.class)
	public ResponseEntity<?> handleException(final ValidationFailedException exception) {
		final AccountServiceApiError apiError = new AccountServiceApiError(exception.getStatus(), exception.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(final Exception exception) {
		final AccountServiceApiError apiError = new AccountServiceApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
