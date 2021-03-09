package gui.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import gui.controller.DataController;
import gui.controller.DeleteAnnotationController;
import gui.controller.LoadImageController;
import gui.model.Model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class ImageApp extends JFrame{

	Model model;
	
	private JPanel contentPane;
	ImageBox imagePane;
	
	JButton btnDelComment;
	JButton btnAddComment;
	JTextArea textArea;

	public ImageBox getImageBox() { return imagePane; }
	public JButton getDelButton() { return btnDelComment; }
	public JButton getAddButton() { return btnAddComment; }
	public JTextArea getTextField() { return textArea; }

	/**
	 * Create the frame.
	 * @param image 
	 */
	public ImageApp(Model m) {
		
		this.model = m;
		setTitle("Image Annotation");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 585);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		//adding in custom versions of JPanel...
		imagePane = new ImageBox(model, this);
		
		btnDelComment = new JButton("Delete");
		
		btnDelComment.addMouseListener(new MouseInputAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				new DeleteAnnotationController(model, ImageApp.this, imagePane).process(); 
			}
		});
		
		btnAddComment = new JButton("Add Comment");
		
		btnAddComment.addMouseListener(new MouseInputAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				//call out to AddAnnotationController Controller
				//System.out.println("Add pressed!");
				imagePane.setDrawingMode(true);
				//System.out.println("add worked??" + imagePane.isDrawingMode()); 
			}
		});
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);
		
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DataController(m, ImageApp.this, imagePane).save();
			}
		});
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//check if there is an image being annotated already, if there is save the data before loading
				if(m.getImage() != null)
				{
					new DataController(m, ImageApp.this, imagePane).save(); 
				}
				
				new LoadImageController(m, ImageApp.this).openImage();
				new DataController(m, ImageApp.this, imagePane).loadAnnotations();
			}
			
		});
		mnNewMenu.add(mntmLoad);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//save data to disk before exiting application
				new DataController(m, ImageApp.this, imagePane).save();
				//exit the application
				System.exit(0);
			}
			
		});
		
		
		mnNewMenu.add(mntmExit);
		
		
		//style
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textArea.setBackground(Color.LIGHT_GRAY);
		imagePane.setBackground(Color.GRAY);
		imagePane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		JLabel imageLabel = new JLabel("Image");
		imageLabel.setFont(new Font("Arial", Font.BOLD, 18));
		imageLabel.setForeground(Color.WHITE);
		JLabel commentLabel = new JLabel("Comment:");
		commentLabel.setForeground(Color.WHITE);
		commentLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(imageLabel, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addGap(226))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(imagePane, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(commentLabel, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(10)
								.addComponent(btnAddComment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnDelComment, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(imageLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(commentLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDelComment)
								.addComponent(btnAddComment)))
						.addComponent(imagePane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		
		contentPane.setLayout(gl_contentPane);
		
	}
}
