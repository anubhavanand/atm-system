package com.neueda.test.atm.dispenser;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.utils.CurrencyValue;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class FiftyCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmDetails, final TransactionDetails transactionDetails, Integer amount) {
		if (amount >= CurrencyValue.FIFTY.value()) {
			final int numberToBeWithdrawn = amount / CurrencyValue.FIFTY.value();
			if(atmDetails.getNoOfFiftyCurrency() >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.FIFTY.value();
				atmDetails.setNoOfFiftyCurrency(atmDetails.getNoOfFiftyCurrency() - numberToBeWithdrawn);
				transactionDetails.setNoOfFiftyCurrency(numberToBeWithdrawn);
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
