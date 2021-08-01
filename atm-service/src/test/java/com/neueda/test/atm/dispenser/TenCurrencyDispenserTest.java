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
public class TenCurrencyDispenserTest {
	
	private TenCurrencyDispenser currencyDispenser;
	
	private ATMCashDetails atmCashDetails;
	
	private TransactionDetails transactionDetails;
	
	private CurrencyDispenser nextDispenser;
	
	@BeforeEach
	public void setUp() {
		currencyDispenser = new TenCurrencyDispenser();
		atmCashDetails = new ATMCashDetails(10, 10, 10, 10);
		transactionDetails= new TransactionDetails();
		nextDispenser = Mockito.mock(CurrencyDispenser.class);
	}

	@Test
	public void verifyNoOfTenDenominationDispensedWhenAmountWithdrawnIsGreaterThanTen() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 25);
		assertEquals(transactionDetails.getNoOfTenCurrency(), 2);
		assertEquals(atmCashDetails.getNoOfTenCurrency(), 8);
	}
	
	@Test
	public void verifyNoOfTenDenominationDispensedWhenAmountWithdrawnIsLessThanTen() {
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 5);
		assertEquals(transactionDetails.getNoOfTenCurrency(), 0);
		assertEquals(atmCashDetails.getNoOfTenCurrency(), 10);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSet() {
		currencyDispenser.setNextDispenser(nextDispenser);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 25);
		assertEquals(transactionDetails.getNoOfTenCurrency(), 2);
		assertEquals(atmCashDetails.getNoOfTenCurrency(), 8);
	}
	
	@Test
	public void verifyDenominationsWhenNextDispenserIsSetButAmountLeftIsZero() {
		currencyDispenser.setNextDispenser(nextDispenser);
		currencyDispenser.dispense(atmCashDetails, transactionDetails, 10);
		assertEquals(transactionDetails.getNoOfTenCurrency(), 1);
		assertEquals(atmCashDetails.getNoOfTenCurrency(), 9);
	}

}
