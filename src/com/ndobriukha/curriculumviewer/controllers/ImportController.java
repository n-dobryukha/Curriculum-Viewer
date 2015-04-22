package com.ndobriukha.curriculumviewer.controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ndobriukha.curriculumviewer.Context;
import com.ndobriukha.curriculumviewer.models.Course;
import com.ndobriukha.curriculumviewer.models.Program;
import com.ndobriukha.curriculumviewer.models.Report;
import com.ndobriukha.curriculumviewer.models.Student;
import com.ndobriukha.curriculumviewer.models.Task;
import com.ndobriukha.curriculumviewer.models.TaskForReport;
import com.ndobriukha.curriculumviewer.models.enums.TaskStatus;
import com.ndobriukha.curriculumviewer.models.enums.TaskType;

public class ImportController {
	
	private final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	
	private Context context;
	
	public ImportController(Context context) {
		this.context = context;
	}
	
	public void ImportXmlFile() {
		File xmlFile = context.getXmlFile();
		if (xmlFile.isFile() && xmlFile.canRead()) {
            try {
            	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            	
            	dbf.setNamespaceAware(true);
            	dbf.setValidating(true);
            	dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
            	
            	
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(xmlFile);
                doc.getDocumentElement().normalize();
                
                NodeList reportNodeList = doc.getDocumentElement().getChildNodes();
                for (int i = 0; i < reportNodeList.getLength(); i++) {
                	Node reportNode = reportNodeList.item(i);
                	if (reportNode.getNodeType() == Node.ELEMENT_NODE) {
                		switch (reportNode.getNodeName()) {
						case "Tasks":
							if (reportNode.hasChildNodes()) FillTasks(reportNode.getChildNodes());
							break;
						case "Courses":
							if (reportNode.hasChildNodes()) FillCourses(reportNode.getChildNodes());
							break;
						case "Programs":
							if (reportNode.hasChildNodes()) FillPrograms(reportNode.getChildNodes());
							break;
						case "Students":
							if (reportNode.hasChildNodes()) FillStudents(reportNode.getChildNodes());
							break;
						case "Reports":
							if (reportNode.hasChildNodes()) FillReports(reportNode.getChildNodes());
							break;
						default:
							break;
						}
                	}
                }
            } catch(Exception e) {
            	e.printStackTrace();
            }
		}
	}

