package com.neueda.test.atm.validation;

import org.springframework.http.HttpStatus;

import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.utils.CurrencyValue;
import com.neueda.test.atm.utils.Message;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Slf4j
public class InvalidWithdrwalAmountValidator implements Validator<WithdrawalRequest> {

	@Override
	public boolean isValid(final WithdrawalRequest withdrawalRequest) {
		if (withdrawalRequest.getAmount() % CurrencyValue.FIVE.value() != 0) {
			log.info("Validation failed: %s, WithdrawalRequest: %s",
					InvalidWithdrwalAmountValidator.class.getSimpleName(), withdrawalRequest);
			throw new ValidationFailedException(HttpStatus.BAD_REQUEST,
					Message.INVALID_AMOUNT.message() + CurrencyValue.FIVE.value());
		}

		return true;
	}
}
