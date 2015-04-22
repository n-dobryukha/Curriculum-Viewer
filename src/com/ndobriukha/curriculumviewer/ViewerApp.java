package com.ndobriukha.curriculumviewer;

import java.awt.BorderLayout;
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

public class ViewerApp {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		
		JTextArea textDetail = new JTextArea();
		Context context = new Context(textDetail);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new FileMenu(context);
		menuBar.add(mnFile);

		JScrollPane treeView = new JScrollPane(context.getTree());
		splitPane.setLeftComponent(treeView);

		textDetail.setEditable(false);
		textDetail.setFont(new Font("Courier New", Font.ITALIC, 12));
		splitPane.setRightComponent(textDetail);
		
		/*try {
			JTree tree = new XmlTree("StudentReport.xml");
			tree.setMinimumSize(new Dimension(200, 30));
			splitPane.setLeftComponent(tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
