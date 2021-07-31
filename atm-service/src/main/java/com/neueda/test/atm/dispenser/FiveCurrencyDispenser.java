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
public class FiveCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmCashDetails, final TransactionDetails transactionDetails, Integer amount) {
		log.debug("Running Five currency dispenser, ATMCashDetails: %s, TransactionDetails: %s, amount: ",
				atmCashDetails, transactionDetails, amount);
		if (amount >= CurrencyValue.FIVE.value()) {
			final int numberToBeWithdrawn = amount / CurrencyValue.FIVE.value();
			if(atmCashDetails.getNoOfFiveCurrency() >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.FIVE.value();
				atmCashDetails.setNoOfFiveCurrency(atmCashDetails.getNoOfFiveCurrency() - numberToBeWithdrawn);
				transactionDetails.setNoOfFiveCurrency(numberToBeWithdrawn);
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
