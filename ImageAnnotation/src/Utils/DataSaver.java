package Utils;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import gui.model.Annotation;
import gui.model.ImageInfo;

/*
*File Structure -- quick and dirty implementation
*Filename1, Comment, x, y, width, height
*Filename1, Comment, x, y, width, height
*Filename2, Comment, x, y, width, height
*Filename2, Comment, x, y, width, height
*/


//the model should only have one of these
public class DataSaver {
	
	//assuming you are running on windows ( "\" instead of "/" )
	private static final String SEP = "\t"; 
	private static final String FILENAME = "ImageCommentData.csv";//always created in the /$USER/home directory
	private static final String FULLPATH  = System.getProperty("user.home") + "\\" + FILENAME; 
	
	static ArrayList<String[]> OldDataBuffer = new ArrayList<>(); //previously loaded
	ArrayList<String[]> NewDataBuffer = new ArrayList<>();
	
	public DataSaver()
	{
		
	}
	
	/**
	 * Writes the new image data to the disk.
	 * This overwrites the old .csv with 
	 * all of the image data that does not belong
	 * to the edited image, AND all of the currently
	 * existing image data 
	 * 
	 * @param image -> the image to store annotation data for
	 */
	protected void writeToDisk(ImageInfo image)
	{
		System.out.println("writing...");
		File f = new File(FULLPATH);
		try
		{
			
			//re-create the file
			f.delete();
			
			System.out.println("Deleted");
		
			if(f.createNewFile())
			{
				System.out.println("Created");
				System.out.println("number of annotations" + image.getAnnotations().size());
				for(Annotation a : image.getAnnotations())
				{
				
					System.out.println(image.getFilepath() + " " +  a.GetComment() + " " +a.GetBox().x + " " +a.GetBox().y + " "+ a.GetBox().width + " " +a.GetBox().height);
					NewDataBuffer.add(new String[] {image.getFilepath(), a.GetComment().replace("\n", " "), ""+a.GetBox().x, ""+a.GetBox().y, ""+a.GetBox().width, ""+a.GetBox().height});
				}
	
				FileWriter fw = new FileWriter(f);  
				
				System.out.println("writing to file");
				
				ArrayList<String[]> write = new ArrayList<>();
				write.addAll(OldDataBuffer);
				write.addAll(NewDataBuffer);
				
				for(String[] s : write)
				{
					System.out.println(s.toString());
					StringBuffer sb = new StringBuffer();
					
					for(String str : s)
					{
						sb.append(str + SEP);
					}
					
					fw.write(sb.toString() + System.lineSeparator());
				}
				
				NewDataBuffer = new ArrayList<>();
				
				fw.flush();
				fw.close();
				System.out.println("done");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("done writing");
	}

	
	/**
	 * Loads the image annotation data from the .csv file that is created on disk
	 * if the .csv file does not exist already it will be created
	 * 
	 * returns the annotations for the input filename, populates the OldDataBuffer with everything else
	 * 
	 * @param filename -> The image filename
	 * @return -> the set of annotations for that image
	 */
	public ArrayList<Annotation> loadFromDisk(String filename)
	{
		System.out.println("loading...");
		ArrayList<Annotation> loadedData = new ArrayList<>();
		
		try
		{
			File f = new File(FULLPATH); 
			//System.out.println(FULLPATH);
			if(f.exists() && !f.isDirectory())
			{
				System.out.println("Clearing old buffer");
				OldDataBuffer = new ArrayList<String[]>(); 
				
				Scanner fr = new Scanner(f); 
			
				while(fr.hasNextLine())
				{
					String[] tmpAry = fr.nextLine().split(SEP);
					
					//pipe to loadedData if it matches what we are looking for, else the append buffer
					if(tmpAry[0].equals(filename))
					{
						//unpack to loadedData
						int x = Integer.parseInt(tmpAry[2]);
						int y = Integer.parseInt(tmpAry[3]);
						int w = Integer.parseInt(tmpAry[4]);
						int h = Integer.parseInt(tmpAry[5]);
						
						Rectangle tmpBox = new Rectangle(x,y,w,h);
						loadedData.add(new Annotation(tmpBox, tmpAry[1]));
						System.out.println("Added to loaded Data");
					}
					else
					{
						System.out.println("Adding to old buffer");
						OldDataBuffer.add(tmpAry); 
					}
					
				}
				System.out.println("done with old buffer: size is");
				System.out.println(OldDataBuffer.size());
				fr.close(); 
			}
		}
		catch(NumberFormatException nfe)
		{
			//this should never be thrown in normal use, I guess this can be a test case...
			nfe.printStackTrace();
			return null;
		}
		catch(IndexOutOfBoundsException iobe)
		{
			System.out.println("ERROR: ImageData file format is corrupted!");
			iobe.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		System.out.println("done");
		return loadedData; 
	}
}