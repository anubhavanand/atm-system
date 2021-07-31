package com.neueda.test.account.controller.exceptionHandler;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class AccountServiceApiError {

    private final HttpStatus status;
    private final String message;

    public AccountServiceApiError(final HttpStatus status, final String message) {
        super();
        this.status = status;
        this.message = message;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
