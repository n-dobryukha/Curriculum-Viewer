package com.ndobriukha.curriculumviewer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ndobriukha.curriculumviewer.Context;
/**
 * 
 * @author Nikita_Dobriukha
 * Реализация меню приложения
 */
@SuppressWarnings("serial")
public class FileMenu extends JMenu {
	private static final String MENU_NAME = "File";
	private static final String IMPORT_FROM_XML = "Import from XML...";
	private static final String EXIT = "Exit";
	
	private Context context;
	
	public FileMenu(Context context) {
		super(MENU_NAME);
		this.context = context;
		// Пункт меню импорта из xml-файла
		JMenuItem importFromXmlItem = new JMenuItem(IMPORT_FROM_XML);
		importFromXmlItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				fileopen.setCurrentDirectory(new File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
				fileopen.setFileFilter(filter);
				int ret = fileopen.showDialog(null, "Open File");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File xmlFile = fileopen.getSelectedFile();
					context.setXmlFile(xmlFile);
					try {
						context.getImportController().ImportXmlFile();
						context.getTreeController().BuildTree();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		// Добавление комбинации Ctrl+O для выбора xml-файла
		KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
		importFromXmlItem.setAccelerator(ctrlO);
		// Пункт меню выхода из приложения
		JMenuItem exitItem = new JMenuItem(EXIT);
		exitItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Really Exit?", "Exit", JOptionPane.OK_CANCEL_OPTION);
			    if (ret == JOptionPane.YES_OPTION) System.exit(0);				
			}
		});
		// Добавление комбинации Alt+F4 для выхода из приложения
		KeyStroke altF4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK);
		exitItem.setAccelerator(altF4);
		
		add(importFromXmlItem);
		add(exitItem);
	}
}