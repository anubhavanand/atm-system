package com.neueda.test.atm.dispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.mocks.MockCurrencyDispenser;
/**
 * 
 * @author Anubhav.Anand
 *
 */
public class FiveCurrencyDispenserTest {
	
	private FiveCurrencyDispenser currencyDispenser;
	
	private ATMCashDetails atmCashDetails;
	
	private TransactionDetails transactionDetails;
	
	@BeforeEach
	public void setUp() {
		currencyDispenser = new FiveCurrencyDispenser();
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		transactionDetails= new TransactionDetails();
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
		currencyDispenser.setNextDispenser(new MockCurrencyDispenser());
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 15);
		assertEquals(transactionDetails.getNoOfFiveCurrency(), 3);
		assertEquals(atmCashDetails.getNoOfFiveCurrency(), 7);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSetButAmountLeftIsZero() {
		currencyDispenser.setNextDispenser(new MockCurrencyDispenser());
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 10);
		assertEquals(transactionDetails.getNoOfFiveCurrency(), 2);
		assertEquals(atmCashDetails.getNoOfFiveCurrency(), 8);
	}

}
