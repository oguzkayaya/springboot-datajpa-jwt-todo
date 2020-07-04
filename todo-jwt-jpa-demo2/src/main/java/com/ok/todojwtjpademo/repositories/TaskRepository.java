package com.ok.todojwtjpademo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ok.todojwtjpademo.models.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>
{

	public List<Task> findByTaskTodoId(int todoId);
	
}
