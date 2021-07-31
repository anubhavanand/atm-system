package com.neueda.test.account.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDebitInfo {
	
	private long accountId;
	
	private int pin;
	
	private int debitAmount;

}
