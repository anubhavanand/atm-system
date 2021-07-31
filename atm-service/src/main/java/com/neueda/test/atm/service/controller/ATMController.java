package com.neueda.test.atm.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.test.atm.VO.AccountBalance;
import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.service.ATMService;
import com.neueda.test.atm.validation.service.ValidationService;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@RestController
@RequestMapping("/atm")
public class ATMController {

	private final ATMService atmService;
	
	private final ValidationService<WithdrawalRequest> validatorService;

	@Autowired
	public ATMController(final ATMService atmService, final ValidationService<WithdrawalRequest> validatorService) {
		this.atmService = atmService;
		this.validatorService = validatorService;
	}

	@PostMapping("/")
	public ResponseEntity<ATMCashDetails> initializeAmountinATM(@RequestBody final ATMCashDetails atmCashDetails) {
		return new ResponseEntity<ATMCashDetails>(atmService.initializeAmountinATM(atmCashDetails), HttpStatus.OK);
	}

	@GetMapping("/id/{id}/pin/{pin}/amount/{amount}")
	public ResponseEntity<TransactionDetails> withdrawAmount(@PathVariable("id") final long accountId,
			@PathVariable("pin") final int pin, @PathVariable("amount") final int amount) {
		validatorService.validate(new WithdrawalRequest(accountId, pin, amount));
		return new ResponseEntity<TransactionDetails>(atmService.withdrawAmount(accountId, pin, amount), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}/pin/{pin}")
	public ResponseEntity<AccountBalance> getAccountBalance(@PathVariable("id") final long accountId,
			@PathVariable("pin") final int pin) {
		return new ResponseEntity<AccountBalance>(atmService.getAccountBalance(accountId, pin), HttpStatus.OK);
	}

}
