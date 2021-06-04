package com.morganstanley.iwp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.morganstanley.iwp.dto.InsuranceUserDTO;
import com.morganstanley.iwp.entity.InsuranceUserEntity;
import com.morganstanley.iwp.exception.UserException;
import com.morganstanley.iwp.repository.UserRepository;
import com.morganstanley.iwp.response.ResponseDTO;
import com.morganstanley.iwp.util.TokenUtility;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class InsuranceUserService implements IInsuranceUserService{
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtility tokenManager;
	
	// User Registration Method
	@Override
	public InsuranceUserEntity addUser(InsuranceUserDTO userDTO) throws IOException {
		log.info("Requested for User Registration , Checking for Duplicates");
		Optional<InsuranceUserEntity> possibleUser = userRepository.findByMobileNumber(userDTO.getMobileNumber());
		
		if (possibleUser.isEmpty()) {// Checking for the presence of the User , if not then registering the user
			log.info("No Duplicates found , initiating User Registration");
			InsuranceUserEntity user =  new InsuranceUserEntity(userDTO);
			
			userRepository.save(user);
			log.info("User Registered with Mobile"+ userDTO.getMobileNumber());
			return user;
		}else {
			log.error("User Already Exists with Given Mobile Number");
			throw new UserException(501 , "User Already Exists with Given Mobile Number");
		}
		
		
	}
	// Method to view All Users
	@Override
	public List<InsuranceUserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	// Method to Update the User Details
	@Override
	public InsuranceUserEntity updateUser(String token, InsuranceUserDTO userDTO) {
		log.info("Requested Update User Method");
		long id = tokenManager.decodeToken(token);
		Optional<InsuranceUserEntity> probableUser = userRepository.findById(id);
		if (probableUser.isPresent()){ // Checking for the presence of the user
			log.info("Contact : "+probableUser.get()+" found initiating update method for token : "+token);

			probableUser.get().updateUserDetails(userDTO);// Calling the update method from Insurance User Entity
			userRepository.save(probableUser.get());
			log.info("Updated User to "+probableUser.get());
			return probableUser.get();
		}else {//User Not found Exception
			log.error("User was not found for Updation with mobile"+userDTO.getMobileNumber());
			throw new UserException(510, "Contact could not be found");
		}
	}

	//Method to Delete User
	@Override
	public ResponseDTO deleteUser(String token) {
		log.info("Requested for deltetion of User with ");
		long id = tokenManager.decodeToken(token);
		Optional<InsuranceUserEntity> probableUser = userRepository.findById(id);
		if (probableUser.isPresent()){ // Checking for the presence of User
			log.info("Contact : "+probableUser.get()+" found initiating deleted method for token : "+token);
			userRepository.delete(probableUser.get()); // Deleting the user
			log.info("Contact deleted successfully");
			return new ResponseDTO("Deleted Successfully" , HttpStatus.ACCEPTED);
		}else {
			log.error("User could not be found for Deletion");
			throw new UserException(520 , "User could not be found for Deletion");
		}
	}
	
	

}
