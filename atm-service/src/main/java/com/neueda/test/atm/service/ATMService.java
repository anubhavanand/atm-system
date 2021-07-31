package com.neueda.test.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.neueda.test.atm.VO.AccountBalance;
import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.dispenser.CurrencyDispenser;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.service.entity.repository.ATMRepository;
import com.neueda.test.atm.utils.Message;
import com.neueda.test.atm.utils.UrlConstants;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Service
public class ATMService {

	private final ATMRepository atmRepository;

	private final RestTemplate restTemplate;

	private final CurrencyDispenser currencyDispenser;

	@Autowired
	public ATMService(final ATMRepository atmRepository, final RestTemplate restTemplate,
			final CurrencyDispenser currencyDispenser) {
		this.atmRepository = atmRepository;
		this.restTemplate = restTemplate;
		this.currencyDispenser = currencyDispenser;
	}

	public ATMCashDetails initializeAmountinATM(ATMCashDetails atmDetails) {
		return atmRepository.save(atmDetails);
	}

	public TransactionDetails withdrawAmount(final WithdrawalRequest withdrawalRequest) {
		final ATMCashDetails atmDetails = atmRepository.getById(1L);
		final TransactionDetails transactionDetails = new TransactionDetails();
		final Integer amountLeft = currencyDispenser.dispense(atmDetails, transactionDetails, withdrawalRequest.getAmount());
		if (amountLeft > 0) {
			throw new ValidationFailedException(HttpStatus.UNPROCESSABLE_ENTITY,
					Message.INSUFFICIENT_ATM_CASH.message());
		}
		transactionDetails.setAccountBalance(debitAccountBalance(withdrawalRequest));
		updateATMRepo(atmDetails);
		return transactionDetails;
	}

	public AccountBalance getAccountBalance(long accountId, int pin) {
		return restTemplate.getForObject(
				UrlConstants.ACCOUNT_SERVICE.value() + UrlConstants.CHECK_BALANCE.value() + accountId + UrlConstants.PIN.value() + pin,
				AccountBalance.class);
	}

	private AccountBalance debitAccountBalance(final WithdrawalRequest withdrawalRequest) {
		return restTemplate.postForObject(UrlConstants.ACCOUNT_SERVICE.value() + UrlConstants.DEBIT.value(), withdrawalRequest, AccountBalance.class);
	}

	private void updateATMRepo(final ATMCashDetails atmDetails) {
		atmRepository.save(atmDetails);
	}

}
