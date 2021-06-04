package com.morganstanley.iwp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morganstanley.iwp.entity.InsuranceCategoryEntity;

public interface CategoryRepository extends JpaRepository<InsuranceCategoryEntity , Long> {
	
	Optional<InsuranceCategoryEntity> findByInsuranceCode(String insuranceCode);


}
