package com.morganstanley.iwp.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class InsuranceUserDetails implements UserDetails{

	private String userName;
	private List<GrantedAuthority> authorities;
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public InsuranceUserDetails(InsuranceUserEntity user) {
		this.userName = String.valueOf(user.getMobileNumber());
		this.password = user.getPassword();
		this.authorities = Arrays.stream(user.getRoles().split(","))
                					.map(SimpleGrantedAuthority::new)
                					.collect(Collectors.toList());
	}

	public InsuranceUserDetails() {
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return "pass";
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
