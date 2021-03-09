package gui.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import gui.model.Annotation;
import gui.model.ImageInfo;
import gui.model.Model;
import gui.view.ImageApp;
import gui.view.ImageBox;

public class SelectCommentController extends MouseAdapter{
	
	Model model;
	ImageBox imagePanel;
    ImageApp app;	
	
	Point clicked;
	
	public SelectCommentController(Model m, ImageBox box, ImageApp app)
	{
		this.model = m;
		this.imagePanel = box;
		this.app = app;
	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		
		clicked = me.getPoint();
		process(clicked); 
	}

	public void process(Point p) {
		
		if(!imagePanel.isDrawingMode() && imagePanel.getCurrentImage() != null && imagePanel.getBuffer() != null)
		{
			Annotation selected = null;
			
			//find the first annotation containing a box with point p
			ImageInfo imf = model.getImage();
			if(imf.getAnnotations().size() > 0)
			{
				for(Annotation a : imf.getAnnotations())
				{
					if(a.GetBox().contains(p))
					{
						selected = a;
						break;
					}
				}
				
				if(selected != null)
				{
					model.setSelectedAnnotation(selected);
					model.setActiveBox(selected.GetBox());
					app.getTextField().setText(selected.GetComment());
					System.out.println("SELECTED ANNOTATION");
				}
			}
		
			imagePanel.repaint();
			app.repaint();
		}
	}
}
