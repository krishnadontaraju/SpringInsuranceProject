package com.morganstanley.iwp.response;

import lombok.Data;
import lombok.ToString;

public @Data @ToString class ResponseDTO {

	private String message;
	private Object data;

	public ResponseDTO(String message, Object data) {
		this.message = message;
		this.data = data;
	}

}
