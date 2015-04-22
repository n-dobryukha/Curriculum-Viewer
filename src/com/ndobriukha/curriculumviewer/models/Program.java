package com.ndobriukha.curriculumviewer.models;

import java.util.Date;
import java.util.HashMap;

public class Program extends BaseWithAuthorAndDate {

	private HashMap<Integer, Course> courses;

	public Program(int id, String name, String author, Date creationDate) {
		super(id, name, author, creationDate);
		courses = new HashMap<Integer, Course>();
	}

	public HashMap<Integer, Course> getCourses() {
		return courses;
	}

	@Override
	public String getData() {
		StringBuilder result = new StringBuilder();
		result.append("Title: " + getName() + "\n");
		return result.toString();
	}
}
