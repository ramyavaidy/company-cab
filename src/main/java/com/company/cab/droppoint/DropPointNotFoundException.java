package com.company.cab.droppoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DropPointNotFoundException extends RuntimeException {
	
	public DropPointNotFoundException(String message) {
		super(message);
	}

}
