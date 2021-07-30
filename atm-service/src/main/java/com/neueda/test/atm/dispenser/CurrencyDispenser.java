package com.neueda.test.atm.dispenser;

import com.neueda.test.atm.VO.TransactionDetails;
import com.neueda.test.atm.entity.ATMCashDetails;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public interface CurrencyDispenser {
	
	Integer dispense(ATMCashDetails atmDetails, TransactionDetails transactionDetails, Integer amount);
	
	void setNextDispenser(CurrencyDispenser nextDispenser);

}
