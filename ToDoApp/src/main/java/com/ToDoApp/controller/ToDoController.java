package com.ToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoApp.entity.ToDoEntity;
import com.ToDoApp.exception.TodoNotFound;
import com.ToDoApp.exception.NoSuchElementException;
import com.ToDoApp.service.ToDoServiceInterface;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
@CrossOrigin("http://localhost:4200")
public class ToDoController {
	
	@Autowired
	private ToDoServiceInterface service;
	

//	@PostMapping("/savedetails")
//	public ResponseEntity<ToDoEntity> saveTodoDetails( @Valid @RequestBody ToDoEntity entity){
//		return new ResponseEntity<ToDoEntity>(service.saveTodoDetails(entity),HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/getalldetails")
//	public List<ToDoEntity>getAllToDoDetails(){
//		return service.getAllToDoDetails();
//	}
//	
//
//	
//	@PutMapping("/updatetodo/{id}")
//	public ToDoEntity updateToDoDetails(@PathVariable Long id, @RequestBody ToDoEntity entity) throws NoSuchElementException {
//		return service.updateToDoDetails(id,entity);
//	}
//	
//	@DeleteMapping("/deletetodo/{id}")
//	public String DeleteToDoById(@PathVariable Long id) {
//		service.DeleteToDoById(id);
//		
//		return "To do item deleted sucessfully";
//	}
//	
	@GetMapping("/completedtodolist")
	public List<ToDoEntity>getCompletedToDolist() throws TodoNotFound{
		return service.getCompletedToDolist();
	}
	

	@PostMapping
    public ResponseEntity<ToDoEntity> createTodo(@Valid @RequestBody ToDoEntity entity){
        return new ResponseEntity<>(service.saveTodoDetails(entity), HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<ToDoEntity> getAllTodos() {
        return service.getAllToDoDetails();
    }
    
    @GetMapping("/{id}")
    public ToDoEntity getTodoById(@PathVariable Long id) throws NoSuchElementException {
        return service.getTodoById(id);
    }
    
    @PutMapping("/{id}")
    public ToDoEntity updateTodo(@PathVariable Long id, @RequestBody ToDoEntity entity) throws NoSuchElementException {
        return service.updateToDoDetails(id, entity);
    }
    
    @PutMapping("todoComplete/{id}")
    public ToDoEntity changeComplete( @PathVariable Long id) throws NoSuchElementException{
    	return service.changeComplete(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
        service.DeleteToDoById(id);
        return ResponseEntity.ok("To do item deleted successfully");
    }
    
    @GetMapping("/completed")
    public List<ToDoEntity> getCompletedTodos() throws TodoNotFound{
        return service.getCompletedToDolist();
    }
    
    @GetMapping("/unCompleted")
    public List<ToDoEntity> getUnCompletedTodos() throws TodoNotFound{
        return service.getUnCompletedTodos();
    }


}
