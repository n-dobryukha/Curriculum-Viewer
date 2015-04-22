package com.ndobriukha.curriculumviewer.models;

import java.util.Date;
import java.util.HashMap;

public class Course extends BaseWithAuthorAndDate {

	private HashMap<Integer, Task> tasks;

	public Course(int id, String name, String author, Date creationDate) {
		super(id, name, author, creationDate);
		tasks = new HashMap<Integer, Task>();
	}

	public HashMap<Integer, Task> getTasks() {
		return tasks;
	}
	
	@Override
	public String getData() {
		StringBuilder result = new StringBuilder();
		result.append("Title: " + getName() + "\n");
		return result.toString();
	}
}
