package com.morganstanley.iwp.service;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.morganstanley.iwp.dto.InsurancePurchaseDTO;
import com.morganstanley.iwp.entity.InsuranceCategoryEntity;
import com.morganstanley.iwp.entity.InsurancePurchaseEntity;
import com.morganstanley.iwp.entity.InsuranceUserEntity;
import com.morganstanley.iwp.exception.PurchaseException;
import com.morganstanley.iwp.exception.UserException;
import com.morganstanley.iwp.repository.CategoryRepository;
import com.morganstanley.iwp.repository.InsurancePurchaseRepository;
import com.morganstanley.iwp.repository.UserRepository;
import com.morganstanley.iwp.response.ResponseDTO;
import com.morganstanley.iwp.util.TokenUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsurancePurchaseService implements IInsurancePurchaseService{
	
	@Autowired
	private InsurancePurchaseRepository creationRepository;
	
	@Autowired
	private TokenUtility tokenManager;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	// Purchase Insurance Method
	@Override
	public ResponseDTO addInsuranceToUser(@Valid InsurancePurchaseDTO insurancePurchaseDTO) {
		
			Optional<InsurancePurchaseEntity> insurancePurchased = creationRepository.findByInsuranceId(insurancePurchaseDTO.getInsuranceId().Id);
			
			Optional<InsuranceCategoryEntity> insuranceCategory = categoryRepository.findById(insurancePurchaseDTO.getInsuranceId().Id);
			
			Optional<InsuranceUserEntity> insuranceUser = userRepository.findById(insurancePurchaseDTO.getUserId().Id);
		
		
			if (insurancePurchased.isEmpty()) {
				// Checking if the user had already purchased the Insurance
				log.info("No pre-existing Insurance purchased found initiating add method");
				InsurancePurchaseEntity contactData = mapper.map(insurancePurchaseDTO , InsurancePurchaseEntity.class);
				creationRepository.save(contactData);

				log.info("Insurance purchased successfully "+contactData);
				
				ResponseDTO response =  new ResponseDTO("Insurance purchased successfully " , tokenManager.createToken(contactData.getId()));
				
				return response;
				}else if (insuranceCategory.isEmpty()) {// Given wrong insurance Id
				
					log.error("Insurance Scheme does not exist "+insurancePurchaseDTO.getInsuranceId().Id);
					throw new PurchaseException(511 , "Insurance Scheme does not exist for Purchase");
				
				}else if (insuranceUser.isEmpty()) {// Given wrong User id
				
					log.error("User does not exist "+insurancePurchaseDTO.getUserId().Id);
					throw new PurchaseException(512 , "User does not exist ");
				
				} else{// Exception for already purchased Insurance
					log.error("User has already purchased the Insurance "+insurancePurchased.get());
					throw new PurchaseException(513 , "Insurance Already purchased by User");
				}
			
			
		}
	

	//Method to view All Insurances
	@Override
	public ResponseDTO viewAllInsurances() {
		
		log.info("Requested View all and fetched "+ creationRepository.findAll());
		
		ResponseDTO response =  new ResponseDTO("Get All Call Successful" ,creationRepository.findAll());
		
		return response;
	}

	// Method to view Insurances by status
	@Override
	public ResponseDTO viewInsurancesByStatus(String status) {
		
		if (creationRepository.findByStatus(status) != null) {
			log.info("Requested View by status and fetched "+ creationRepository.findByStatus(status));
			ResponseDTO response =  new ResponseDTO("Get All Call Successful" ,creationRepository.findByStatus(status));
			return response;
		}else {
			throw new PurchaseException(523 , "No Record found with status "+ status);
		}
		
	}

	//Method to view Insurances purchased by the user
	@Override
	public ResponseDTO viewInsuranceByUser(long id) {
		if (creationRepository.findByUser(id) != null) {
			log.info("Requested View by user-id and fetched "+ creationRepository.findByUser(id));
			ResponseDTO response =  new ResponseDTO("Get All Call Successful" ,creationRepository.findByUser(id));
			return response;
		}else {
			throw new PurchaseException(523 , "No Record found with id "+ id);
		}
		
	}

	// Method to view Insurances that are in between the period given
	@Override
	public ResponseDTO viewInsurancesByPeriod(int perioid) {

		if (creationRepository.findByPeriod(perioid) != null) {
			log.info("Requested View by period and fetched "+ creationRepository.findByPeriod(perioid));
			ResponseDTO response =  new ResponseDTO("Get All Call Successful" ,creationRepository.findByPeriod(perioid));
			return response;
		}else {
			throw new PurchaseException(523 , "No Record found within period "+ perioid);
		}
		
	}
	
	// Claim Insurance Method
	@Override
	public ResponseDTO updateClaimStatus(long insurancePurchaseId) {
		
		Optional<InsurancePurchaseEntity> isInsurancePurchased = creationRepository.findById(insurancePurchaseId);
		if (isInsurancePurchased.isPresent()) {//Checking for the presence of the insurance
				
			log.info("Requested to change the claim status of "+isInsurancePurchased.get());

				
			//Considering that at the time of Purchase the Insurance shall not be claimed t=and the Insurance will be claimed only after
			//So, Initially set the claim to false
				
			isInsurancePurchased.get().changeClaimStatus(true);//Changing the Status to true 
				
			log.info("Successfully claimed insurance for "+isInsurancePurchased.get().getUserId().getCompleteName() +
					" against "+isInsurancePurchased.get().getInsuranceId().getInsuranceName());
				
			return new ResponseDTO("Successfully claimed the Insurance " , HttpStatus.ACCEPTED);
			}else {//Insurance not found Exception
				log.error("Could not Claim the Insurance "+isInsurancePurchased.get().getUserId().getCompleteName()+
						" With Insurance "+isInsurancePurchased.get().getInsuranceId().getInsuranceName()+" Could not be found");
					
				throw new PurchaseException(500 , "Could not Claim the Insurance "+isInsurancePurchased.get().getUserId().getCompleteName()+
						" With Insurance "+isInsurancePurchased.get().getInsuranceId().getInsuranceName()+" Could not be found");
					
			}
			
	}
		
		
	// Insurance Updation Method
	@Override
	public ResponseDTO updateInsuranceOftheUser(String token, @Valid InsurancePurchaseDTO insurancePurchaseDTO) {
			
		long id = tokenManager.decodeToken(token);
			
		Optional<InsurancePurchaseEntity> insurancePurchased = creationRepository.findById(id);
			
		Optional<InsuranceCategoryEntity> insuranceCategory = categoryRepository.findById(insurancePurchaseDTO.getInsuranceId().Id);
			
		Optional<InsuranceUserEntity> insuranceUser = userRepository.findById(insurancePurchaseDTO.getUserId().Id);
			
		if (insuranceUser.isEmpty()) {// Given wrong User id
				
			log.error("User does not exist "+insurancePurchaseDTO.getUserId().Id);
			throw new PurchaseException(512 , "User does not exist ");
			
		}else if (insuranceCategory.isEmpty()) {// Given wrong insurance Id
			
			log.error("Insurance Scheme does not exist "+insurancePurchaseDTO.getInsuranceId().Id);
			throw new PurchaseException(511 , "Insurance Scheme does not exist for Purchase");
		
		} else if(!insurancePurchased.isEmpty()){// Exception for already purchased Insurance
			log.error("User has already purchased the Insurance "+insurancePurchased.get());
			throw new PurchaseException(513 , "Insurance Already purchased by User");
		}else {
			log.info("Contact : "+insurancePurchased.get()+" found initiating update method for token : "+token);

			insurancePurchased.get().updateCreationData(insurancePurchaseDTO);// Calling the update method of contact data class
			creationRepository.save(insurancePurchased.get());
			log.info("Updated Contact to "+insurancePurchased.get());
			ResponseDTO response = new ResponseDTO("Updated Successfully" , insurancePurchased.get());
			return response;
		}
	}

	// Delete Method
	@Override
	public ResponseDTO deleteInsurance(long id) {
		Optional<InsurancePurchaseEntity> insurancePurchased = creationRepository.findById(id);
		if (insurancePurchased.isPresent()){ // Checking for the presence of the Insurance
			log.info("Insurance : "+insurancePurchased.get()+" found initiating deleted method for id : "+id);
			creationRepository.delete(insurancePurchased.get()); // Calling the delete method
			log.info("Insurance deleted successfully");
			return new ResponseDTO("Deleted Successfully" , HttpStatus.MOVED_PERMANENTLY);
		}else {
			throw new UserException(500 , "Insurance could not be found to be deleted");
		}
	}
	
}
