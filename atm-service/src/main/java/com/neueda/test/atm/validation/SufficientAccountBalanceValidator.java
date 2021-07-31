package com.neueda.test.atm.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.neueda.test.atm.VO.AccountBalance;
import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.utils.Message;
import com.neueda.test.atm.utils.UrlConstants;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class SufficientAccountBalanceValidator implements Validator<WithdrawalRequest> {

	private final RestTemplate restTemplate;

	public SufficientAccountBalanceValidator(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public boolean isValid(final WithdrawalRequest request) {
		final AccountBalance accountBalance = restTemplate.getForObject(UrlConstants.ACCOUNT_SERVICE.value()
				+ request.getAccountId() + UrlConstants.PIN.value() + request.getPin(), AccountBalance.class);
		if (accountBalance.getMaxWithdrawalAmount() < request.getAmount()) {
			throw new ValidationFailedException(HttpStatus.NOT_ACCEPTABLE,
					Message.INSUFFICIENT_ACCOUNT_BALANCE.message());
		}
		return true;
	}

}
