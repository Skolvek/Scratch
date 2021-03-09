package gui.model;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;


public class TestModel {

	Rectangle testr = new Rectangle(7, 8, 9, 10); 
	
	@Test
	public void testConstructor() {
		
		Model m = new Model();
		
		//newly created obj should have all nulled out fields
		assertEquals(m.getImage(), null);
		assertEquals(m.getActiveBox(), null);
		assertEquals(m.getSelectedAnnotation(), null); 		
	}
	
	@Test
	public void testSetterGetter() {
		
		Model m = new Model();
		ImageInfo ii = new ImageInfo();
		Rectangle r = new Rectangle(1,2,3,4);
		Annotation an = new Annotation(r, "TEST");

		//set-get image
		m.setImage(ii);
		assertEquals(m.getImage(), ii);
		
		//set-get selectedAnnotation
		m.setSelectedAnnotation(an);
		assertEquals(m.getSelectedAnnotation(), an);
		
		//set-get activeBox
		m.setActiveBox(r);
		assertEquals(m.getActiveBox(), r);
	}
}
