package gui.controller;

import java.util.ArrayList;

import Utils.DataSaver;
import gui.model.Annotation;
import gui.model.Model;
import gui.view.ImageApp;
import gui.view.ImageBox;

public class DataController extends DataSaver {
	
	Model model;
	ImageApp app;
	ImageBox imagePanel;
	
	/**
	 * 
	 * @param m -> Model
	 * @param app -> ImageApp view
	 * @param imagePane -> ImageBox view
	 */
	public DataController(Model m, ImageApp app, ImageBox imagePane)
	{
    	this.model = m;
    	this.app = app;
    	this.imagePanel = imagePane;
	}
	
	public boolean save() {
		boolean ret = false;
		if(model.getImage() != null)
		{
			System.out.println("saving annotations (controller) ..." + model.getImage().getAnnotations().size() + "annotations");
			writeToDisk(model.getImage());
			System.out.println("done");
			ret = true;
		}
		return ret;
	}
	
	public boolean loadAnnotations() {
		boolean ret = false;
		
		ArrayList<Annotation> loaded = loadFromDisk(model.getImage().getFilepath());
		if(loaded.equals(null))
		{
			System.out.println("FAILURE READING .CSV FILE!!");
		}
		else if(loaded.size() > 0)
		{
			for(Annotation a : loaded)
			{
				model.getImage().addAnnotation(a);
			}
			ret = true;
		}
		
		imagePanel.repaint();
		app.repaint();
		return ret;
	}
}
