package Utils;

import static org.junit.Assert.*;

import org.junit.Test;

import gui.model.ImageInfo;

public class TestDataSaver {
	
	//this test class assumes that the .csv file does not exist to begin

	@Test
	public void test() {
		DataSaver ds = new DataSaver(); 
		
		ds.writeToDisk(new ImageInfo()); 
	}
	
	//test save of good data
	@Test
	public void testDataSaver() {
		//create imageInfo
		//add annotations
		//save image
		//assert that the correct data was saved
		
	}
}
