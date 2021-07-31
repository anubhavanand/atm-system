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
public class TwentyCurrencyDispenserTest {
	
	private TwentyCurrencyDispenser currencyDispenser;
	
	private ATMCashDetails atmCashDetails;
	
	private TransactionDetails transactionDetails;
	
	@BeforeEach
	public void setUp() {
		currencyDispenser = new TwentyCurrencyDispenser();
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		transactionDetails= new TransactionDetails();
	}

	@Test
	public void verifyNoOfTwentyDenominationDispensedWhenAmountWithdrawnIsGreaterThanTwenty() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 70);
		assertEquals(transactionDetails.getNoOfTwentyCurrency(), 3);
		assertEquals(atmCashDetails.getNoOfTwentyCurrency(), 7);
	}
	
	@Test
	public void verifyNoOfTwentyDenominationDispensedWhenAmountWithdrawnIsLessThanTwenty() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 10);
		assertEquals(transactionDetails.getNoOfTwentyCurrency(), 0);
		assertEquals(atmCashDetails.getNoOfTwentyCurrency(), 10);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSet() {
		currencyDispenser.setNextDispenser(new MockCurrencyDispenser());
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 70);
		assertEquals(transactionDetails.getNoOfTwentyCurrency(), 3);
		assertEquals(atmCashDetails.getNoOfTwentyCurrency(), 7);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSetButAmountLeftIsZero() {
		currencyDispenser.setNextDispenser(new MockCurrencyDispenser());
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 20);
		assertEquals(transactionDetails.getNoOfTwentyCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfTwentyCurrency(), 9);
	}

}
