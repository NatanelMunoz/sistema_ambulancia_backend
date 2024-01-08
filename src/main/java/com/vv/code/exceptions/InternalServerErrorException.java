package com.vv.code.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class InternalServerErrorException extends RuntimeException {

	private String code;
	private HttpStatus status;

	public InternalServerErrorException(String code, String message, HttpStatus status) {
		super(message);
		this.code = code;
		this.status = status;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
