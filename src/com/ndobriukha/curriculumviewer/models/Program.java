package com.ndobriukha.curriculumviewer.models;

import java.util.Date;
import java.util.HashMap;

public class Program extends BaseWithDate {

	private HashMap<Integer, Course> courses;

	public Program(int id, String name, Date creationDate) {
		super(id, name, creationDate);
		courses = new HashMap<Integer, Course>();
	}

	public HashMap<Integer, Course> getCourses() {
		return courses;
	}

}
