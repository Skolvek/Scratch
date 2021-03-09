package gui.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import gui.model.Annotation;
import gui.model.Model;
import gui.view.AddCommentWindow;
import gui.view.ImageApp;
import gui.view.ImageBox;

public class AddAnnotationController extends MouseAdapter{

	Model model;
	ImageBox imagePanel;
	ImageApp app;	
	JTextArea textArea;
	
	Point start;
	Point end;
	
	Rectangle box;
	String comment; 
	
	
	public AddAnnotationController(Model m, ImageBox panel, ImageApp app) {
		this.model = m;
		this.imagePanel = panel;  
		this.app = app;
	}
	
	@Override
	public void mousePressed(MouseEvent me) {

		start = me.getPoint();
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		//System.out.println("Image pane released");
		end = me.getPoint();
		
		//System.out.println("add worked (controller)??" + imagePanel.isDrawingMode());
		if(imagePanel.isDrawingMode() && imagePanel.getBuffer() != null)
		{
			//create box component
			box = new Rectangle(start);
			box.add(end);
			
			prepare(box);
			
			//create a new "add comment" modal 			
			AddCommentWindow ac = new AddCommentWindow();
			ac.setModal(true);
			ac.setVisible(true);
			
			if(ac.isUpdated())
			{
				process(ac, box); 
			}
			
			ac.dispose();
						
			imagePanel.repaint();
			app.repaint();
		}
	}
	
	public void process(AddCommentWindow ac, Rectangle box) {
		//System.out.println("ADDED ANNOTATION!");
		comment = ac.getText();		
		model.getImage().addAnnotation(new Annotation(box, this.comment));
		model.setActiveBox(null);
//		model.getImage().getAnnotations().forEach(x -> {
//			System.out.println("Comment" + x.GetComment() + "box: " + x.GetBox());
//		});
	}
	
	public void prepare(Rectangle box)
	{
		//disable imagePanel for drawing
		imagePanel.setDrawingMode(false);
		model.setActiveBox(box); 
		imagePanel.repaint();
		
		//enable the text feild
		textArea = app.getTextField();
		
		textArea.setEnabled(true);
	}

}
