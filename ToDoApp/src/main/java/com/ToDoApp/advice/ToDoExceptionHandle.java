package com.ToDoApp.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ToDoApp.exception.TodoNotFound;
import com.ToDoApp.dto.ErrorResponse;
import com.ToDoApp.exception.NoSuchElementException;

@RestControllerAdvice
public class ToDoExceptionHandle {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> ToDoNotFoundException(NoSuchElementException ex) {
		
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Message", ex.getMessage());
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ErrorResponse errorResponse = ErrorResponse.builder()
				.displayMessage(ex.getLocalizedMessage())
				.timestamp(LocalDateTime.now())
				.status(status.value())
				.error(status.getReasonPhrase())
				.errorMessages(errorMap)
				.build();
		
		return new ResponseEntity<ErrorResponse>(errorResponse, status);
	}

	@ExceptionHandler(TodoNotFound.class)
	public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFound ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		HashMap<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Message", ex.getLocalizedMessage());
		ErrorResponse errorResponse = ErrorResponse.builder()
										.displayMessage(ex.getLocalizedMessage())
										.timestamp(LocalDateTime.now())
										.status(status.value())
										.error(status.getReasonPhrase())
										.errorMessages(errorMap)
										.build();

		return new ResponseEntity<ErrorResponse>(errorResponse, status);
	}
}
