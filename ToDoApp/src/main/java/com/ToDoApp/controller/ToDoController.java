package com.ToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoApp.entity.ToDoEntity;
import com.ToDoApp.exception.DetailsNotFound;
import com.ToDoApp.exception.NoSuchElementException;
import com.ToDoApp.service.ToDoServiceInterface;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.List;


@RestController
@RequestMapping("/todoapp")
public class ToDoController {
	
	@Autowired
	private ToDoServiceInterface service;
	
	@PostMapping("/savedetails")
	public ResponseEntity<ToDoEntity> saveTodoDetails( @Valid @RequestBody ToDoEntity entity){
		return new ResponseEntity<ToDoEntity>(service.saveTodoDetails(entity),HttpStatus.CREATED);
	}
	
	@GetMapping("/getalldetails")
	public List<ToDoEntity>getAllToDoDetails(){
		return service.getAllToDoDetails();
	}
	
	@GetMapping("/gettodo/{id}")
	public ToDoEntity getTodoById(@PathVariable Long id) throws NoSuchElementException {
		return service.getTodoById(id);
	}
	
	@PutMapping("/updatetodo/{id}")
	public ToDoEntity updateToDoDetails(@PathVariable Long id, @RequestBody ToDoEntity entity) throws NoSuchElementException {
		return service.updateToDoDetails(id,entity);
	}
	
	@DeleteMapping("/deletetodo/{id}")
	public String DeleteToDoById(@PathVariable Long id) {
		service.DeleteToDoById(id);
		
		return "To do item deleted sucessfully";
	}
	
	@GetMapping("/completedtodolist")
	public List<ToDoEntity>getCompletedToDolist() throws DetailsNotFound{
		return service.getCompletedToDolist();
	}
	
	@GetMapping("/pendingTodo")
	public List<ToDoEntity> pendingToDoList() throws DetailsNotFound{
		return service.pendingToDoList();
	}
	
}
