package gui.controller;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.model.Annotation;
import gui.model.ImageInfo;
import gui.model.Model;
import gui.view.AddCommentWindow;
import gui.view.ImageApp;
import gui.view.ImageBox;
import junit.framework.TestCase;

public class TestAddAnnotationController extends TestCase {

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
		imagePanel = app.getImageBox();
		app.setVisible(true);
		
	}
	
	@After
	public void tearDown() {
		app.setVisible(false);
		app.dispose();
	}
	
	@Test
	public void testAdd() {

		//this is the "drawn" rectangle
		Rectangle r = new Rectangle(1, 2, 12, 12);
		
		AddCommentWindow acw = new AddCommentWindow(); 
		acw.setText("TESTCOMMENT");
		
		//this should be compared against
		Annotation an = new Annotation(r, acw.getText());
//		System.out.println("adding...");
		
		AddAnnotationController ancont = new AddAnnotationController(m, imagePanel, app);
		
		ancont.prepare(r);
		assertEquals(m.getActiveBox(), r);
		ancont.process(acw, r);
		acw.dispose();
		
//		System.out.println("should equal: " + an.GetComment() + " rectangle: " + an.GetBox().x + " " + an.GetBox().y );
//		System.out.println("added");
//		for(Annotation a : m.getImage().getAnnotations())
//		{
//			System.out.println("comment: " + a.GetComment() + " rectangle: " + a.GetBox().x + " " + a.GetBox().y );
//		}
		imagePanel.setCurrentImage(ii);
		app.repaint();
		imagePanel.repaint();
		imagePanel.paintComponent(imagePanel.getGraphics());
		//confirm that the annotation was added
		assertEquals(m.getImage().getAnnotations().size(), 1);
		Annotation an2 = m.getImage().getAnnotations().get(0); 
		assertEquals(an2.GetComment(), an.GetComment());
		assertEquals(an2.GetBox().equals(an.GetBox()), true);

	}

}

