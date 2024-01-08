package com.vv.code.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@RestControllerAdvice
/**
 * @author Natanael Muñoz
 * @version 1.0
 * Date: 09/06/2023
 */
public class ControllerAdvice {
	
	@ExceptionHandler(value= BadRequest.class)
	/**
	 * @param exception - recoge la exception
	 * @return Un error de tipo badRequest personalizado
	 */
	public ResponseEntity<BadRequestException> badRequestException(BadRequest exception){
		BadRequestException error = new BadRequestException("500", "Error en la peticion");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}	
}
