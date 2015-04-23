package com.ndobriukha.curriculumviewer.models;

import com.ndobriukha.curriculumviewer.models.enums.TaskStatus;
/**
 * 
 * @author Nikita_Dobriukha
 * Модель задачи в отчете.
 * Расширяет класс Task
 */
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

	@Override
	public String getData() {
		StringBuilder result = new StringBuilder(super.getData());
		result.append("Ststus: " + getStatus() + "\n");
		if (getRate() != 0) result.append("Rate: " + getRate() + "\n");
		return result.toString();
	}
	
	

}
