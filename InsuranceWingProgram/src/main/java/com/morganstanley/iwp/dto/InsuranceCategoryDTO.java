package com.morganstanley.iwp.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

public @Data @ToString class InsuranceCategoryDTO {
	@NotNull(message = "Name of the Insurance Scheme cannot be null")
	public String insuranceName;
	@NotNull(message = "Status of the Insurance Scheme cannot be null")
	public String insuranceStatus;
	@NotNull(message = "scheme of the Insurance Scheme cannot be null")
	public String insuranceScheme;
	@NotNull(message = "Code of the Insurance Scheme cannot be null")
	public String insuranceCode;

}
