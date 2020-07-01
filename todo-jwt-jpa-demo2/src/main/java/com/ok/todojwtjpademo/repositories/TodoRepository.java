package com.ok.todojwtjpademo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.models.User;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

	public List<Todo> findByTodoUserUsername(String user);
	
	
	
	
	

	
}
