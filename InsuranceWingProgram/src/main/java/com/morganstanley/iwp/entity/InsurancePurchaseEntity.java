package com.morganstanley.iwp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.morganstanley.iwp.dto.InsurancePurchaseDTO;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "new_insurances")
public @Data @ToString class InsurancePurchaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "insurance_creation_id")
	private long Id;
	@ManyToOne
	@JoinColumn(name = "user_id" , referencedColumnName = "user_serial_number")
	private InsuranceUserEntity userId;
	@ManyToOne
	@JoinColumn(name = "insurance_id" , referencedColumnName = "category_id")
	private InsuranceCategoryEntity insuranceId;
	
	private int activePeriod;
	private boolean claimStatus;
	private String status;
	private LocalDateTime addedTime = LocalDateTime.now();
	private LocalDateTime updatedTime;
	public InsurancePurchaseEntity(InsurancePurchaseDTO insurancePurchaseDTO) {
		super();
		this.updateCreationData(insurancePurchaseDTO);
		
	}
	
	public InsurancePurchaseEntity() {}
	
	public void updateCreationData(InsurancePurchaseDTO insurancePurchaseDTO) {
		this.userId = insurancePurchaseDTO.userId;
		this.insuranceId = insurancePurchaseDTO.insuranceId;
		this.activePeriod = insurancePurchaseDTO.activePeriod;
		this.status = insurancePurchaseDTO.status;
		this.updatedTime = LocalDateTime.now();
	}
	
	public void changeClaimStatus(boolean claimStatus) {
		this.claimStatus = claimStatus;
	}

	
}
