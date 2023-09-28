package com.ToDoApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDoApp.entity.ToDoEntity;
@Repository
public interface ToDoDAO extends JpaRepository<ToDoEntity, Long>{

}
