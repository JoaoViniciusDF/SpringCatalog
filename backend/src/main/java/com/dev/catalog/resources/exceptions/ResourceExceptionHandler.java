package com.dev.catalog.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev.catalog.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		StandardError erro = new StandardError(); 
		
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("Resource not Found");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}
}
