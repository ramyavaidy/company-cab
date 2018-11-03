package com.company.cab.teammember;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public class CabCapacityNotEnoughException extends RuntimeException {
		
		public CabCapacityNotEnoughException(String message) {
			super(message);
		}

	}

