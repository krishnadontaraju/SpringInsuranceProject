package com.morganstanley.iwp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morganstanley.iwp.dto.InsurancePurchaseDTO;
import com.morganstanley.iwp.response.ResponseDTO;
import com.morganstanley.iwp.service.IInsurancePurchaseService;

@RestController
@RequestMapping("/insurance/createInsurance")
public class PurchaseController {


	@Autowired
	private IInsurancePurchaseService purchaseService;
	
	// Controls the Purchase of Insurance by User
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addInsuranceToUser(@Valid @RequestBody InsurancePurchaseDTO creationDTO){
		
		ResponseDTO response = purchaseService.addInsuranceToUser(creationDTO);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.CREATED);
	}
	
	// Controls the Update of Insurance by User

	@PutMapping("/update/{token}")
	public ResponseEntity<ResponseDTO> updateInsuranceOfuser(@PathVariable String token , @Valid @RequestBody InsurancePurchaseDTO creationDTO){
		ResponseDTO response = purchaseService.updateInsuranceOftheUser(token , creationDTO);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.ACCEPTED);
	}

	// Controls the view all Insurances

	@GetMapping("/view/all")
	public ResponseEntity<ResponseDTO> viewAllUserInsurances(){
		ResponseDTO response = purchaseService.viewAllInsurances();
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.OK);
	}

	// Controls the view of Insurances with status

	@GetMapping("/view/status/{status}")
	public ResponseEntity<ResponseDTO> viewAllUsersByStatus(@PathVariable String status){
		ResponseDTO response = purchaseService.viewInsurancesByStatus(status);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.CREATED);
	}

	// Controls the view of Insurances with User id

	@GetMapping("/view/user/{id}")
	public ResponseEntity<ResponseDTO> viewAllInsurancesByUser(@PathVariable long id){
		
		ResponseDTO response = purchaseService.viewInsuranceByUser(id);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.OK);
	}

	// Controls the view of Insurances within period by User

	@GetMapping("/view/period/{peroid}")
	public ResponseEntity<ResponseDTO> viewAllInsuranceByPeriod(@PathVariable int peroid){
		
		ResponseDTO response = purchaseService.viewInsurancesByPeriod(peroid);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.OK);
	}

	// Controls the Deletion of Insurance by User

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteInsurance(@PathVariable long id){
		ResponseDTO response = purchaseService.deleteInsurance(id);
		
		return new ResponseEntity<ResponseDTO> (response, HttpStatus.OK);
	}

	// Controls the Claim of Insurance by User

	@PutMapping("/claim/{purchaseId}")
	public ResponseEntity<ResponseDTO> claimInsuranceOfTheUser(@PathVariable long purchaseId){
		
		ResponseDTO response = purchaseService.updateClaimStatus(purchaseId);
		
		return new ResponseEntity<ResponseDTO>(response , HttpStatus.OK);
		
	}
	
	
	
}
