package com.ndobriukha.curriculumviewer.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
/**
 * 
 * @author Nikita_Dobriukha
 * Модель учебного курса
 */
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
		result.append("Author: " + getAuthor() + "\n");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		result.append("Created: " + dateFormat.format(getCreationDate()) + "\n");
		result.append("Duration (hrs): " + getTasksDuration() + "\n");
		result.append(getTasksList());
		return result.toString();
	}
	
	public int getTasksDuration() {
		int result = 0;
		for (Entry<Integer, Task> entryTask: tasks.entrySet()) {
			result += entryTask.getValue().getDuration();			
		}
		return result;
	}
	
	public StringBuilder getTasksList() {
		StringBuilder result = new StringBuilder("\nTasks:\n");
		for (Entry<Integer, Task> entryTask: tasks.entrySet()) {
			result.append(entryTask.getValue().getName() + "\n");			
		}
		return result;
	}
}
