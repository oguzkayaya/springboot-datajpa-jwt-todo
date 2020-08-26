package com.ok.todojwtjpademo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok.todojwtjpademo.models.Task;
import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.models.User;
import com.ok.todojwtjpademo.repositories.TodoRepository;
import com.ok.todojwtjpademo.repositories.UserRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Todo> getAllTodos(String logonUser) {

		User user = userRepository.findByUsername(logonUser);
		List<Todo> todoList = user.getTodos();

		return todoList;
	}

	public Todo getOneTodo(int id, String logonUser) {

		Todo todo = todoRepository.findById(id).get();

		if (todo.getTodoUser().getUsername().equals(logonUser))
			return todo;

		return null;
	}

	public Todo addTodo(Todo newTodo, String logonUser) {

		User user = userRepository.findByUsername(logonUser);

		newTodo.setTodoUser(user);

		Todo addedTodo = todoRepository.save(newTodo);

		return addedTodo;
	}

	public Todo deleteTodo(int todoId, String logonUser) {

		Todo deletingTodo = todoRepository.findById(todoId).get();

		if (deletingTodo.getTodoUser().getUsername().equals(logonUser)) {
			todoRepository.delete(deletingTodo);
			return deletingTodo;
		}
		return null;

	}

	public Todo updateTodo(int todoId, Todo todo, String logonUser) {

		Todo updatingTodo = todoRepository.findById(todoId).get();

		updatingTodo.setDescription(todo.getDescription());

		return todoRepository.save(updatingTodo);


	}

}
