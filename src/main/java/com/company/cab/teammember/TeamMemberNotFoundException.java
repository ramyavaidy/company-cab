package com.company.cab.teammember;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamMemberNotFoundException extends RuntimeException {
	
	public TeamMemberNotFoundException(String message) {
		super(message);
	}

}
