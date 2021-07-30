package com.neueda.test.atm.dispenser;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.utils.CurrencyValue;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class TwentyCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmDetails, final TransactionDetails transactionDetails, Integer amount) {
		if (amount >= 20) {
			final int numberToBeWithdrawn = amount / CurrencyValue.TWENTY.value();
			if(atmDetails.getNoOfTwentyCurrency() >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.TWENTY.value();
				atmDetails.setNoOfTwentyCurrency(atmDetails.getNoOfTwentyCurrency() - numberToBeWithdrawn);
				transactionDetails.setNoOfTwentyCurrency(numberToBeWithdrawn);
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
