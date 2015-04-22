package com.ndobriukha.curriculumviewer.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends Base {

	private String city;
	private String email;
	private Date startDate;
	private boolean isSigned;
	private Program program;

	public Student(int id, String name, String city, String email, Date startDate, boolean isSigned, Program program) {
		super(id, name);
		this.city = city;
		this.email = email;
		this.startDate = startDate;
		this.isSigned = isSigned;
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

	public Date getStartDate() {
		return startDate;
	}

	public boolean isSigned() {
		return isSigned;
	}

	@Override
	public String getData() {
		StringBuilder result = new StringBuilder();
		result.append("FullName: " + getName() + "\n");
		result.append("City: " + getCity() + "\n");
		result.append("Email: " + getEmail() + "\n");
		result.append("Contract signed: " + isSigned() + "\n");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		result.append("Start date: " + dateFormat.format(getStartDate()) + "\n");
		return result.toString();
	}
	
}
