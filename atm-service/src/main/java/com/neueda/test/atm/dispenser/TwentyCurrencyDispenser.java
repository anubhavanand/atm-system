package com.neueda.test.atm.dispenser;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.utils.CurrencyValue;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Slf4j
public class TwentyCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmCashDetails, final TransactionDetails transactionDetails, Integer amount) {
		log.debug("Running Ten currency dispenser, ATMCashDetails: %s, TransactionDetails: %s, amount: ",
				atmCashDetails, transactionDetails, amount);
		if (amount >= CurrencyValue.TWENTY.value()) {
			final int numberToBeWithdrawn = amount / CurrencyValue.TWENTY.value();
			if(atmCashDetails.getNoOfTwentyCurrency() >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.TWENTY.value();
				atmCashDetails.setNoOfTwentyCurrency(atmCashDetails.getNoOfTwentyCurrency() - numberToBeWithdrawn);
				transactionDetails.setNoOfTwentyCurrency(numberToBeWithdrawn);
			}
		}
		
		if (amount > 0 && nextDispenser != null) {
			return nextDispenser.dispense(atmCashDetails, transactionDetails, amount);
		}
		
		return amount;
	}

	@Override
	public void setNextDispenser(final CurrencyDispenser nextDispenser) {
		this.nextDispenser = nextDispenser;
	}

}
