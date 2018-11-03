package com.company.cab.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.company.cab.droppoint.DropPointNotFoundException;

@ControllerAdvice
@RestController
public class CompanyResponseEntityExceptionHanlder extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DropPointNotFoundException.class)
	public final ResponseEntity<Object> handleDropPointNotFoundException(DropPointNotFoundException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage());
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
}
