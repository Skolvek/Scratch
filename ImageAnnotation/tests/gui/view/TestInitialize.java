package gui.view;

import gui.model.*;
import gui.view.ImageApp;
import gui.view.ImageBox;
import junit.framework.TestCase;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.junit.Test;

import junit.framework.TestCase;

public class TestInitialize extends TestCase {
	Model m;
	ImageApp app;
	ImageBox imagePanel;

	String dir = "C:\\Users\\SKolvek\\eclipse-workspace\\ImageAnnotation\\Resources\\";
	String testfile = dir + "lenny.png";
	ImageInfo ii = new ImageInfo(testfile);

	@Before
	public void setUp() {
		m = new Model();
		m.setImage(ii);

		app = new ImageApp(m);
		app.setVisible(true);
	}

	@After
	public void tearDown() {
		app.setVisible(false);
		app.dispose();
	}

	@Test
	public void testLoad() {
		ImageInfo img = m.getImage();
		assertEquals(img, ii);
		
		ArrayList<Annotation> anns = m.getImage().getAnnotations();
		assertEquals(anns.size(), 0);
	}
}
