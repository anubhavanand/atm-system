package com.neueda.test.atm.mocks;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.dispenser.CurrencyDispenser;
import com.neueda.test.atm.entity.ATMCashDetails;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class MockCurrencyDispenser implements CurrencyDispenser {

	@Override
	public Integer dispense(final ATMCashDetails atmDetails, final TransactionDetails transactionDetails, final Integer amount) {
		return 0;
	}

	@Override
	public void setNextDispenser(final CurrencyDispenser nextDispenser) {
		
	}

}
