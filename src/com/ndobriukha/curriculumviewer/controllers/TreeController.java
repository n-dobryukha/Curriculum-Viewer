package com.ndobriukha.curriculumviewer.controllers;

import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.ndobriukha.curriculumviewer.Context;
import com.ndobriukha.curriculumviewer.models.Course;
import com.ndobriukha.curriculumviewer.models.Program;
import com.ndobriukha.curriculumviewer.models.Student;
import com.ndobriukha.curriculumviewer.models.Task;

public class TreeController {

	Context context;
	
	public TreeController(Context context) {
		this.context = context;
	}
	
	public void BuildTree() {
		
		DefaultTreeModel treeModel = (DefaultTreeModel)context.getTree().getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
		root.removeAllChildren();
		
		for (Entry<Integer, Student> entryStudent: context.getStudents().entrySet()) {
			Student student = entryStudent.getValue();			
			DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode(student);
			root.add(studentNode);
			Program program = student.getProgram();
			DefaultMutableTreeNode programNode = new DefaultMutableTreeNode(program);
			studentNode.add(programNode);
			for (Entry<Integer, Course> entryCourse: program.getCourses().entrySet()) {
				Course course = entryCourse.getValue();
				DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(course);
				programNode.add(courseNode);
				for (Entry<Integer, Task> entryTask: course.getTasks().entrySet()) {
					Task task = entryTask.getValue();
					DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode(task);
					courseNode.add(taskNode);					
				}
			}
		}		
		treeModel.reload(root);
	}
}
