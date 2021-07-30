package com.neueda.test.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDetails {
	
	@Id
	private long accountId;
	
	private int pin;
	
	private double openingBalance;
	
	private double overDraft;

}
