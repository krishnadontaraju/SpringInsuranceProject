package com.morganstanley.iwp.dto;

import java.io.File;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

public @ToString @Data class InsuranceUserDTO {
	@NotNull(message = "Name of the Insurance User cannot be null")
	public String completeName;
	@NotNull(message = "Mobile Number of the Insurance User cannot be null")
	public String mobileNumber;
	@NotNull(message = "Age of the Insurance User cannot be null")
	public int age;
	@NotNull(message = "Occupation of the Insurance User cannot be null")
	public String occupation;
	@NotNull(message = "Family Background of the Insurance User cannot be null")
	public String familyBackground;
	@NotNull(message = "Pre-Existing Ailment of the Insurance User cannot be null")
	public String preExistingAilment;
	@NotNull(message = "Vehicle Information of the Insurance User cannot be null")
	public String vehicleInformation;
	@NotNull(message = "Identity Document Label of the Insurance User cannot be null")
	public String identityDocLabel;
	@NotNull(message = "Income Document Label of the Insurance User cannot be null")
	public String incomeDocumentLabel;
	@NotNull(message = "Health Document Label of the Insurance User cannot be null")
	public String healthDocumentLabel ;
	@NotNull(message = "Permenan tAddress of the Insurance User cannot be null")
	public List<String> permenantAddress;
	@NotNull(message = "Current Address of the Insurance User cannot be null")
	public List<String> currentAddress;
	@NotNull(message = "Identity Document of the Insurance User cannot be null")
	public File identityDocument;
	@NotNull(message = "Health Document of the Insurance User cannot be null")
	public File healthDocument;
	@NotNull(message = "Income Document of the Insurance User cannot be null")
	public File incomeDocument;
	@NotNull(message = "Password cannot be null")
	public String password;

}
