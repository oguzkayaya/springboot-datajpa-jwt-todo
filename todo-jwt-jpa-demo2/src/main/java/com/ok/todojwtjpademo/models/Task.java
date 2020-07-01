package com.ok.todojwtjpademo.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "task_list")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "todo_id", referencedColumnName = "id")
	private Todo taskTodo;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "done")
	private boolean done;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Todo getTaskTodo() {
		return taskTodo;
	}

	public void setTaskTodo(Todo taskTodo) {
		this.taskTodo = taskTodo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Task(int id, Todo taskTodo, String taskName, Date dueDate, boolean done) {
		super();
		this.id = id;
		this.taskTodo = taskTodo;
		this.taskName = taskName;
		this.dueDate = dueDate;
		this.done = done;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskTodo=" + taskTodo + ", taskName=" + taskName + ", dueDate=" + dueDate
				+ ", done=" + done + "]";
	}

}
