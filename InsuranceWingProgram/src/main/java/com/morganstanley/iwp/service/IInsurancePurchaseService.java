package com.morganstanley.iwp.service;

import javax.validation.Valid;

import com.morganstanley.iwp.dto.InsurancePurchaseDTO;
import com.morganstanley.iwp.response.ResponseDTO;

public interface IInsurancePurchaseService {

	ResponseDTO addInsuranceToUser(@Valid InsurancePurchaseDTO creationDTO);

	ResponseDTO updateInsuranceOftheUser(String token, @Valid InsurancePurchaseDTO creationDTO);

	ResponseDTO viewAllInsurances();

	ResponseDTO viewInsurancesByStatus(String status);

	ResponseDTO viewInsuranceByUser(long id);

	ResponseDTO viewInsurancesByPeriod(int perioid);

	ResponseDTO deleteInsurance(long id);

	ResponseDTO updateClaimStatus(long insurancePurchaseId);

}
