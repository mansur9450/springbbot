package com.springboot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.payload_dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(ResourceNotFound.class)// it handle specif excep like wrong id,..
	 
	    public ResponseEntity<ErrorDetails> handlePostNotFoundException(ResourceNotFound exeecption,WebRequest webRequest) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(),exeecption.getMessage(),webRequest.getDescription(false)); 
		 return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    
	 }

	    @ExceptionHandler(ApiException.class)
	    public ResponseEntity<ErrorDetails> handleCommentNotFoundException(ApiException apiException,WebRequest webRequest) {
	    	 ErrorDetails errorDetails = new ErrorDetails(new Date(),apiException.getMessage(),webRequest.getDescription(false)); 
	    	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
	                                                               WebRequest webRequest){
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
	                webRequest.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}