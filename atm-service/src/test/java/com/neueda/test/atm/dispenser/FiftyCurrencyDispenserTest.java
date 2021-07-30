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
public class FiftyCurrencyDispenserTest {
	
	private FiftyCurrencyDispenser currencyDispenser;
	
	private ATMCashDetails atmCashDetails;
	
	private TransactionDetails transactionDetails;
	
	@BeforeEach
	public void setUp() {
		currencyDispenser = new FiftyCurrencyDispenser();
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		transactionDetails= new TransactionDetails();
	}

	@Test
	public void verifyNoOfFiftyDenominationDispensedWhenAmountWithdrawnIsGreaterThanFifty() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 70);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 9);
	}
	
	@Test
	public void verifyNoOfFiftyDenominationDispensedWhenAmountWithdrawnIsLessThanFifty() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 40);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 0);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 10);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSet() {
		currencyDispenser.setNextDispenser(new MockCurrencyDispenser());
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 70);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 9);
		assertEquals(transactionDetails.getNoOfTwentyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfTwentyCurrency(), 9);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSetButAmountLeftIsZero() {
		currencyDispenser.setNextDispenser(new MockCurrencyDispenser());
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 50);
		assertEquals(transactionDetails.getNoOfFiftyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfFiftyCurrency(), 9);
		assertEquals(transactionDetails.getNoOfTwentyCurrency(), 0);
		assertEquals(atmCashDetails.getNoOfTwentyCurrency(), 10);
	}

}
