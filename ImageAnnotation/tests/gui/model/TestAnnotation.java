package gui.model;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

public class TestAnnotation {

	@Test
	public void testAnnotationSet() {
		
		Annotation an = new Annotation();
		
		Rectangle r = new Rectangle(2,3,4,5);
		String c = "This is my comment";
		
		an.SetBox(r);
		assertEquals(an.GetBox(), r);
		an.SetComment(c);
		assertEquals(an.GetComment(), c);
		
	}
	
	@Test
	public void testAnnotationConstructor() {
		
		Rectangle r = new Rectangle(1,2,3,4);
		String c = "This is my comment";
		
		Annotation anc = new Annotation(r, c);
		
		assertEquals(anc.GetBox(), r);
		assertEquals(anc.GetComment(), c);
	}

}
