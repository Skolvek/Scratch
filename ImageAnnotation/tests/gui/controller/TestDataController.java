package gui.controller;
import gui.model.*;
import gui.view.ImageApp;
import gui.view.ImageBox;
import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDataController extends TestCase {

	Model m;
	ImageApp app;
	ImageBox imagePanel;

	String dir = "C:\\Users\\SKolvek\\eclipse-workspace\\ImageAnnotation\\Resources\\";
	String testfile = dir + "lenny.png";

	@Before
	public void setUp() {
		m = new Model();

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
	public void test() {
		
		DataController dc = new DataController(m, app, imagePanel);
		
		//try saving nothing...
		boolean save_bad = dc.save();
		assertEquals(save_bad, false);
		
		//create image, load annotation
		ImageInfo ii = new ImageInfo(testfile);
		m.setImage(ii);
		Rectangle r = new Rectangle(3,4, 10, 10);
		Annotation an = new Annotation(r, "TESTCOMMENT");
		m.getImage().addAnnotation(an);
		app.repaint();
		imagePanel.repaint();
		
		//test that it made it in...
		assertEquals(m.getImage().getAnnotations().size(), 1);
		
		boolean save_good = dc.save();
		assertEquals(save_good, true);

		//restart the application...
		tearDown();
		setUp();

		
		//test that we can load what was saved
		m.setImage(ii);
		boolean load_good = dc.loadAnnotations();
		assertEquals(m.getImage().getAnnotations().get(0), an);
	}
}
