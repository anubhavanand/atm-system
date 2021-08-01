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
public class FiftyCurrencyDispenser implements CurrencyDispenser {

	private CurrencyDispenser nextDispenser;

	@Override
	public Integer dispense(final ATMCashDetails atmCashDetails, final TransactionDetails transactionDetails,
			Integer amount) {
		log.debug("Running Fify currency dispenser, ATMCashDetails: {}, TransactionDetails: {}, amount: ",
				atmCashDetails, transactionDetails, amount);
		final int noOfFiftyCurrencyInATM = atmCashDetails.getNoOfFiftyCurrency();
		if (amount >= CurrencyValue.FIFTY.value() && noOfFiftyCurrencyInATM >= 0) {
			final int numberToBeWithdrawn = amount / CurrencyValue.FIFTY.value();
			if (noOfFiftyCurrencyInATM >= numberToBeWithdrawn) {
				amount = amount % CurrencyValue.FIFTY.value();
				atmCashDetails.setNoOfFiftyCurrency(noOfFiftyCurrencyInATM - numberToBeWithdrawn);
				transactionDetails.setNoOfFiftyCurrency(numberToBeWithdrawn);
			} else {
				amount = amount - noOfFiftyCurrencyInATM * CurrencyValue.FIFTY.value();
				transactionDetails.setNoOfFiftyCurrency(noOfFiftyCurrencyInATM);
				atmCashDetails.setNoOfFiftyCurrency(0);
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
