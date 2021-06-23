package com.morganstanley.iwp.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.morganstanley.iwp.dto.InsuranceUserDTO;
import com.morganstanley.iwp.entity.InsuranceUserEntity;
import com.morganstanley.iwp.response.ResponseDTO;
import com.morganstanley.iwp.service.IInsuranceUserService;
import com.morganstanley.iwp.util.TokenUtility;

@RestController
@RequestMapping("/insurance/users")
public class UserController {

	@Autowired
	private IInsuranceUserService userService;
	@Autowired 
	private TokenUtility token;
	
	//Controls the Registration of User
	@PostMapping(value = "/addUser/adj")
	public ResponseEntity<ResponseDTO> addUser(@RequestBody InsuranceUserDTO userDTO ) throws IOException{
		
		InsuranceUserEntity user = userService.addUser(userDTO);
		
		ResponseDTO response = new ResponseDTO("Added User Successfully" , token.createToken(user.getId()) );
		
		return new ResponseEntity<ResponseDTO>(response , HttpStatus.CREATED);
		
	}
	
	//Controls the Retrieval of All Users
	
	@GetMapping("/users/adj")
	public ResponseEntity<ResponseDTO> getUsers(){
		
		List<InsuranceUserEntity> users = userService.getAllUsers();
		
		ResponseDTO response = new ResponseDTO("Get Call Successful" , users);
		
		return new ResponseEntity<ResponseDTO>(response , HttpStatus.FOUND);
		
	}
	
	//Controls the Updation of User
	
	@PutMapping("/update/{token}/adj")
	public ResponseEntity<ResponseDTO> updateUser (@PathVariable String token , @RequestBody InsuranceUserDTO userDTO){
		
		InsuranceUserEntity user = userService.updateUser(token , userDTO);
		
		ResponseDTO response = new ResponseDTO("Updated user Succesfully" , user);
		
		return new ResponseEntity<ResponseDTO>(response , HttpStatus.ACCEPTED);
		
	}
	
	//Controls the Deltion of User
	
	@DeleteMapping("/delete/adj")
	public ResponseEntity<ResponseDTO> deleteUser(@RequestParam String token){
		
		ResponseDTO response = userService.deleteUser(token);
		
		return new ResponseEntity<ResponseDTO>(response , HttpStatus.MOVED_PERMANENTLY);
	}
	
}
