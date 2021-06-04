package com.morganstanley.iwp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseException extends RuntimeException{
	
	public PurchaseException(int exceptionCode, String exceptionMessage) {
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
