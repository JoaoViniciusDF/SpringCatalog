package com.dev.catalog.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev.catalog.services.exceptions.DatabaseException;
import com.dev.catalog.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		HttpStatus stauts = HttpStatus.NOT_FOUND;
		StandardError erro = new StandardError(); 
		
		erro.setTimestamp(Instant.now());
		erro.setStatus(stauts.value());
		erro.setError("Resource not Found");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(stauts).body(erro);
		
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		
		HttpStatus stauts = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(); 
		
		erro.setTimestamp(Instant.now());
		erro.setStatus(stauts.value());
		erro.setError("Database exception");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(stauts).body(erro);
		
	}
	
}
