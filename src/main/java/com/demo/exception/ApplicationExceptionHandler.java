package com.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.entity.ErrorMessage;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleRecordNotFoundException(Exception e){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(e.getMessage());
		errorMessage.setStatus(status);
		ResponseEntity<ErrorMessage> resEntity = new ResponseEntity<>(errorMessage,status);
		return resEntity;
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorMessage> methodNotAllowed(Exception e) {
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

		ErrorMessage errorMessag = new ErrorMessage();
		errorMessag.setMessage(e.getMessage());
		errorMessag.setStatus(status);
		ResponseEntity<ErrorMessage> resEntity = new ResponseEntity<>(errorMessag, status);
		return resEntity;
	}
	
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<ErrorMessage> BadRequestExceptions(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		ErrorMessage errorMessag = new ErrorMessage();
		errorMessag.setMessage(e.getMessage());
		errorMessag.setStatus(status);
		ResponseEntity<ErrorMessage> resEntity = new ResponseEntity<>(errorMessag, status);
		return resEntity;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST) // always send BadRequest to client
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField(); // reading the error field
			String errorMessage = error.getDefaultMessage();// reading the error msg
			errors.put(fieldName, errorMessage); // put in the map
		});
		return errors;
	}
	
	

}
