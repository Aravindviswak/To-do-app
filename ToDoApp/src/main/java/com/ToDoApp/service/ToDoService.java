package com.ToDoApp.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoApp.dao.ToDoDAO;
import com.ToDoApp.entity.ToDoEntity;
import com.ToDoApp.exception.TodoNotFound;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

import com.ToDoApp.exception.NoSuchElementException;

@Service
public class ToDoService implements ToDoServiceInterface {

	@Autowired
	private ToDoDAO repository;
	
	
	@Override
	public ToDoEntity saveTodoDetails(ToDoEntity entity) {
		
		return repository.save(entity);
	}

	@Override
	public List<ToDoEntity> getAllToDoDetails() {
		return repository.findAll();
	}

	@Override
	public ToDoEntity getTodoById(Long id) throws NoSuchElementException {
		// TODO Auto-generated method stub
		ToDoEntity data=repository.findById(id).orElse(null);
		if(data!=null) {
			return data;
		}
		else {
			throw new NoSuchElementException("Todo item not found with the ID  "+id);
		}
		
	}

	@Override
	public ToDoEntity updateToDoDetails(Long id, ToDoEntity entity) throws NoSuchElementException {
		ToDoEntity data=repository.findById(id).orElse(null);
		if(data!=null) {
			data.setTitle(entity.getTitle());
			data.setDueDate(entity.getDueDate());
			data.setDescription(entity.getDescription());
			data.setComplete(entity.isComplete());
			
			return repository.save(data);
		}
		else {
			throw new NoSuchElementException("Todo item not found with the ID  "+id);
		}
	
	}

	@Override
	public void DeleteToDoById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<ToDoEntity> getCompletedToDolist() throws TodoNotFound {
		//Method is created inside DAO.
		List<ToDoEntity> completedTasks = repository.findByComplete(true);
	    if(completedTasks.isEmpty()) {
	        throw new TodoNotFound("No Completed Todo Items");
	    }
	    return completedTasks;
		

	}
	
	@Override
	public List<ToDoEntity> getUnCompletedTodos() throws TodoNotFound {
		//Method is created inside DAO.
		List<ToDoEntity> completedTasks = repository.findByComplete(false);
	    if(completedTasks.isEmpty()) {
	        throw new TodoNotFound("No Completed Todo Items");
	    }
	    return completedTasks;

		
	}

	@Override
	public ToDoEntity changeComplete(Long id) throws NoSuchElementException {
		ToDoEntity entity= new ToDoEntity() ;
		Set<ConstraintViolation<ToDoEntity>> violations = Validation
	                .buildDefaultValidatorFactory()
	                .getValidator()
	                .validate(entity);

	        if (!violations.isEmpty()) {
	            // Handle validation errors here
	            throw new ConstraintViolationException(violations);
	        }
	        if (entity.getDueDate() == null || entity.getDescription() == null || entity.getTitle() == null) {
	            throw new IllegalArgumentException("Due date, description, and title are required fields.");
	        }
		Optional<ToDoEntity> data=repository.findById(id);
		if(data.isPresent()) {
			ToDoEntity details=data.get();
			details.setTitle(details.getTitle());
			details.setDueDate(details.getDueDate());
			details.setDescription(details.getDescription());

			details.setComplete(!details.isComplete());
			return repository.save(details);
		}
		else {
			throw new NoSuchElementException("Todo item not found with the ID  "+id);
		}
		
	}


	

}
