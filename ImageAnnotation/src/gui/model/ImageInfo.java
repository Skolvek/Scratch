package gui.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//this represents 1 single image
public class ImageInfo {
	
	//private String imageHash; //reach goal -- deal with this later
	private ArrayList<Annotation> annotations = new ArrayList<>();
	private String filepath;	
	
	public ImageInfo(String filepath)
	{
		if(ValidateFile(filepath))
		{
			System.out.println("valid file");
			this.filepath = filepath; 
		}

		annotations = getAnnotations();
	}
	
	public ImageInfo()
	{
		//default constructor for writing test cases
	}
	
	/**
	 * Validates that the file exists and is usable by this application
	 * @param filepath
	 * @return true -> file is valid, else false
	 */
	public boolean ValidateFile(String filepath)
	{		
		boolean valid = false;		
		List<String> allowed = Arrays.asList("png", "gif", "jpg");
		
		File f = new File(filepath);
		
		if(f.exists())
		{
			String filename=f.getName();
			if(filename.contains(".") && filename.lastIndexOf(".") != 0)
			{
				//ignore upper/lower case
				String ext = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
				valid = allowed.contains(ext);
			}		
		}
		return valid;
	}
	
	/**
	 * gets the set of annotations from a file (use on load of image)
	 * @param filepath
	 * @return -> the set of annotations for this image
	 */
	public ArrayList<Annotation> getAnnotations()
	{	
		return this.annotations; 
	}
	
	/**
	 * Adds a new annotation to the image info for the current loaded image
	 * @param newAnnotation
	 */
	public void addAnnotation(Annotation newAnnotation) {
		this.annotations.add(newAnnotation);
	}

	public void removeAnnotation(Annotation an) {
		this.annotations.remove(an); 		
	}
	
	public String getFilepath() {
		return this.filepath;
	}
	
	//does nothing if the filetype is invalid
	public void setFilepath(String filepath) {
		if(ValidateFile(filepath))
		{
			this.filepath = filepath;
		}
	}
}
