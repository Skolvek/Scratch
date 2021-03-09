package gui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class AddCommentWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private boolean updated;

	public String getText() {return textArea.getText();}
	public void setText(String text) {textArea.setText(text);}
	public boolean isUpdated() {return updated;}
	
	/**
	 * Create the dialog.
	 */
	public AddCommentWindow() {
		setBackground(Color.WHITE);
		
		updated = false;
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textArea = new JTextArea();
			textArea.setBackground(Color.LIGHT_GRAY);
			textArea.setBounds(10, 25, 414, 203);
			contentPanel.add(textArea);
		}
		{
			JLabel lblNewLabel = new JLabel("Enter Comment");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(10, 0, 242, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Finish");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				
				okButton.addMouseListener(new MouseInputAdapter() {
					
					@Override
					public void mousePressed(MouseEvent e) {
						updated = true;
						AddCommentWindow.this.setVisible(false);
					}
				});
				
			}
		}
	}

}
