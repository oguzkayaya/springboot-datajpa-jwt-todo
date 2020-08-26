package com.ok.todojwtjpademo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.services.TodoService;
import com.ok.todojwtjpademo.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTodos(HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		return ResponseEntity.ok(todoService.getAllTodos(logonUser));

	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneTodo(@PathVariable int id, HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		Todo todo = todoService.getOneTodo(id, logonUser);

		if (todo == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(todo);

		return ResponseEntity.ok(todoService.getOneTodo(id, logonUser));

	}

	@RequestMapping(value = "/todos", method = RequestMethod.POST)
	public ResponseEntity<?> AddTodo(@RequestBody Todo newTodo, HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		Todo addedTodo = todoService.addTodo(newTodo, logonUser);

		if (addedTodo == null)
			return null;

		return ResponseEntity.status(HttpStatus.CREATED).body(addedTodo);

	}

	@RequestMapping(value = "todos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> DeleteTodo(@PathVariable int id, HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		Todo deletedTodo = todoService.deleteTodo(id, logonUser);

		if (deletedTodo.getId() == -1) {

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("There is no such todo");

		} else if (deletedTodo.getId() == -2) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not able to delete it");

		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedTodo);
	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> UpdateTodo(@RequestBody Todo newTodo, HttpServletRequest request, @PathVariable int id) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);
		
		Todo updatedTodo = todoService.updateTodo(id, newTodo, logonUser);
		
		if(updatedTodo == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedTodo);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoService.updateTodo(id, newTodo, logonUser));

	}
}
