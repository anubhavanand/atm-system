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
public class FiftyCurrencyDispenserTest {
	
	private FiftyCurrencyDispenser currencyDispenser;
	
	private ATMCashDetails atmCashDetails;
	
	private TransactionDetails transactionDetails;
	
	private CurrencyDispenser nextDispenser;
	
	@BeforeEach
	public void setUp() {
		currencyDispenser = new FiftyCurrencyDispenser();
		transactionDetails= new TransactionDetails();
		nextDispenser = Mockito.mock(CurrencyDispenser.class);
	}

	@Test
	public void verifyNoOfFiftyDenominationDispensedWhenAmountWithdrawnIsGreaterThanFifty() {
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 70);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 9);
	}
	
	@Test
	public void verifyNoOfFiftyDenominationDispensedWhenAmountWithdrawnIsGreaterThanFiftyAndNotesInATMIsLess() {
		atmCashDetails = new ATMCashDetails(10, 10, 10, 1);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 100);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 0);
	}
	
	@Test
	public void verifyNoOfFiftyDenominationDispensedWhenAmountWithdrawnIsLessThanFifty() {
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 40);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 0);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 10);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSet() {
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		currencyDispenser.setNextDispenser(nextDispenser);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 70);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 9);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSetButAmountLeftIsZero() {
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		currencyDispenser.setNextDispenser(nextDispenser);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 50);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 9);
	}

}
