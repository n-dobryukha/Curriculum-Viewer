package com.ndobriukha.curriculumviewer.controllers;

import javax.swing.JOptionPane;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * 
 * @author Nikita_Dobriukha
 * Реализация ErrorHandler для xml-парсера
 */
public class ParserErrorHandler implements ErrorHandler {

	private String getParseExceptionInfo(SAXParseException spe) {
        String systemId = spe.getSystemId();
        if (systemId == null) {
            systemId = "null";
        }
        String info = "URI=" + systemId + "\nLine=" + spe.getLineNumber() + ":\n" + spe.getMessage();
        return info;
    }
	
	@Override
	public void error(SAXParseException spe) throws SAXException {
		JOptionPane.showMessageDialog(null, getParseExceptionInfo(spe), "Error", JOptionPane.ERROR_MESSAGE);
		throw spe;
	}

	@Override
	public void fatalError(SAXParseException spe) throws SAXException {
		JOptionPane.showMessageDialog(null, getParseExceptionInfo(spe), "Fatal Error", JOptionPane.ERROR_MESSAGE);
		throw spe;
	}

	@Override
	public void warning(SAXParseException spe) throws SAXException {
		JOptionPane.showMessageDialog(null, getParseExceptionInfo(spe), "Warning", JOptionPane.WARNING_MESSAGE);
		throw spe;
	}

}
