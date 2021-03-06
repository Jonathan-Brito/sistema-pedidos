package com.brito.sistemapedidos.resources.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brito.sistemapedidos.services.exceptions.AuthorizationException;
import com.brito.sistemapedidos.services.exceptions.DataIntegrityException;
import com.brito.sistemapedidos.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception
			, ServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis()  
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception
			, ServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), 
				exception.getMessage(), System.currentTimeMillis()  
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception
			, ServletRequest request){
		
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validação", System.currentTimeMillis()  
				);
		
		for(FieldError x : exception.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(ObjectNotFoundException exception
			, ServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), exception.getMessage(), System.currentTimeMillis()  
				);
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

}
