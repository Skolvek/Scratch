package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.controller.DataController;
import gui.model.Model;
import gui.view.ImageApp;

public class Main {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//Initialize steps here
					Model m = new Model(); 
					
					ImageApp frame = new ImageApp(m);
					frame.pack();
					frame.setVisible(true);
					
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							new DataController(m, frame, frame.getImageBox()).save();
							frame.dispose();
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
