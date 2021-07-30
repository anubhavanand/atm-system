package com.neueda.test.atm.validation;

import org.springframework.http.HttpStatus;

import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.model.WithdrawalRequest;

public class InvalidWithdrwalAmountValidator implements Validator<WithdrawalRequest> {

	@Override
	public boolean isValid(final WithdrawalRequest entity) {
		//ToDO
		if (entity.getAmount() % 5 != 0) {
			throw new ValidationFailedException(HttpStatus.BAD_REQUEST, "Invalid amount! Amount should be in multiple of 5");
		} 
		
		return true;
	}
}
