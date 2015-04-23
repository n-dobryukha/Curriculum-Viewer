package com.ndobriukha.curriculumviewer.controllers;

import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.ndobriukha.curriculumviewer.Context;
import com.ndobriukha.curriculumviewer.models.Course;
import com.ndobriukha.curriculumviewer.models.Program;
import com.ndobriukha.curriculumviewer.models.Report;
import com.ndobriukha.curriculumviewer.models.Student;
import com.ndobriukha.curriculumviewer.models.Task;
import com.ndobriukha.curriculumviewer.models.TaskForReport;
/**
 * 
 * @author Nikita_Dobriukha
 * ���������� ������ � JTree-�������
 */
public class TreeController {

	Context context;
	
	public TreeController(Context context) {
		this.context = context;
	}
	
	/**
	 * ������ JTree-������ �� ���������� � ��������� ������
	 */
	public void BuildTree() {
		
		DefaultTreeModel treeModel = (DefaultTreeModel)context.getTree().getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
		root.removeAllChildren();
		
		for (Entry<Integer, Student> entryStudent: context.getStudents().entrySet()) {
			Student student = entryStudent.getValue();	
			int studentId = student.getId();			
			Report report = context.getReports().get(studentId);			
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
					int taskId = task.getId();
					DefaultMutableTreeNode taskNode;
					if (report != null) {
						TaskForReport taskReport = report.getTasks().get(taskId);
						taskNode = new DefaultMutableTreeNode(taskReport);
					}
					else {
						taskNode = new DefaultMutableTreeNode(task);
					}
					courseNode.add(taskNode);					
				}
			}
		}		
		treeModel.reload(root);
	}
}
