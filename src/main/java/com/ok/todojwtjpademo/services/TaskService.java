package com.ok.todojwtjpademo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok.todojwtjpademo.models.Task;
import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.repositories.TaskRepository;
import com.ok.todojwtjpademo.repositories.TodoRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TodoRepository todoRepository;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();

	public List<Task> GetTasks(int todoId, String logonUser) {

		List<Task> taskList = taskRepository.findByTaskTodoId(todoId);

		if (taskList.get(0).getTaskTodo().getTodoUser().getUsername().equals(logonUser)) {
			return taskList;
		}

		return null;

	}

	public Task AddTaskToTodo(int todoId, Task task, String logonUser) {

		Todo todo = todoRepository.findById(todoId).get();

		Task addedTask = new Task();
		addedTask.setTaskTodo(todo);

		addedTask.setTaskName(task.getTaskName());
		addedTask.setDone(task.isDone());
		addedTask.setDueDate(task.getDueDate());

		if (todo.getTodoUser().getUsername().equals(logonUser)) {

			return taskRepository.save(addedTask);

		}

		return null;

	}

	public Task DeleteTask(int id, String logonUser) {

		Task deletedTask = taskRepository.findById(id).get();

		if (deletedTask.getTaskTodo().getTodoUser().getUsername().equals(logonUser)) {

			taskRepository.delete(deletedTask);

			return deletedTask;
		}

		return null;
	}

	public Task UpdateTask(int taskId, Task newTask, String logonUser) {

		Task updatingTask = taskRepository.findById(taskId).get();

		if (updatingTask.getTaskTodo().getTodoUser().getUsername().equals(logonUser)) {

			updatingTask.setDone(newTask.isDone());
			updatingTask.setDueDate(newTask.getDueDate());
			updatingTask.setTaskName(newTask.getTaskName());

			return taskRepository.save(updatingTask);

		}

		return null;
	}

}
