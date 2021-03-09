package gui.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.model.ImageInfo;
import gui.model.Model;
import gui.view.ImageApp;
import gui.view.ImageBox;

public class TestLoadImageController {

	Model m;
	ImageApp app;
	ImageBox imagePanel;
	
	String dir = "C:\\Users\\SKolvek\\eclipse-workspace\\ImageAnnotation\\Resources\\";
	String testfile = dir + "lenny.png";
	
	@Before
	public void setUp() {
		m = new Model();		
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
		assertEquals(m.getImage(), null);
		new LoadImageController(m, app).process(testfile);
		assertEquals(m.getImage().getFilepath(), testfile);
	}
}
