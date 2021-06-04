package com.morganstanley.iwp.dto;

import javax.validation.constraints.NotNull;

import com.morganstanley.iwp.entity.InsuranceCategoryEntity;
import com.morganstanley.iwp.entity.InsuranceUserEntity;

import lombok.Data;
import lombok.ToString;

public @Data @ToString class InsurancePurchaseDTO {

	@NotNull(message = "User -Id of the Insurance Purchase cannot be null")
	public InsuranceUserEntity userId;
	@NotNull(message = "Insurance Id of the Insurance Purchase cannot be null")
	public InsuranceCategoryEntity insuranceId;
	@NotNull(message = "Active Period of the Insurance Purchase cannot be null")
	public int activePeriod;
	@NotNull(message = "Status of the Insurance Purchase cannot be null")
	public String status;

	
}
