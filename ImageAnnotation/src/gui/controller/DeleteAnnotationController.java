package gui.controller;

import gui.model.Annotation;
import gui.model.ImageInfo;
import gui.model.Model;
import gui.view.ImageApp;
import gui.view.ImageBox;

public class DeleteAnnotationController {
	Model model;
	ImageBox imagePanel;
    ImageApp app;
    
    public DeleteAnnotationController(Model m, ImageApp app, ImageBox imagePanel)
    {
    	this.model = m;
    	this.app = app;
    	this.imagePanel = imagePanel;
    }
    
    public void process() 
    {
    	ImageInfo image = model.getImage();
    	Annotation an = model.getSelectedAnnotation();
    	
    	if(an != null)
    	{
    		image.removeAnnotation(an);
    		model.setActiveBox(null);
    		model.setSelectedAnnotation(null);
    		app.getTextField().setText("");
		
	    	//re-draw the application to remove the box and comment
	    	imagePanel.repaint();
			app.repaint();
			
//			model.getImage().getAnnotations().forEach(x -> {
//				System.out.println("Comment" + x.GetComment() + "box: " + x.GetBox());
//			});
    	}
    }
}
