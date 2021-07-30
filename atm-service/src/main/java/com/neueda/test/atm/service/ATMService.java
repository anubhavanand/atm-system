package com.neueda.test.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.neueda.test.atm.VO.AccountBalance;
import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.dispenser.CurrencyDispenser;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.service.entity.repository.ATMRepository;

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

	public TransactionDetails withdrawAmount(final long accountId, final int pin, final int amount) {
		final ATMCashDetails atmDetails = atmRepository.getById(1L);
		final TransactionDetails transactionDetails = new TransactionDetails();
		final Integer amountLeft = currencyDispenser.dispense(atmDetails, transactionDetails, amount);
		if (amountLeft > 0) {
			throw new RuntimeException("Insufficient cash in ATM.");
		}
		transactionDetails.setAccountBalance(updateAccountBalance(accountId, pin, amount));
		updateATMRepo(atmDetails);
		return transactionDetails;
	}

	public AccountBalance getAccountBalance(long accountId, int pin) {
		return restTemplate.getForObject("http://localhost:9001/accounts/id/" + accountId + "/pin/" + pin,
				AccountBalance.class);
	}

	private AccountBalance updateAccountBalance(final long accountId, final int pin, final Integer amountWithdrawn) {
		return restTemplate.getForObject(
				"http://localhost:9001/accounts/id/" + accountId + "/pin/" + pin + "/amount/" + amountWithdrawn,
				AccountBalance.class);
	}

	private void updateATMRepo(final ATMCashDetails atmDetails) {
		atmRepository.save(atmDetails);
	}

}
