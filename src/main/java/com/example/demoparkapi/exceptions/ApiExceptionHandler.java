package com.example.demoparkapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,
				HttpStatus.UNPROCESSABLE_ENTITY, 
				"formato de campo invalido, por favor insira um campo valido",
				result));
	}
	
	@ExceptionHandler({UserNameUniqueException.class, CpfUniqueVioleationException.class})
	public ResponseEntity<ErrorMessage> UserNameUniqueException(RuntimeException ex, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,
				HttpStatus.CONFLICT, 
				ex.getMessage()));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFoundException(RuntimeException ex, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,
				HttpStatus.NOT_FOUND, 
				ex.getMessage()));
	}
	
	@ExceptionHandler(PasswordInvalidException.class)
	public ResponseEntity<ErrorMessage> passowordInvalidException(PasswordInvalidException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,
				HttpStatus.BAD_REQUEST, 
				ex.getMessage()));
	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,
				HttpStatus.FORBIDDEN, 
				ex.getMessage()));
	}
	

}
