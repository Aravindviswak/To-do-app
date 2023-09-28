package com.ToDoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoApp.dao.ToDoDAO;
import com.ToDoApp.entity.ToDoEntity;
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
		// TODO Auto-generated method stub
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


	

}