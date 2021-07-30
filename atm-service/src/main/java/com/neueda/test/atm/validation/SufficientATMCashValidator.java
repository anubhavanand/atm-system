package com.neueda.test.atm.validation;

import org.springframework.http.HttpStatus;

import com.neueda.test.atm.controller.errorHandler.ValidationFailedException;
import com.neueda.test.atm.entity.ATMCashDetails;
import com.neueda.test.atm.model.WithdrawalRequest;
import com.neueda.test.atm.service.entity.repository.ATMRepository;
import com.neueda.test.atm.utils.AmountBuilder;

/**
 * 
 * @author Anubhav.Anand
 *
 */
public class SufficientATMCashValidator implements Validator<WithdrawalRequest>{
	
	private final ATMRepository atmRepository;

	public SufficientATMCashValidator(final ATMRepository atmRepository) {
		this.atmRepository = atmRepository;
	}
	
	@Override
	public boolean isValid(final WithdrawalRequest entity) {
		final ATMCashDetails atmDetails = atmRepository.getById(1L);
		if(entity.getAmount() > getAvailableAmountInATM(atmDetails)) {
			throw new ValidationFailedException(HttpStatus.UNPROCESSABLE_ENTITY, "Insufficient Cash.");
		}
		return true;
	}
	
	private Integer getAvailableAmountInATM(final ATMCashDetails atmDetails) {
		return new AmountBuilder().setNoOfFiftyCurrency(atmDetails.getNoOfFiftyCurrency())
				.setNoOfTwentyCurrency(atmDetails.getNoOfTwentyCurrency())
				.setNoOfTenCurrency(atmDetails.getNoOfTenCurrency())
				.setNoOfFiveCurrency(atmDetails.getNoOfFiveCurrency()).getTotalAmount();
	}

}
