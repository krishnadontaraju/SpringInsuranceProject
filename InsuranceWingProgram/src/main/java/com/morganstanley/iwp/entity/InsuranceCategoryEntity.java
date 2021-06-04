package com.morganstanley.iwp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.morganstanley.iwp.dto.InsuranceCategoryDTO;

import lombok.Data;

@Entity
@Table(name = "insurance_categories")
@Data
public class InsuranceCategoryEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	public long Id;
	private String insuranceName;
	private String insuranceStatus;
	private String insuranceScheme;
	private LocalDateTime additionTime = LocalDateTime.now();
	private LocalDateTime lastUpDateTime;
	private String insuranceCode;
	
	public InsuranceCategoryEntity(InsuranceCategoryDTO categoryDTO) {
		this.updateCategory(categoryDTO);
	}
	
	public void updateCategory(InsuranceCategoryDTO categoryDTO) {
		this.insuranceName = categoryDTO.insuranceName;
		this.insuranceStatus = categoryDTO.insuranceStatus;
		this.insuranceScheme = categoryDTO.insuranceScheme;
		this.insuranceCode = categoryDTO.insuranceCode;
		this.lastUpDateTime = LocalDateTime.now();
		
		
	}
	
	public InsuranceCategoryEntity () {}

	public InsuranceCategoryEntity(long id) {
		super();
		this.Id = id;
	}
	
}
