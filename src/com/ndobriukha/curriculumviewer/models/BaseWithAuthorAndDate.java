package com.ndobriukha.curriculumviewer.models;

import java.util.Date;
/**
 * 
 * @author Nikita_Dobriukha
 * Расширение базового класса для курсов и программ
 * Содержит "author" и "creationDate"
 */
abstract public class BaseWithAuthorAndDate extends Base {

	private String author;
	private Date creationDate;

	public BaseWithAuthorAndDate(int id, String name, String author, Date creationDate) {
		super(id, name);
		this.author = author;
		this.creationDate = creationDate;
	}
	
	public String getAuthor() {
		return author;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
