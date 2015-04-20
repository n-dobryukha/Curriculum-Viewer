package com.ndobriukha.curriculumviewer.models;

import java.util.Date;

abstract public class BaseWithDate extends Base {

	private Date creationDate;

	public BaseWithDate(int id, String name, Date creationDate) {
		super(id, name);
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
