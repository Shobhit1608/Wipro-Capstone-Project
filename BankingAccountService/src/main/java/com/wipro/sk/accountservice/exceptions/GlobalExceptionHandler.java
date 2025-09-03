package com.wipro.sk.accountservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wipro.sk.customerservice.exceptions.CustomerNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(AccountNotFoundException.class)
	    public ResponseEntity<Error> handleAccountNotFoundException(AccountNotFoundException ex) {
	        Error errorResponse = new Error(
	                "Account Not Found",
	                ex.getMessage()
	        );
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
	  @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<Error> handleEntityNotFoundException(CustomerNotFoundException ex) {
	        Error errorResponse = new Error(
	                "Customer Not Found",
	                ex.getMessage()
	        );
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
}
