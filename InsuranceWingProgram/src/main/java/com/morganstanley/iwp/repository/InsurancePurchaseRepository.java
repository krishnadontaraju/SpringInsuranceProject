package com.morganstanley.iwp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.morganstanley.iwp.entity.InsurancePurchaseEntity;

public interface InsurancePurchaseRepository extends JpaRepository<InsurancePurchaseEntity , Long>{

	
	@Query(value = "SELECT * FROM new_insurances as n \r\n"
			+ "LEFT JOIN users as u ON n.user_id = u.user_serial_number\r\n"
			+ "RIGHT JOIN insurance_categories as i ON n.insurance_id = i.category_id WHERE n.status = :status" , nativeQuery = true)
	
	 List<InsurancePurchaseEntity>findByStatus(String status);
	
	@Query(value = "SELECT * FROM new_insurances as n \r\n"
			+ "LEFT JOIN users as u ON n.user_id = u.user_serial_number\r\n"
			+ "RIGHT JOIN insurance_categories as i ON n.insurance_id = i.category_id WHERE u.user_serial_number = ?" , nativeQuery = true)

	List<InsurancePurchaseEntity> findByUser(long id);
	
	@Query(value = "SELECT * FROM new_insurances as n \r\n"
			+ "LEFT JOIN users as u ON n.user_id = u.user_serial_number\r\n"
			+ "RIGHT JOIN insurance_categories as i ON n.insurance_id = i.category_id WHERE n.active_period <= ?" , nativeQuery = true)

	List<InsurancePurchaseEntity> findByPeriod(int perioid);

	@Query(value = "SELECT * FROM new_insurances as n WHERE n.insurance_id = ?" , nativeQuery = true)
	Optional<InsurancePurchaseEntity> findByInsuranceId(long id);


}
