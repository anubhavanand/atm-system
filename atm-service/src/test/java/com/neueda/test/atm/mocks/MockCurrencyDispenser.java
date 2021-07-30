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
	public Integer dispense(ATMCashDetails atmDetails, TransactionDetails transactionDetails, Integer amount) {
		atmDetails.setNoOfTwentyCurrency(9);
		transactionDetails.setNoOfTwentyCurrency(1);
		return 0;
	}

	@Override
	public void setNextDispenser(final CurrencyDispenser nextDispenser) {
		
	}

}
