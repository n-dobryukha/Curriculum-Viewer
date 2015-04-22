package com.ndobriukha.curriculumviewer.models;

import com.ndobriukha.curriculumviewer.models.enums.TaskStatus;

public class TaskForReport extends Task {
	
	private int rate = 0;
	private TaskStatus status;

	public TaskForReport(Task task, TaskStatus status) {
		super(task.getId(), task.getName(), task.getDuration(), task.getType());
		this.status = status;
	}
	
	public TaskForReport(Task task, TaskStatus status, int rate) {
		this(task, status);
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}

	public TaskStatus getStatus() {
		return status;
	}

}
