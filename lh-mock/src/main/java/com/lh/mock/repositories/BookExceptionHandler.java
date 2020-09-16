package com.lh.mock.repositories;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lh.mock.repositories.exceptions.ErrorResponse;
import com.lh.mock.repositories.exceptions.RecordNotFoundException;


@EnableWebMvc
@ControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BookExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String INCORRECT_REQUEST = "INCORRECT_REQUEST";

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	public final ResponseEntity<ErrorResponse> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {
		List<String> details = new ArrayList<>();
		details.add("Didn't found any relevant object");
		ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse>  handleMIllegalAr(ConstraintViolationException ex){
		return null;
		
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse>  handleMIllegalArgumentException(IllegalArgumentException ex) {
		ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, null);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	List<String> errors = new ArrayList<>();
    			ex.getBindingResult().getFieldErrors()
    					.forEach(error -> errors.add(error.getField() + " : " + error.getDefaultMessage() ));
        ErrorResponse errorDetails = new ErrorResponse(INCORRECT_REQUEST, errors);
        return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    }
}