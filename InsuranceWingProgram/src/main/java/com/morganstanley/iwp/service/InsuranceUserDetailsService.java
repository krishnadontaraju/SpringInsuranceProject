package com.morganstanley.iwp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.morganstanley.iwp.entity.InsuranceUserDetails;
import com.morganstanley.iwp.entity.InsuranceUserEntity;
import com.morganstanley.iwp.repository.UserRepository;

@Service
public class InsuranceUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 Optional<InsuranceUserEntity> user = userRepository.findByMobileNumber(userName);

	     user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

	     return user.map(InsuranceUserDetails::new).get();
	}

}
