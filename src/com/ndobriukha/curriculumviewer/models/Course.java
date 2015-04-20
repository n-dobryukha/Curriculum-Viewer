package com.ndobriukha.curriculumviewer.models;

import java.util.Date;
import java.util.HashMap;

public class Course extends BaseWithDate {

	private HashMap<Integer, Task> theoreticalTasks;
	private HashMap<Integer, Task> practicalTasks;

	public Course(int id, String name, Date creationDate) {
		super(id, name, creationDate);
		theoreticalTasks = new HashMap<Integer, Task>();
		practicalTasks = new HashMap<Integer, Task>();
	}

	public HashMap<Integer, Task> getTheoreticalTasks() {
		return theoreticalTasks;
	}

	public HashMap<Integer, Task> getPracticalTasks() {
		return practicalTasks;
	}
}
