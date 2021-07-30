package com.neueda.test.atm.validation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.validation.Validator;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Service
public class WithdrawalRequestValidationService implements ValidationService<WithdrawalRequest>, ValidationRegistrationService<WithdrawalRequest> {
	
	private List<Validator<WithdrawalRequest>> withdrawalRequestValidations = new ArrayList<>();
	
	@Override
	public void register(final Validator<WithdrawalRequest> validator) {
		withdrawalRequestValidations.add(validator);
	}

	@Override
	public boolean validate(final WithdrawalRequest request) {
		for(Validator<WithdrawalRequest> validator : withdrawalRequestValidations) {
			boolean valid = validator.isValid(request);
			if(!valid) {
				return false;
			}
		}
		return true;
	}

}
