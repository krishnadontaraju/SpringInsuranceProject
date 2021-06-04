package com.morganstanley.iwp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ResponseStatus
public class CategoryException extends RuntimeException{
	
	public CategoryException(int exceptionCode, String exceptionMessage) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int exceptionCode;
	private String exceptionMessage;

}
