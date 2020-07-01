package com.ok.todojwtjpademo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User todoUser;

	@Column(name = "description")
	private String description;

	@JsonManagedReference
	@OneToMany(mappedBy = "taskTodo", orphanRemoval = true)
	private List<Task> tasks;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getTodoUser() {
		return todoUser;
	}

	public void setTodoUser(User todoUser) {
		this.todoUser = todoUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Todo(int id, User todoUser, String description) {
		super();
		this.id = id;
		this.todoUser = todoUser;
		this.description = description;
	}

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", todoUser=" + todoUser + ", description=" + description + "]";
	}

}
