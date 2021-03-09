package gui.model;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Test;

/**
 *NOTE: Update the dir variable to the path to your eclipse-workspace
 */

public class TestImageInfo {
	
	//filepaths for test
	String dir = "C:\\Users\\SKolvek\\eclipse-workspace\\ImageAnnotation\\Resources\\";
	String valid_PNG_File = dir + "lenny.png";
	String valid_JPG_File = dir + "pepe.JPG";
	String valid_GIF_File = dir + "mj_eating_popcorn.gif";
	String invalidFile = dir + "InvalidFile.ods";
	String notExistFile = dir + "imnotreal.png";
	
	//empty array for test
	ArrayList<Annotation> empty = new ArrayList<>();
	
	@Test
	public void testConstructor()
	{
		ImageInfo ii = new ImageInfo();
		assertEquals(ii.getAnnotations(), empty);
		assertEquals(ii.getFilepath(), null);
	}
	
	@Test
	public void testSetFile()
	{
		//tests the constructor that takes a filepath
		//also tests get/setFilepath
		
		ImageInfo ii = new ImageInfo(valid_PNG_File);
		assertEquals(ii.getFilepath(), valid_PNG_File);
		//set to invalid file
		ii.setFilepath(notExistFile);
		//should not change
		assertEquals(ii.getFilepath(), valid_PNG_File);
		
		//set to different valid file
		ii.setFilepath(valid_GIF_File);
		//should change
		assertEquals(ii.getFilepath(), valid_GIF_File);
	}
	
	@Test
	public void testAddRemove()
	{
		ImageInfo ii = new ImageInfo();
		
		ArrayList<Annotation> external = new ArrayList<>();
		Rectangle r = new Rectangle(3,4,5,6);
		Annotation an = new Annotation(r, "TEST");
		
		ii.addAnnotation(an);
		external.add(an);
		//prove it was added...
		assertEquals(ii.getAnnotations(), external);
		
		ii.removeAnnotation(an);
		external.remove(an);
		//prove it was removed...
		assertEquals(ii.getAnnotations(), external);
	}
	
	@Test
	public void testFileValid()
	{
		//test file validation
		ImageInfo ii = new ImageInfo(); 
		assertEquals(ii.ValidateFile(valid_PNG_File), true);
		assertEquals(ii.ValidateFile(valid_JPG_File), true);
		assertEquals(ii.ValidateFile(valid_GIF_File), true);
		assertEquals(ii.ValidateFile(invalidFile), false);
		assertEquals(ii.ValidateFile(notExistFile), false);
	}

}
