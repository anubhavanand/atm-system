package com.neueda.test.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.test.account.entity.AccountDetails;
import com.neueda.test.account.service.AccountService;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<Object> addAccountDetails(@RequestBody final AccountDetails accountDetails) {
		return new ResponseEntity<Object>(accountService.addAccountDetails(accountDetails), HttpStatus.OK);
	}

	@GetMapping("/id/{id}/pin/{pin}")
	public ResponseEntity<Object> getAccountBalance(@PathVariable("id") final Long accountId,
			@PathVariable("pin") final int pin) {
		return accountService.getAccountBalance(accountId, pin);
	}

	@GetMapping("/id/{id}/pin/{pin}/amount/{amount}")
	public ResponseEntity<Object> debitFromAccount(@PathVariable("id") final Long accountId,
			@PathVariable("pin") final int pin, @PathVariable("amount") final int amount) {
		return accountService.debitFromAccount(accountId, pin, amount);
	}

}