	private void FillTasks(NodeList taskNodeList) {
		context.getTasks().clear();
		for (int i = 0; i < taskNodeList.getLength(); i++) {
			Node taskNode = taskNodeList.item(i);
			if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
				Element taskElement = (Element)taskNode;
				int id = Integer.parseInt(taskElement.getAttribute("id"));				
				TaskType type = TaskType.valueOf(taskElement.getAttribute("type"));
				NodeList valueNodeList = taskElement.getChildNodes();
				String name = "";
				int duration = 0;
				for (int j = 0; j < valueNodeList.getLength(); j++) {
					Node valueNode = valueNodeList.item(j);
					if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
						Element valueElement = (Element)valueNode;
						switch (valueNode.getNodeName()) {
						case "Name":
							name = valueElement.getFirstChild().getNodeValue();
							break;
						case "Duration":
							duration = Integer.parseInt(valueElement.getFirstChild().getNodeValue());
							break;
						default:
							break;
						}
					}
				}
				Task task = new Task(id, name, duration, type);
				context.getTasks().put(id, task);
			}
		}
	}
	
	private void FillCourses(NodeList courseNodeList) {
		context.getCourses().clear();
		for (int i = 0; i < courseNodeList.getLength(); i++) {
			Node courseNode = courseNodeList.item(i);
			if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
				Element courseElement = (Element)courseNode;
				int id = Integer.parseInt(courseElement.getAttribute("id"));
				NodeList valueNodeList = courseElement.getChildNodes();
				String name = "";
				String author = "";
				Date creationDate = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				for (int j = 0; j < valueNodeList.getLength(); j++) {
					Node valueNode = valueNodeList.item(j);
					if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
						Element valueElement = (Element)valueNode;
						switch (valueNode.getNodeName()) {
						case "Name":
							name = valueElement.getFirstChild().getNodeValue();
							break;
						case "Author":
							author = valueElement.getFirstChild().getNodeValue();
							break;
						case "CreationDate":
							try {
								creationDate = format.parse(valueElement.getFirstChild().getNodeValue());
							} catch (DOMException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						default:
							break;
						}
					}
				}
				Course course = new Course(id, name, author, creationDate);
				
				NodeList taskNodeList = courseElement.getElementsByTagName("Task");
				for (int j = 0; j < taskNodeList.getLength(); j++) {
					Node taskNode = taskNodeList.item(j);
					if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
						int taskId = Integer.parseInt(((Element)taskNode).getAttribute("id"));
						Task task = context.getTasks().get(taskId);
						course.getTasks().put(taskId, task);
					}
				}				
				context.getCourses().put(id, course);
			}
		}
	}
	
	private void FillPrograms(NodeList programNodeList) {
		context.getPrograms().clear();
		for (int i = 0; i < programNodeList.getLength(); i++) {
			Node programNode = programNodeList.item(i);
			if (programNode.getNodeType() == Node.ELEMENT_NODE) {
				Element programElement = (Element)programNode;
				int id = Integer.parseInt(programElement.getAttribute("id"));
				NodeList valueNodeList = programElement.getChildNodes();
				String name = "";
				String author = "";
				Date creationDate = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				for (int j = 0; j < valueNodeList.getLength(); j++) {
					Node valueNode = valueNodeList.item(j);
					if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
						Element valueElement = (Element)valueNode;
						switch (valueNode.getNodeName()) {
						case "Name":
							name = valueElement.getFirstChild().getNodeValue();
							break;
						case "Author":
							author = valueElement.getFirstChild().getNodeValue();
							break;
						case "CreationDate":
							try {
								creationDate = format.parse(valueElement.getFirstChild().getNodeValue());
							} catch (DOMException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						default:
							break;
						}
					}
				}
				Program program = new Program(id, name, author, creationDate);
				
				NodeList courseNodeList = programElement.getElementsByTagName("Course");
				for (int j = 0; j < courseNodeList.getLength(); j++) {
					Node courseNode = courseNodeList.item(j);
					if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
						int courseId = Integer.parseInt(((Element)courseNode).getAttribute("id"));
						Course course = context.getCourses().get(courseId);
						program.getCourses().put(courseId, course);
					}
				}				
				context.getPrograms().put(id, program);
			}
		}
	}
	
	private void FillStudents(NodeList studentNodeList) {
		context.getStudents().clear();
		for (int i = 0; i < studentNodeList.getLength(); i++) {
			Node studentNode = studentNodeList.item(i);
			if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element studentElement = (Element)studentNode;
				int id = Integer.parseInt(studentElement.getAttribute("id"));
				NodeList valueNodeList = studentElement.getChildNodes();
				String name = "";
				String city = "";
				String email = "";
				Date startDate = new Date();
				boolean isSigned = false;
				int programId = 0;
				DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
				for (int j = 0; j < valueNodeList.getLength(); j++) {
					Node valueNode = valueNodeList.item(j);
					if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
						Element valueElement = (Element)valueNode;
						switch (valueNode.getNodeName()) {
						case "FullName":
							name = valueElement.getFirstChild().getNodeValue();
							break;
						case "City":
							city = valueElement.getFirstChild().getNodeValue();
							break;
						case "Email":
							email = valueElement.getFirstChild().getNodeValue();
							break;
						case "StartDate":
							try {
								startDate = formatDate.parse(valueElement.getFirstChild().getNodeValue());
							} catch (DOMException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "IsSigned":
							isSigned = Boolean.valueOf(valueElement.getFirstChild().getNodeValue());
							break;
						case "Program":
							programId = Integer.parseInt(valueElement.getAttribute("id"));
							break;
						default:
							break;
						}
					}
				}
				Student student = new Student(id, name, city, email, startDate, isSigned, context.getPrograms().get(programId));				
				context.getStudents().put(id, student);
			}
		}
	}
	
	private void FillReports(NodeList reportNodeList) {
		context.getReports().clear();
		for (int i = 0; i < reportNodeList.getLength(); i++) {
			Node reportNode = reportNodeList.item(i);
			if (reportNode.getNodeType() == Node.ELEMENT_NODE) {
				Element reportElement = (Element)reportNode;
				int studentId = Integer.parseInt(reportElement.getAttribute("studentId"));
				
				Report report = new Report(context.getStudents().get(studentId));
				
				NodeList taskNodeList = reportElement.getElementsByTagName("Task");
				for (int j = 0; j < taskNodeList.getLength(); j++) {
					Node taskNode = taskNodeList.item(j);
					if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
						int taskId = Integer.parseInt(((Element)taskNode).getAttribute("id"));
						
						NodeList valueNodeList = reportElement.getChildNodes();
						TaskStatus status = TaskStatus.NOT_STARTED;
						int rate = 0;
						for (int k = 0; k < valueNodeList.getLength(); k++) {
							Node valueNode = valueNodeList.item(k);
							if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
								Element valueElement = (Element)valueNode;
								switch (valueNode.getNodeName()) {
								case "Status":
									status = TaskStatus.valueOf(valueElement.getFirstChild().getNodeValue());
									break;
								case "Rate":
									rate = Integer.parseInt(valueElement.getFirstChild().getNodeValue());
									break;
								default:
									break;
								}
							}
						}
						TaskForReport task;
						if (rate == 0) task = new TaskForReport(context.getTasks().get(taskId), status);
						else task = new TaskForReport(context.getTasks().get(taskId), status, rate);
						
						report.getTasks().put(taskId, task);
					}
				}
				
				context.getReports().put(studentId, report);
			}
		}
	}
}
