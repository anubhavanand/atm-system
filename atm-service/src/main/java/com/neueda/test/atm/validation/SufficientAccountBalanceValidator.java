package com.neueda.test.atm.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.neueda.test.atm.VO.AccountBalance;
import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.model.WithdrawalRequest;

public class SufficientAccountBalanceValidator implements Validator<WithdrawalRequest> {

	private final RestTemplate restTemplate;

	public SufficientAccountBalanceValidator(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public boolean isValid(final WithdrawalRequest request) {
		final AccountBalance accountBalance = restTemplate.getForObject(
				"http://localhost:9001/accounts/id/" + request.getAccountId() + "/pin/" + request.getPin(),
				AccountBalance.class);
		if(accountBalance.getMaxWithdrawalAmount() < request.getAmount()) {
			throw new ValidationFailedException(HttpStatus.NOT_ACCEPTABLE, "Account Balance Insufficient.");
		}
		return true;
	}

}
