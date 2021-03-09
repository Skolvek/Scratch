package gui.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import gui.controller.AddAnnotationController;
import gui.controller.SelectCommentController;
import gui.model.Annotation;
import gui.model.ImageInfo;
import gui.model.Model;

public class ImageBox extends JPanel {

	Model model;
	ImageApp app;
	
	private ImageInfo currentImage;
	private BufferedImage buffer;
	
	private int buffHeight;
	private int buffWidth;
	
	//only applies to comments
	private static final Color SELECT_COLOR = Color.red; 
	private static final Color UNSELECT_COLOR = Color.lightGray;
	
	private boolean drawingMode;
	
	public boolean isDrawingMode() { return drawingMode; }
	public void setDrawingMode(boolean enable) { this.drawingMode = enable; }
	
	public BufferedImage getBuffer() { return buffer; }
	
	/**
	 * Create the panel.
	 */
	public ImageBox(Model m, ImageApp app) {
		this.model = m;
		this.app = app;
		drawingMode = false;
		
		AddAnnotationController anControl = new AddAnnotationController(model, this, app);
		this.addMouseListener(anControl);
		
		SelectCommentController scControl = new SelectCommentController(model, this, app);
		this.addMouseListener(scControl);
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		// System.out.println(currentImage.filepath);
		super.paintComponent(g);
		
		if(getCurrentImage() == null)
		{
			return;
		} //window builder thing
		else
		{
			g.drawImage(bufferImage(model.getImage()),  0,  0,  this);
		}

		//draw every annotation on the window
		for(Annotation a : currentImage.getAnnotations())
		{
			Rectangle box = a.GetBox();
			g.setColor(UNSELECT_COLOR);
			
			g.drawRect(box.x, box.y, box.width, box.height);
			
			drawBox(g, box, UNSELECT_COLOR); 
			
		}
		
		//draw the selected / active box
		Rectangle activeBox = model.getActiveBox();
		if(activeBox != null)
		{
			g.setColor(SELECT_COLOR);
			g.drawRect(activeBox.x, activeBox.y, activeBox.width, activeBox.height);
			drawBox(g, activeBox, SELECT_COLOR);
		}
	}
	
	private void drawBox(Graphics g, Rectangle box, Color c)
	{
		Graphics2D g2 = (Graphics2D) g;
		float thicc = 3;
		Stroke old = g2.getStroke();
		g2.setStroke(new BasicStroke(thicc));
		g2.setColor(c);
		g2.drawRect(box.x, box.y, box.width, box.height);
		g2.setStroke(old);	
	}
	
	public Dimension getPerferredSize()
	{
		return new Dimension(buffWidth, buffHeight);
	}
	
	private BufferedImage bufferImage(ImageInfo imageInf)
	{
		BufferedImage bf = null;
		
		try
		{
			bf = ImageIO.read(new File(imageInf.getFilepath()));
		}
		catch(IOException ex)
		{
			System.out.println("ERROR: IOException in ImageBox.java" + ex.toString());
		}
		catch(NullPointerException ex)
		{
			//do nothing...
			//this happens on init
		}
		
		return bf; 
	}
	
	public ImageInfo getCurrentImage() {
		return currentImage;
	}

	public void setCurrentImage(ImageInfo newImage) {
		this.currentImage = newImage;
		
		this.buffer = bufferImage(newImage);
		
		if(buffer != null)
		{
			this.buffHeight = this.buffer.getHeight();
			this.buffWidth = this.buffer.getWidth();
		}
	}
	
}
