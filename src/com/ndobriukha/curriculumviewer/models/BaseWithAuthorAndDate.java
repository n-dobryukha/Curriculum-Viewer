package com.ndobriukha.curriculumviewer.models;

import java.util.Date;

abstract public class BaseWithAuthorAndDate extends Base {

	private String author;
	private Date creationDate;

	public BaseWithAuthorAndDate(int id, String name, String author, Date creationDate) {
		super(id, name);
		this.author = author;
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
