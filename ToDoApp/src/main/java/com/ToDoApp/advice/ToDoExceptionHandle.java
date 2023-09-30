package com.ToDoApp.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ToDoApp.exception.DetailsNotFound;
import com.ToDoApp.exception.NoSuchElementException;

@RestControllerAdvice
public class ToDoExceptionHandle {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public Map<String, String>ToDoNotFoundException(NoSuchElementException ex){
		Map<String, String> errorMap=new HashMap<String, String>();
		
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
	
	@ExceptionHandler(DetailsNotFound.class)
	public Map<String, String> handleDetailsNotFound(DetailsNotFound ex){
		Map<String, String> errorMap=new HashMap<String, String>();
		errorMap.put("Output", ex.getMessage());
		
		return errorMap;
	}
}
