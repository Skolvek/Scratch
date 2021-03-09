package gui.model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Utils.DataSaver;

public class Model {
	
	private ImageInfo currentImage; 
	private Annotation selectedAnnotation;
	private Rectangle activeBox; //the box that is either being drawn, or has been selected to view the comment
	
	public final DataSaver ds = new DataSaver(); 
	
	public Model() {
		
	}
	
	public ImageInfo getImage() {
		return currentImage;
	}
	
	public void setImage(ImageInfo image) {
		currentImage = image;
	}
	
	public Annotation getSelectedAnnotation() {
		return selectedAnnotation; 
	}
	
	public void setSelectedAnnotation(Annotation a) {
		selectedAnnotation = a; 
	}

	public Rectangle getActiveBox() {
		return activeBox; 
	}
	
	public void setActiveBox(Rectangle box) {
		activeBox = box;		
	}
}
