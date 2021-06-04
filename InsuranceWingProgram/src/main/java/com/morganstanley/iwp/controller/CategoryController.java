package com.morganstanley.iwp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.morganstanley.iwp.dto.InsuranceCategoryDTO;
import com.morganstanley.iwp.entity.InsuranceCategoryEntity;
import com.morganstanley.iwp.response.ResponseDTO;
import com.morganstanley.iwp.service.IInsuranceCategoryService;
import com.morganstanley.iwp.util.TokenUtility;


@RestController
@RequestMapping("/insurance/category")
public class CategoryController {
	
	@Autowired
	private IInsuranceCategoryService categoryService;
	
	@Autowired
	private TokenUtility tokenManager;
	
	//Controls the Registration of The Insurance scheme
	@PostMapping("/addCategory")
	public ResponseEntity<ResponseDTO> addContactsToTheBook(@Valid @RequestBody InsuranceCategoryDTO categoryDTO , @RequestPart MultipartFile healthDOc){
		
		InsuranceCategoryEntity category = categoryService.addCategory(categoryDTO);
		
		ResponseDTO response = new ResponseDTO("Insurance created Successfully" , tokenManager.createToken(category.getId()));
		
		return new ResponseEntity<ResponseDTO>(response , HttpStatus.OK);
		
	}
	
	//Controls the Updation of The Insurance scheme

	@PutMapping("/updates/{token}")
	public ResponseEntity<ResponseDTO> updateContactsOfTheBook(@PathVariable("token") String token , @Valid @RequestBody InsuranceCategoryDTO categoryDTO){
		InsuranceCategoryEntity category = categoryService.updateCategory(token , categoryDTO);
		
		ResponseDTO response = new ResponseDTO("Updated the Insurance Successfully" , category);

		return new ResponseEntity<ResponseDTO> (response , HttpStatus.OK);
	}

	//Controls the viewing possibility of The Insurance scheme

	
	@GetMapping(value = {"" , "/" , "/see"})
	public ResponseEntity<ResponseDTO> viewAllContacts(){
		
		List<InsuranceCategoryEntity> categories = categoryService.viewAllCategories();
		
		ResponseDTO responseDTO = new ResponseDTO("Get all Call Successful", categories);

		return new ResponseEntity<ResponseDTO> (responseDTO , HttpStatus.OK);
	}

	//Controls the Deleting of The Insurance scheme

    @DeleteMapping("/delete")
	ResponseEntity<ResponseDTO> deleteContacts(@RequestParam String token){

		ResponseDTO responseDTO = categoryService.deleteInsuranceCategory(token);

		return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);


	}
}
