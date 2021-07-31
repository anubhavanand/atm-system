package com.neueda.test.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.neueda.test.account.VO.AccountBalance;
import com.neueda.test.account.controller.exceptionHandler.ValidationFailedException;
import com.neueda.test.account.entity.AccountDetails;
import com.neueda.test.account.repository.AccountRepository;
import com.neueda.test.account.util.Message;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public AccountDetails addAccountDetails(final AccountDetails accountDetails) {
		return accountRepository.save(accountDetails);
	}

	public AccountBalance getAccountBalance(final Long accountId, final int pin) {
		final AccountDetails accountDetails = accountRepository.getById(accountId);
		if (pin != accountDetails.getPin()) {
			throw new ValidationFailedException(HttpStatus.UNAUTHORIZED, Message.INCORRECT_PIN.message());
		}
		return getAccountBalance(accountDetails);
	}

	public AccountBalance debitFromAccount(final Long accountId, final int pin, final int amountWithdrawn) {
		final AccountDetails accountDetails = accountRepository.getById(accountId);
		if (pin != accountDetails.getPin()) {
			throw new ValidationFailedException(HttpStatus.UNAUTHORIZED, Message.INCORRECT_PIN.message());
		}
		if (accountDetails.getOpeningBalance() >= amountWithdrawn) {
			accountDetails.setOpeningBalance(accountDetails.getOpeningBalance() - amountWithdrawn);
		} else {
			accountDetails.setOverDraft(
					accountDetails.getOverDraft() - (amountWithdrawn - accountDetails.getOpeningBalance()));
			accountDetails.setOpeningBalance(0);
		}
		accountRepository.save(accountDetails);
		return getAccountBalance(accountDetails);
	}

	private AccountBalance getAccountBalance(final AccountDetails accountDetails) {
		final double maxWithdrwalAmount = accountDetails.getOpeningBalance() + accountDetails.getOverDraft();
		return new AccountBalance(accountDetails.getOpeningBalance(), accountDetails.getOverDraft(),
				maxWithdrwalAmount > 0 ? maxWithdrwalAmount : 0.0);
	}

}
