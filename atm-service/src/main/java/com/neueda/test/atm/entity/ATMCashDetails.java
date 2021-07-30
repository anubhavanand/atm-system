package com.neueda.test.atm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Anubhav.Anand
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ATMCashDetails {
	
	@Id
	public final Long ID = 1L;

	private Integer noOfFiveCurrency;
	
	private Integer noOfTenCurrency;
	
	private Integer noOfTwentyCurrency;
	
	private Integer noOfFiftyCurrency;
	
}