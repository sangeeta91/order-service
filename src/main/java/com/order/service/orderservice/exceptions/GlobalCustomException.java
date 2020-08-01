package com.order.service.orderservice.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalCustomException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(OrderNotFoundException.class)
	protected ResponseEntity<Object> handleNoOrderFoundException(OrderNotFoundException ex) {
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", ex.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.NOT_FOUND.value()));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

}
