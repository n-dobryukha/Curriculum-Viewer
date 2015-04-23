package com.ndobriukha.curriculumviewer.models;

import com.ndobriukha.curriculumviewer.models.enums.TaskType;
/**
 * 
 * @author Nikita_Dobriukha
 * Модель учебной задачи
 */
public class Task extends Base {

	private TaskType type;
	private int duration;

	public Task(int id, String name, int duration, TaskType type) {
		super(id, name);
		this.duration = duration;
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public TaskType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (type != other.type)
			return false;
		return true;
	}
	
	@Override
	public String getData() {
		StringBuilder result = new StringBuilder();
		result.append("Title: " + getName() + "\n");
		result.append("Duration (hrs): " + getDuration() + "\n");
		result.append("Type: " + getType() + "\n");
		return result.toString();
	}

}
