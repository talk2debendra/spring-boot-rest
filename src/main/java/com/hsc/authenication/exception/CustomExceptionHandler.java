package com.hsc.authenication.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	
	@Autowired
	private Environment environment;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class.getName());

	/*
	 * Handles if the required request body missed
	 * */
	@Override
	public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request){
		
		String resolvedMessage =getResolvedMessgae(ex.getMessage(),"error.body.missing.msg",ex);
		LOGGER.error(resolvedMessage);
		
		
		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details),HttpStatus.NOT_ACCEPTABLE);
	}

	
	/*
	 * Handles if the request mapping method mismatch
	 * */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String resolvedMessage =getResolvedMessgae(ex.getMessage(),"error.request.method.notsupport.msg",ex);
		LOGGER.error(resolvedMessage);
		
		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details),HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	
	@ExceptionHandler({Exception.class})
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		
		String resolvedMessage =getResolvedMessgae(ex.getMessage(),"error.internal.servererror.msg",ex);
		LOGGER.error(resolvedMessage);
		
		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	/*
	 * Handles if the required resource not found
	 * */
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		String resolvedMessage =getResolvedMessgae(ex.getMessage(),"error.resource.notfound.msg",ex);
		LOGGER.error(resolvedMessage);

		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details), HttpStatus.NOT_FOUND);
	}

	/*
	 * Handles if there is any authentication exception occurs
	 * */
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {

		String resolvedMessage =getResolvedMessgae("","error.authenticate.msg",ex);
		LOGGER.error(resolvedMessage);
		
		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details), HttpStatus.UNAUTHORIZED);
	}
	
	
	
	
	@ExceptionHandler({SubscriberException.class,ConstraintViolationException.class})
	public final ResponseEntity<Object> handleSubscriberException(SubscriberException ex, WebRequest request) {

		String resolvedMessage =getResolvedMessgae("","error.subscriber.username.exists",ex);
		LOGGER.error(resolvedMessage);
		
		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details), HttpStatus.CONFLICT);
	}
	
	

	/*
	 * Handles the Method argument validations 
	 * */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		
		String resolvedMessage =getResolvedMessgae("","error.request.notvalid.msg",ex);
		LOGGER.error(resolvedMessage);
		
		List<String> details = new ArrayList<>();
	    ex.getBindingResult().getAllErrors().forEach(error -> {
	        details.add(((FieldError) error).getField()+"="+error.getDefaultMessage());
	    });
		return new ResponseEntity(ErrorResponse.getErrorResponse(resolvedMessage, details), HttpStatus.BAD_REQUEST);
	}

	
	/*
	 * Handles generate auth token exception
	 * */
	protected ResponseEntity<Object> handleAuthTokenGenerateException(AuthTokenGenerateException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String resolvedMessage =getResolvedMessgae(ex.getMessage(),"error.auth.token.generate.msg",ex);
		LOGGER.error(resolvedMessage);
		
		List<String> details = new ArrayList<>();
		details.add(request.getDescription(false));
		
		return new ResponseEntity<>(ErrorResponse.getErrorResponse(resolvedMessage,details), HttpStatus.UNAUTHORIZED);
	}
	
	
	private String getResolvedMessgae(String message, String messageKey,Exception ex) {
		return ("".equals(message) || null==message)?environment.getProperty(messageKey):ex.getMessage();
	}
	
	
	
	
	
}
