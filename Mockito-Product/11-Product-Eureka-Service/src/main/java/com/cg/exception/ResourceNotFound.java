package com.cg.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

//customized exception and give port no
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends Exception{

	public ResourceNotFound(String message) {
		super(message);
	}
	
}
