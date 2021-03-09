package gui.model;

import java.awt.Rectangle;

public class Annotation {
	
	private Rectangle box;
	private String comment;
	
	//pass this object a Drawn box and Text component???
	public Annotation(Rectangle b, String t)
	{
		this.box = b;
		this.comment = t;
	}

	public Annotation() {
		
	}

	public void SetBox(Rectangle box) {
		this.box = box;
	}
	
	public Rectangle GetBox() { 
		return this.box; 
	}

	public void SetComment(String comment) {
		this.comment = comment;		
	}

	public String GetComment() {
		return this.comment;
	}	
}
