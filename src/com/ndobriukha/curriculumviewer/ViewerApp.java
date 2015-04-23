package com.ndobriukha.curriculumviewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ndobriukha.curriculumviewer.gui.FileMenu;
/**
 * 
 * @author Nikita_Dobriukha
 * Главное окно приложения.
 * Содержит меню и двухпанельную рабочую область.
 */
public class ViewerApp {
	
	private final Dimension MINIMUM_WINDOW_SIZE = new Dimension(800, 600);
	private final Dimension MINIMUM_TREEVIEW_SIZE = new Dimension(250, 400);
	private final Font DEFAULT_FONT = new Font("Courier New", Font.ITALIC, 12);

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewerApp window = new ViewerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewerApp() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setMinimumSize(MINIMUM_WINDOW_SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
				
		JTextArea textDetail = new JTextArea();
		Context context = new Context(textDetail);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new FileMenu(context);
		menuBar.add(mnFile);

		JScrollPane treeView = new JScrollPane(context.getTree());
		treeView.setMinimumSize(new Dimension(MINIMUM_TREEVIEW_SIZE));
		treeView.setFont(DEFAULT_FONT);
		splitPane.setLeftComponent(treeView);

		textDetail.setEditable(false);
		textDetail.setFont(DEFAULT_FONT);
		splitPane.setRightComponent(textDetail);		
	}

}
