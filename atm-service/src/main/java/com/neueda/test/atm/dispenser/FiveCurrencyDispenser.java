package com.neueda.test.atm.dispenser;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.utils.CurrencyValue;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class FiveCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmDetails, final TransactionDetails transactionDetails, Integer amount) {
		if (amount >= CurrencyValue.FIVE.value()) {
			final int numberToBeWithdrawn = amount / CurrencyValue.FIVE.value();
			if(atmDetails.getNoOfFiveCurrency() >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.FIVE.value();
				atmDetails.setNoOfFiveCurrency(atmDetails.getNoOfFiveCurrency() - numberToBeWithdrawn);
				transactionDetails.setNoOfFiveCurrency(numberToBeWithdrawn);
			}
		}
		
		if (amount > 0 && nextDispenser != null) {
			return nextDispenser.dispense(atmDetails, transactionDetails, amount);
		}
		
		return amount;
	}

	@Override
	public void setNextDispenser(final CurrencyDispenser nextDispenser) {
		this.nextDispenser = nextDispenser;
	}

}
