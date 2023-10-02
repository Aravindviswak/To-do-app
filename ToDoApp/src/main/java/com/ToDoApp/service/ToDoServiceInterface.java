package com.ToDoApp.service;

import java.util.List;

import com.ToDoApp.entity.ToDoEntity;
import com.ToDoApp.exception.TodoNotFound;
import com.ToDoApp.exception.NoSuchElementException;

public interface ToDoServiceInterface {

	ToDoEntity saveTodoDetails(ToDoEntity entity);

	List<ToDoEntity> getAllToDoDetails();

	ToDoEntity getTodoById(Long id) throws NoSuchElementException;

	ToDoEntity updateToDoDetails(Long id,ToDoEntity entity) throws NoSuchElementException;

	void DeleteToDoById(Long id);

	List<ToDoEntity> getCompletedToDolist() throws TodoNotFound;

	List<ToDoEntity> getUnCompletedTodos() throws TodoNotFound;

	

}
