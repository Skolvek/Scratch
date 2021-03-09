package gui.controller;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import gui.model.Annotation;
import gui.model.ImageInfo;
import gui.model.Model;
import gui.view.ImageApp;

public class LoadImageController {
	
	Model m;
	ImageApp app;
	
	public LoadImageController(Model m, ImageApp app)
	{
		this.m = m;
		this.app = app; 
	}
	
	public void openImage()
	{
		//open dialogue to set the image
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fc.showOpenDialog(app);
		
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fc.getSelectedFile();
			process(selectedFile.getAbsolutePath());
		}
	}
	
	public void process(String filepath)
	{
		ImageInfo newImage = new ImageInfo(filepath);
		m.setImage(newImage);
		m.setActiveBox(null);
		app.getImageBox().setCurrentImage(newImage);
		app.getImageBox().repaint();
		app.repaint();
	}
}
