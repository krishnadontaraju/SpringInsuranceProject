package com.morganstanley.iwp.entity;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.morganstanley.iwp.dto.InsuranceUserDTO;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@SecondaryTable(name = "documents" , pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public @Data @ToString class InsuranceUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_serial_number")
	public long Id;
	private String completeName;
	private String mobileNumber;
	private int age;
	private String occupation;
	private String familyBackground;
	private String preExistingAilment;
	private String vehicleInformation;
	private LocalDateTime registrationTime;
	private LocalDateTime lastUpdationTime;
	private String kyc;
	private String password;
	private String roles;
	
	
	@Column(name = "identity_doc_type" , table = "documents")
	private String identityDocLabel;
	
	@Column(name = "identity_document" , table = "documents")
	private File identityDocument;
	
	@Column(name = "income_doc_type" , table = "documents")
	private String incomeDocumentLabel;
	
	@Column(name = "income_document" , table = "documents")
	private File incomeDocument;
	
	@Column(name = "health_doc_type" , table = "documents")
	private String healthDocumentLabel ;
	
	@Column(name = "health_document" , table = "documents")
	private File healthDocument;
	
	
	
	@ElementCollection
	@CollectionTable(name = "address" , joinColumns = @JoinColumn(name = "user_id") )
	@Column(name = "permenant_address")
	private List<String> permenantAddress;
	@ElementCollection
	@CollectionTable(name = "address" , joinColumns = @JoinColumn(name = "user_id") )
	@Column(name = "current_address")
	private List<String> currentAddress;
	
	public InsuranceUserEntity() {}	
	public InsuranceUserEntity(InsuranceUserDTO userDTO) {
		this.registrationTime = LocalDateTime.now();
		this.updateUserDetails(userDTO);
	}

	public void updateUserDetails(InsuranceUserDTO userDTO) {
		this.completeName = userDTO.completeName;
		this.age = userDTO.age;
		this.currentAddress = userDTO.currentAddress;
		this.familyBackground =userDTO.familyBackground;
		this.mobileNumber = userDTO.mobileNumber;
		this.permenantAddress = userDTO.permenantAddress;
		this.occupation = userDTO.occupation;
		this.preExistingAilment =  userDTO.preExistingAilment;
		this.identityDocLabel  = userDTO.identityDocLabel;
		this.healthDocumentLabel = userDTO.healthDocumentLabel;
		this.incomeDocumentLabel = userDTO.incomeDocumentLabel;
		this.lastUpdationTime = LocalDateTime.now();
		this.incomeDocument = userDTO.incomeDocument;
		this.identityDocument = userDTO.identityDocument;
		this.healthDocument = userDTO.healthDocument;
	}

	public InsuranceUserEntity(long id) {
		super();
		this.Id = id;
	}
	
//	public void uploadTheFiles(MultipartFile identityDocument , MultipartFile incomeDocument , MultipartFile healthDocument) throws IOException {
//		
//		this.identityDocument = ;
//		this.incomeDocument = incomeDocument.getBytes();
//		this.healthDocument = healthDocument.getBytes();
//		
//		
//	}
	
	
}
