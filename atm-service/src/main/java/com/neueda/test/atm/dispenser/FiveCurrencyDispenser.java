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
		log.debug("Running Five currency dispenser, ATMCashDetails: {}, TransactionDetails: {}, amount: ",
				atmCashDetails, transactionDetails, amount);

		final int noOfFiveCurrencyInATM = atmCashDetails.getNoOfFiveCurrency();
		if (amount >= CurrencyValue.FIVE.value() && noOfFiveCurrencyInATM >= 0) {
			final int numberToBeWithdrawn = amount / CurrencyValue.FIVE.value();
			if (noOfFiveCurrencyInATM >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.FIVE.value();
				atmCashDetails.setNoOfFiveCurrency(noOfFiveCurrencyInATM - numberToBeWithdrawn);
				transactionDetails.setNoOfFiveCurrency(numberToBeWithdrawn);
			} else {
				amount = amount - noOfFiveCurrencyInATM * CurrencyValue.FIVE.value();
				transactionDetails.setNoOfFiveCurrency(noOfFiveCurrencyInATM);
				atmCashDetails.setNoOfFiveCurrency(0);
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
