package com.neueda.test.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest {
	
	private long accountId;
	
	private int pin;
	
	private int amount;

}
