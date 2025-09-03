package com.wipro.sk.paymentservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(InsufficientBalanceException.class)
	   public ResponseEntity<Error> handleInsufficientBalance(InsufficientBalanceException ex) {
	        Error errorResponse = new Error(
	                "Bad Request",
	                ex.getMessage()
	        );
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(FeignException.NotFound.class)
	    public ResponseEntity<Error> AccountNotFound(FeignException.NotFound ex) {
	        Error errorResponse = new Error(
	                "Account Not Found",
	                "One of the recivier acoount could not be found."
	        );
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
}
