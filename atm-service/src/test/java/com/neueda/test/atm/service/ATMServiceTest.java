package com.neueda.test.atm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.neueda.test.atm.VO.AccountBalance;
import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.dispenser.CurrencyDispenser;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.service.entity.repository.ATMRepository;
import com.neueda.test.atm.utils.Message;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class ATMServiceTest {

	private ATMRepository atmRepository;

	private RestTemplate restTemplate;

	private CurrencyDispenser currencyDispenser;

	private ATMService atmService;

	@BeforeEach
	public void setup() {
		atmRepository = Mockito.mock(ATMRepository.class);
		restTemplate = Mockito.mock(RestTemplate.class);
		currencyDispenser = Mockito.mock(CurrencyDispenser.class);
		atmService = new ATMService(atmRepository, restTemplate, currencyDispenser);
	}

	@Test
	void testInitializeAmountinATM() {
		final ATMCashDetails atmCashDetails = new ATMCashDetails(10, 5, 10, 5);
		when(atmRepository.save(ArgumentMatchers.any())).thenReturn(atmCashDetails);
		final ATMCashDetails savedATMCashDetails = atmService.initializeAmountinATM(atmCashDetails);
		assertEquals(atmCashDetails, savedATMCashDetails);
	}

	@Test
	public void testGetAccountBalanceWhenPinIncorrect() {
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any()))
				.thenThrow(new ValidationFailedException(HttpStatus.UNAUTHORIZED, "Incorrect pin"));
		final ValidationFailedException exception = assertThrows(ValidationFailedException.class, () -> {
			atmService.getAccountBalance(987654321, 123);
		});
		assertEquals(exception.getStatus(), HttpStatus.UNAUTHORIZED);
		assertEquals(exception.getMessage(), "Incorrect pin");
	}

	@Test
	public void testGetAccountBalance() {
		final AccountBalance accountBalance = new AccountBalance(100.0, 10.0, 110.0);
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(accountBalance);

		final AccountBalance receivedAccountBalance = atmService.getAccountBalance(987654321, 4321);
		assertEquals(accountBalance, receivedAccountBalance);
	}

	@Test
	public void testWithdrawAmountWhenNotAllAmountCanBeDispensed() {
		final ATMCashDetails atmCashDetails = new ATMCashDetails(10, 5, 10, 5);
		when(atmRepository.getById(ArgumentMatchers.any())).thenReturn(atmCashDetails);
		when(currencyDispenser.dispense(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(2);

		final WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
		final ValidationFailedException exception = assertThrows(ValidationFailedException.class, () -> {
			atmService.withdrawAmount(withdrawalRequest);
		});
		assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(exception.getMessage(), Message.INSUFFICIENT_ATM_CASH.message());
	}

	@Test
	public void testWithdrawAmountWhenAllAmountCanBeDispensed() {
		final ATMCashDetails atmCashDetails = new ATMCashDetails(10, 5, 10, 5);
		when(atmRepository.getById(ArgumentMatchers.any())).thenReturn(atmCashDetails);
		when(currencyDispenser.dispense(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(0);

		final AccountBalance accountBalance = new AccountBalance(100.0, 10.0, 110.0);
		when(restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(accountBalance);

		final TransactionDetails transactionDetails = atmService.withdrawAmount(new WithdrawalRequest());
		assertEquals(accountBalance, transactionDetails.getAccountBalance());
	}

}
