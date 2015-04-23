package com.ndobriukha.curriculumviewer.models;

import java.util.HashMap;
/**
 * 
 * @author Nikita_Dobriukha
 * Модель отчета по студенту и выполненным им задачам
 */
public class Report {

	private Student student;
	private HashMap<Integer, TaskForReport> tasks = new HashMap<Integer, TaskForReport>();
	
	public Report(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public HashMap<Integer, TaskForReport> getTasks() {
		return tasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
}
