package com.ndobriukha.curriculumviewer;

import java.io.File;
import java.util.HashMap;

import com.ndobriukha.curriculumviewer.models.Course;
import com.ndobriukha.curriculumviewer.models.Program;
import com.ndobriukha.curriculumviewer.models.Student;
import com.ndobriukha.curriculumviewer.models.Task;

public class Context {

	private File xmlFile;
	private HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
	private HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
	private HashMap<Integer, Program> programs = new HashMap<Integer, Program>();
	private HashMap<Integer, Student> students = new HashMap<Integer, Student>();

	public File getXmlFile() {
		return xmlFile;
	}
	
	public void setXmlFile(File file) {
		if (file.exists() && file.isFile()) xmlFile = file;
	}
	
	public HashMap<Integer, Task> getTasks() {
		return tasks;
	}

	public HashMap<Integer, Course> getCourses() {
		return courses;
	}

	public HashMap<Integer, Program> getPrograms() {
		return programs;
	}

	public HashMap<Integer, Student> getStudents() {
		return students;
	}

}
