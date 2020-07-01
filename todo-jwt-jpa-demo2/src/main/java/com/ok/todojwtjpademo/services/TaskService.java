package com.ok.todojwtjpademo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import com.ok.todojwtjpademo.models.Task;
import com.ok.todojwtjpademo.models.Todo;

@Service
public class TaskService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();

	public List<Task> GetTasks(int id, String logonUser) {

		List<Task> taskList = em
				.createQuery("select c from Task c where taskTodo.id = :id and taskTodo.todoUser.username = :logonUser")
				.setParameter("id", id).setParameter("logonUser", logonUser).getResultList();

		return taskList;

	}

	public Task AddTaskToTodo(int todoId, Task task, String logonUser) {

		Todo todo = em.find(Todo.class, todoId);

		Task addedTask = new Task();
		addedTask.setTaskTodo(todo);

		addedTask.setTaskName(task.getTaskName());
		addedTask.setDone(task.isDone());
		addedTask.setDueDate(task.getDueDate());

		if (todo.getTodoUser().getUsername().equals(logonUser)) {

			task.setTaskTodo(todo);

			em.getTransaction().begin();
			em.persist(addedTask);
			em.getTransaction().commit();

			return addedTask;

		}

		return null;

	}

	public Task DeleteTask(int id, String logonUser) {

		Task deletedTask = em.find(Task.class, id);

		if (deletedTask.getTaskTodo().getTodoUser().getUsername().equals(logonUser)) {

			em.getTransaction().begin();
			em.remove(deletedTask);
			em.getTransaction().commit();

			return deletedTask;
		}

		return null;
	}

	public Task UpdateTask(int taskId, Task newTask, String logonUser) {

		Task updatingTask = em.find(Task.class, taskId);

		if (updatingTask.getTaskTodo().getTodoUser().getUsername().equals(logonUser)) {
			
			updatingTask.setDone(newTask.isDone());
			updatingTask.setDueDate(newTask.getDueDate());
			updatingTask.setTaskName(newTask.getTaskName());

			em.getTransaction().begin();
			em.merge(updatingTask);
			em.getTransaction().commit();

			return updatingTask;
		}

		return null;
	}

}
