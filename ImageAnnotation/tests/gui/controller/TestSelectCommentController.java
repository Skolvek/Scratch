package gui.controller;

import gui.model.*;
import gui.view.ImageApp;
import gui.view.ImageBox;
import junit.framework.TestCase;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestSelectCommentController extends TestCase {
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
		imagePanel.setCurrentImage(m.getImage());
		
		app.setVisible(true);

	}

	@After
	public void tearDown() {
		app.setVisible(false);
		app.dispose();
	}
	
	@Test
	public void testSelect() {
		//add in a test annotation
		Rectangle r = new Rectangle(3,4, 10, 10);
		Annotation an = new Annotation(r, "TESTCOMMENT");
		
		m.getImage().addAnnotation(an);
		app.repaint();
		imagePanel.repaint();
		imagePanel.setDrawingMode(false);
		
		SelectCommentController scc = new SelectCommentController(m, imagePanel, app);
		
		//test that a point outside of rectangle does not register
		Point bad_pt = new Point(1,2);
		scc.process(bad_pt);
		assertEquals(m.getActiveBox(), null);
		assertEquals(m.getSelectedAnnotation(), null);
		
		Point good_pt = new Point(3,4);
		scc.process(good_pt);
		assertEquals(m.getActiveBox(), r);
		assertEquals(m.getSelectedAnnotation(), an);
		
	}
}
