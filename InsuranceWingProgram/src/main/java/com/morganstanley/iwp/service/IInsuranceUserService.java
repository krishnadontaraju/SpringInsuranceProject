package com.morganstanley.iwp.service;

import java.io.IOException;
import java.util.List;

import com.morganstanley.iwp.dto.InsuranceUserDTO;
import com.morganstanley.iwp.entity.InsuranceUserEntity;
import com.morganstanley.iwp.response.ResponseDTO;

public interface IInsuranceUserService {

	InsuranceUserEntity addUser(InsuranceUserDTO userDTO) throws IOException;

	List<InsuranceUserEntity> getAllUsers();

	InsuranceUserEntity updateUser(String token, InsuranceUserDTO userDTO);

	ResponseDTO deleteUser(String token);


}
