package com.neueda.test.atm.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TransactionDetails {
	
	private int noOfFiveCurrency;
	
	private int noOfTenCurrency;
	
	private int noOfTwentyCurrency;
	
	private int noOfFiftyCurrency;
	
	private AccountBalance accountBalance;

}
