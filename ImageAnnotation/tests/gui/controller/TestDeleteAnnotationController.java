package gui.controller;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.model.Annotation;
import gui.model.ImageInfo;
import gui.model.Model;
import gui.view.ImageApp;
import gui.view.ImageBox;
import junit.framework.TestCase;

public class TestDeleteAnnotationController extends TestCase {

	Model m;
	ImageApp app;
	ImageBox imagePanel;
	
	String dir = "C:\\Users\\SKolvek\\eclipse-workspace\\ImageAnnotation\\Resources\\";
	String testfile = dir + "lenny.png";
	
	@Before
	public void setUp() {
		m = new Model();
		ImageInfo ii = new ImageInfo(testfile);
		m.setImage(ii);
		
		app = new ImageApp(m);
		
		imagePanel = app.getImageBox();
		
		app.setVisible(true);
		
	}
	
	@After
	public void tearDown() {
		app.setVisible(false);
		app.dispose();
	}
	
	@Test
	public void testDelete() {
		//assert starting annotation is there
		//delete it
		//assert not there		
		Rectangle r = new Rectangle(3,4, 10, 10);
		Annotation an = new Annotation(r, "TESTCOMMENT");
		
		m.getImage().addAnnotation(an);
		app.repaint();
		imagePanel.repaint();
		imagePanel.setDrawingMode(false);
		//test that it made it in...
		assertEquals(m.getImage().getAnnotations().size(), 1);
		//select the annotation to delete
		m.setSelectedAnnotation(an);

		new DeleteAnnotationController(m, app, imagePanel).process();
		assertEquals(m.getImage().getAnnotations().size(), 0);
	}

}

