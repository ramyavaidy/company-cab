package com.company.cab.cabs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CabNotFoundException extends RuntimeException {

	public CabNotFoundException(String message) {
		super(message);
	}
}
