package com.ok.todojwtjpademo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ok.todojwtjpademo.models.Task;
import com.ok.todojwtjpademo.services.TaskService;
import com.ok.todojwtjpademo.util.JwtUtil;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/todos/{id}/tasks", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTasks(@PathVariable(name = "id") int todoId, HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		return ResponseEntity.ok(taskService.GetTasks(todoId, logonUser));

	}

	@RequestMapping(value = "/todos/{id}/tasks", method = RequestMethod.POST)
	public ResponseEntity<?> addTaskToTodo(@PathVariable(name = "id") int todoId, @RequestBody Task task,
			HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		return ResponseEntity.ok(taskService.AddTaskToTodo(todoId, task, logonUser));

	}

	@RequestMapping(value = "/todos/{todoId}/tasks/{taskId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable(name = "taskId") int taskId, HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		return ResponseEntity.ok(taskService.DeleteTask(taskId, logonUser));

	}

	@RequestMapping(value = "/todos/{todoId}/tasks/{taskId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTask(@PathVariable(name = "taskId") int taskId, @RequestBody Task updatedTask,
			HttpServletRequest request) {

		String logonUser = jwtUtil.extractUsernameFromRequest(request);

		return ResponseEntity.ok(taskService.UpdateTask(taskId, updatedTask, logonUser));

	}

}
