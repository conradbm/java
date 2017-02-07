import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame
{
	static final int x = 500;
	static final int y = 500;
	static final Dimension WINDOW_DIMENSION = new Dimension(x, y);
	String initializerText = "Ready to draw!";
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem saveItem;
	JMenuItem loadItem;
	JLabel bufferLabel;
	PadDraw PadDraw;
	
	Rectangle bufferLabelRect;
	Rectangle menuBarRect;
	Rectangle canvasRect;
	
	
	public static void main(String[] args) 
	{
		Frame f = new Frame();
	}
	
	public Frame()
	{
		initialize();
		//repaint();
	}
	
	private void initialize()
	{
		// General housekeeping
		this.setSize(this.x,this.y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		saveItem = new JMenuItem("Save");
		loadItem = new JMenuItem("Load");
		bufferLabel = new JLabel(initializerText);
		PadDraw = new PadDraw();
		
		// Bound everything up with Rectangles
		
		//bufferLabelRect
		bufferLabelRect = new Rectangle(this.x, y - 400);
		bufferLabel.setBounds(bufferLabelRect);
				
		//menuBarRect
		menuBarRect = new Rectangle(menuBar.getSize().width, menuBar.getSize().height);
		menuBar.setBounds(menuBarRect);
		
		// Add Action Listeners
		saveItem.addActionListener(new itemActionListener());
	    loadItem.addActionListener(new itemActionListener());
			  
		// Add elements
	    fileMenu.add(saveItem);
	    fileMenu.add(loadItem);
		menuBar.add(fileMenu);
		this.add(menuBar, BorderLayout.NORTH);
		this.add(PadDraw, BorderLayout.CENTER);	
		this.add(bufferLabel, BorderLayout.SOUTH);

		// make it visisble before we launch
		this.setVisible(true);
	}

	private class itemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Save Button ActionListener
			if(e.getActionCommand() == "Save"){
				try {
					PadDraw.save();
					updateLabel("Saving...");
				} catch (IOException exception) {
					exception.printStackTrace();
					updateLabel("Error Saving File: " + exception.getMessage() + ".\n");
				}
			resetLabel();
			}
			
			//Load Button ActionListener
			else if(e.getActionCommand() == "Load"){
				try{
					PadDraw.load();
					//PadDraw.red();
					updateLabel("Loading...");
				} catch(IOException e1){
					e1.printStackTrace();
					updateLabel("Error Loading File: " + e1.getMessage() + ".\n");
				}
			resetLabel();
			}
		}
	}
	
	private void updateLabel(String text){
		bufferLabel.setText(text);
	}
	private void resetLabel(){
		bufferLabel.setText(initializerText);
	}
}
