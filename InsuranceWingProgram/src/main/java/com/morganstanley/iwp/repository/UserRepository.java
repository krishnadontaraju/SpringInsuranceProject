package com.morganstanley.iwp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morganstanley.iwp.entity.InsuranceUserEntity;

public interface UserRepository extends JpaRepository<InsuranceUserEntity, Long>{

	Optional<InsuranceUserEntity> findByMobileNumber(long mobileNumber);


}
