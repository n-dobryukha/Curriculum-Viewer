package com.ndobriukha.curriculumviewer;

import java.io.File;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.ndobriukha.curriculumviewer.controllers.ImportController;
import com.ndobriukha.curriculumviewer.controllers.TreeController;
import com.ndobriukha.curriculumviewer.models.Base;
import com.ndobriukha.curriculumviewer.models.Course;
import com.ndobriukha.curriculumviewer.models.Program;
import com.ndobriukha.curriculumviewer.models.Report;
import com.ndobriukha.curriculumviewer.models.Student;
import com.ndobriukha.curriculumviewer.models.Task;

public class Context {

	private File xmlFile;
	private HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
	private HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
	private HashMap<Integer, Program> programs = new HashMap<Integer, Program>();
	private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
	private HashMap<Integer, Report> reports = new HashMap<Integer, Report>();
	private JTree tree = new JTree(new DefaultMutableTreeNode("Students"));
	private JTextArea txetDetail;
	

	private ImportController importController = new ImportController(this);
	private TreeController treeController = new TreeController(this);
	
	public Context(JTextArea textDetail) {
		this.txetDetail = textDetail;
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if ((node !=null) && (!node.isRoot()))
					getTextDetail().setText(((Base) node.getUserObject()).getData());
				else
					getTextDetail().setText(null);
			}
		});
		
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}
	
	public File getXmlFile() {
		return xmlFile;
	}
	
	public void setXmlFile(File file) {
		if (file.exists() && file.isFile()) xmlFile = file;
	}
	
	public HashMap<Integer, Task> getTasks() {
		return tasks;
	}

	public HashMap<Integer, Course> getCourses() {
		return courses;
	}

	public HashMap<Integer, Program> getPrograms() {
		return programs;
	}

	public HashMap<Integer, Student> getStudents() {
		return students;
	}
	
	public HashMap<Integer, Report> getReports() {
		return reports;
	}
	
	public JTree getTree() {
		return tree;
	}
	
	public JTextArea getTextDetail() {
		return txetDetail;
	}

	public ImportController getImportController() {
		return importController;
	}
	
	public TreeController getTreeController() {
		return treeController;
	}
	
	
	
}
