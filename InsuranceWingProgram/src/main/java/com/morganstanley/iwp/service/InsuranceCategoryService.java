package com.morganstanley.iwp.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.morganstanley.iwp.dto.InsuranceCategoryDTO;
import com.morganstanley.iwp.entity.InsuranceCategoryEntity;
import com.morganstanley.iwp.exception.CategoryException;
import com.morganstanley.iwp.exception.UserException;
import com.morganstanley.iwp.repository.CategoryRepository;
import com.morganstanley.iwp.response.ResponseDTO;
import com.morganstanley.iwp.util.TokenUtility;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class InsuranceCategoryService implements IInsuranceCategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private TokenUtility tokenManager;
	
	@Autowired
	private ModelMapper mapper;
	
	// Insurance Scheme Registration Method
	@Override
	public InsuranceCategoryEntity addCategory(@Valid InsuranceCategoryDTO categoryDTO) {
		log.info("Requested for Insurance Scheme Registration , Checking for Duplicates");
        Optional<InsuranceCategoryEntity> probableInsuranceCategory = categoryRepository.findByInsuranceCode(categoryDTO.getInsuranceCode());
		
		if (probableInsuranceCategory.isPresent()) { // Checking if Contact is already present , if present raising error
			log.error("Insurance Scheme already found");
			throw new CategoryException(600 , "Insurance already found");
		}
		else {
			log.info("No pre-existing Insurance scheme found initiating add method");
			InsuranceCategoryEntity insuranceCategory = mapper.map(categoryDTO , InsuranceCategoryEntity.class);
			categoryRepository.save(insuranceCategory);

			log.info("Insurance scheme has been Registered "+insuranceCategory);
			
			return insuranceCategory;
			
		}
	}

	// Method to update Insurance Schemes
	@Override
	public InsuranceCategoryEntity updateCategory(String token, @Valid InsuranceCategoryDTO categoryDTO) {
		log.info("Requested Update User Method");
		long id = tokenManager.decodeToken(token);
		Optional<InsuranceCategoryEntity> probableInsuranceCategory = categoryRepository.findById(id);
		if (probableInsuranceCategory.isPresent()){ // Checking for the presence of the contact
			log.info("Insurance Scheme : "+probableInsuranceCategory.get()+" found initiating update method for token : "+token);

			probableInsuranceCategory.get().updateCategory(categoryDTO);// Calling the update method of contact data class
			categoryRepository.save(probableInsuranceCategory.get());
			log.info("Updated Insurance Scheme to "+probableInsuranceCategory.get());
			return probableInsuranceCategory.get();
		}else {
			log.error("Insurance Scheme could not be found");
			throw new CategoryException(400, "Insurance Scheme could not be found");
		}
	}

	//Method to view All Schemes
	@Override
	public List<InsuranceCategoryEntity> viewAllCategories() {
		return categoryRepository.findAll();
	}
	
	// Method to delete Insurance Category
	@Override
	public ResponseDTO deleteInsuranceCategory(String token) {
		log.info("Requested for deltetion of Insurance Scheme with ");
		long id = tokenManager.decodeToken(token);
		Optional<InsuranceCategoryEntity> isThere = categoryRepository.findById(id);
		if (isThere.isPresent()){ // Checking for the presence of the Insurance Scheme
			log.info("Insurance Scheme : "+isThere.get()+" found initiating deleted method for token : "+token);
			categoryRepository.delete(isThere.get()); // Calling the update method of contact data class
			log.info("Insurance Scheme deleted successfully");
			return new ResponseDTO("Insurance Scheme Successfully" , HttpStatus.MOVED_PERMANENTLY);
		}else {
			log.error("Insurance Scheme could not be found to be deleted");
			throw new UserException(500 , "Insurance Scheme could not be found to be deleted");
		}
	}

}
