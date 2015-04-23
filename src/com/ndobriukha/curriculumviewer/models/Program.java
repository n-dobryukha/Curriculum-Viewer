package com.ndobriukha.curriculumviewer.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
/**
 * 
 * @author Nikita_Dobriukha
 * Модель учебной программы
 */
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
		result.append("Author: " + getAuthor() + "\n");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		result.append("Created: " + dateFormat.format(getCreationDate()) + "\n");
		result.append("Duration (hrs): " + getCoursesDuration() + "\n");
		result.append(getCoursesList());
		return result.toString();
	}
	
	public int getCoursesDuration() {
		int result = 0;
		for (Entry<Integer, Course> entryCourse: courses.entrySet()) {
			result += entryCourse.getValue().getTasksDuration();			
		}
		return result;
	}
	
	public StringBuilder getCoursesList() {
		StringBuilder result = new StringBuilder("\nCourses:\n");
		for (Entry<Integer, Course> entryCourse: courses.entrySet()) {
			result.append(entryCourse.getValue().getName() + "\n");			
		}
		return result;
	}
}
