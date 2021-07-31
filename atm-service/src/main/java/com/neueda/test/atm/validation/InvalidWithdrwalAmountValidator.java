package com.neueda.test.atm.validation;

import org.springframework.http.HttpStatus;

import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.utils.CurrencyValue;
import com.neueda.test.atm.utils.Message;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class InvalidWithdrwalAmountValidator implements Validator<WithdrawalRequest> {

	@Override
	public boolean isValid(final WithdrawalRequest entity) {
		if (entity.getAmount() % CurrencyValue.FIVE.value() != 0) {
			throw new ValidationFailedException(HttpStatus.BAD_REQUEST,
					Message.INVALID_AMOUNT.message() + CurrencyValue.FIVE.value());
		}

		return true;
	}
}
