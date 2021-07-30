package com.neueda.test.atm.dispenser;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.utils.CurrencyValue;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class TenCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmDetails, final TransactionDetails transactionDetails, Integer amount) {
		if (amount >= 10) {
			final int numberToBeWithdrawn = amount / CurrencyValue.TEN.value();
			if(atmDetails.getNoOfTenCurrency() >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.TEN.value();
				atmDetails.setNoOfTenCurrency(atmDetails.getNoOfTenCurrency() - numberToBeWithdrawn);
				transactionDetails.setNoOfTenCurrency(numberToBeWithdrawn);
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
