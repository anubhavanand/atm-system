package com.neueda.test.atm.dispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
/**
 * 
 * @author Anubhav.Anand
 *
 */
public class FiveCurrencyDispenserTest {
	
	private FiveCurrencyDispenser currencyDispenser;
	
	private ATMCashDetails atmCashDetails;
	
	private TransactionDetails transactionDetails;
	
	private CurrencyDispenser nextDispenser;
	
	@BeforeEach
	public void setUp() {
		currencyDispenser = new FiveCurrencyDispenser();
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		transactionDetails= new TransactionDetails();
		nextDispenser = Mockito.mock(CurrencyDispenser.class);
	}

	@Test
	public void verifyNoOfFiveDenominationDispensedWhenAmountWithdrawnIsGreaterThanFive() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 10);
		assertEquals(transactionDetails.getNoOfFiveCurrency(), 2);
		assertEquals(atmCashDetails.getNoOfFiveCurrency(), 8);
	}
	
	@Test
	public void verifyNoOfFiveDenominationDispensedWhenAmountWithdrawnIsLessThanFive() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 3);
		assertEquals(transactionDetails.getNoOfFiveCurrency(), 0);
		assertEquals(atmCashDetails.getNoOfFiveCurrency(), 10);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSet() {
		currencyDispenser.setNextDispenser(nextDispenser);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 18);
		assertEquals(transactionDetails.getNoOfFiveCurrency(), 3);
		assertEquals(atmCashDetails.getNoOfFiveCurrency(), 7);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSetButAmountLeftIsZero() {
		currencyDispenser.setNextDispenser(nextDispenser);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 10);
		assertEquals(transactionDetails.getNoOfFiveCurrency(), 2);
		assertEquals(atmCashDetails.getNoOfFiveCurrency(), 8);
	}

}
