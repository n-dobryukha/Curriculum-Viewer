package com.ndobriukha.curriculumviewer.models;

public class Task extends Base {

	private int duration;

	public Task(int id, String name, int duration) {
		super(id, name);
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

}
