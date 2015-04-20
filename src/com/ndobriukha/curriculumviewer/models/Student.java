package com.ndobriukha.curriculumviewer.models;

public class Student extends Base {

	private String city;
	private String email;
	private Program program;

	public Student(int id, String name, String city, String email, Program program) {
		super(id, name);
		this.city = city;
		this.email = email;
		this.program = program;
	}

	public String getCity() {
		return city;
	}

	public String getEmail() {
		return email;
	}

	public Program getProgram() {
		return program;
	}

}
