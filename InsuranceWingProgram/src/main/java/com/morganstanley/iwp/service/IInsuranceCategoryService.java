package com.morganstanley.iwp.service;

import java.util.List;

import javax.validation.Valid;

import com.morganstanley.iwp.dto.InsuranceCategoryDTO;
import com.morganstanley.iwp.entity.InsuranceCategoryEntity;
import com.morganstanley.iwp.response.ResponseDTO;

public interface IInsuranceCategoryService {

	InsuranceCategoryEntity addCategory(@Valid InsuranceCategoryDTO categoryDTO);

	List<InsuranceCategoryEntity> viewAllCategories();

	ResponseDTO deleteInsuranceCategory(String token);

	InsuranceCategoryEntity updateCategory(String token, @Valid InsuranceCategoryDTO categoryDTO);

}
