package com.neueda.test.atm.controller.errorHandler;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class ATMServiceApiError {

    private final HttpStatus status;
    private final String message;

    public ATMServiceApiError(final HttpStatus status, final String message) {
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
