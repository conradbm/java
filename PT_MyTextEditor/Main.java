package dannycodejam;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;

import javax.swing.*;

public class Main {
	
	// Static variables
	private static Dimension editorSize;
	private static final String title = "JDragonTextEditor++";
	
	// Baseline components
	JFrame frame;
	Container container;
	static JTextArea textArea;
	JPanel navBarSubPanel;
	
	// Menu bar
	JMenuBar menuBar;
	
	// File Menu and components
	JMenu fileMenu;
	JMenuItem newItem;
	JMenuItem saveItem;
	JMenuItem openItem;
	
	// Edit Menu and components
	JMenu editMenu;
	JMenuItem cutItem;
	JMenuItem copyItem;
	JMenuItem pasteItem;
	
	// Action Menu and components
	JMenu actionMenu;
	JMenuItem playItem;
	JMenuItem stopItem;
	JMenuItem pauseItem;
	
	// 2nd Menu Bar
	JMenuBar menuBar_2;
	
	// Buttons for the MenuBar
	JMenu styleMenu;
	JMenuItem boldItem;
	JMenuItem italicizeItem;
	JMenuItem underlineItem;
	
	Listener listener;
	
	public static void main(String[] args) 
	{
		new Main();
	}


	public Main()
	{
		init();
	}
	private void init()
	{
		// Instantiate
		frame = new JFrame();
		container = new Container();
		textArea = new JTextArea();
		editorSize = new Dimension(650, 700);
		
		// Menu bar
		menuBar = new JMenuBar();
		
		// File Menu
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		saveItem = new JMenuItem("Save");
		openItem = new JMenuItem("Open");
		
		// Edit Menu
		editMenu = new JMenu("Edit");
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("Paste");
		
		// Action Menu
		actionMenu = new JMenu("Actions");
		playItem = new JMenuItem("Play");
		playItem.setIcon(new ImageIcon("/Users/bmc/Desktop/Java_GUIs/Danny Code Jam/playItem.png"));
		listener = new Listener();
		
		// 2nd Menu Bar
		menuBar_2 = new JMenuBar();
		
		// Style Menu
		styleMenu = new JMenu("Style");
			// Bold Item + Icon setting
		boldItem = new JMenuItem("Bold");
		boldItem.setIcon(new ImageIcon("/Users/bmc/Desktop/Java_GUIs/Danny Code Jam/boldItem.png"));
			// Italicize Item + Icon setting
		italicizeItem = new JMenuItem("Italicize");
		italicizeItem.setIcon(new ImageIcon("/Users/bmc/Desktop/Java_GUIs/Danny Code Jam/italicizeItem.png"));
			// Underline Item + Icon setting
		underlineItem = new JMenuItem("Underline");
		underlineItem.setIcon(new ImageIcon("/Users/bmc/Desktop/Java_GUIs/Danny Code Jam/underlineItem.png"));

		// Add items to fileMenu, add fileMenu to menuBar 
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		menuBar.add(fileMenu);
		
		// Add items to the editMenu, add editMenu to menuBar
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		menuBar.add(editMenu);
		
		// Add items to the ActionMenu, add actionMenu to menuBar
		actionMenu.add(playItem);
		menuBar.add(actionMenu);
		
		// Add buttons to StyleMenu, add styleMenu to menuBar_2
		styleMenu.add(boldItem);
		styleMenu.add(italicizeItem);
		styleMenu.add(underlineItem);
		menuBar_2.add(styleMenu);
		
		// Add Listeners
		newItem.addActionListener(listener);
		openItem.addActionListener(listener);
		saveItem.addActionListener(listener);
		cutItem.addActionListener(listener);
		copyItem.addActionListener(listener);
		pasteItem.addActionListener(listener);
		playItem.addActionListener(listener);
		boldItem.addActionListener(listener);
		italicizeItem.addActionListener(listener);
		underlineItem.addActionListener(listener);
		
		// Setup
		container = frame.getContentPane();
		textArea.setEditable(true);
		
		// Make Sub-Panel and set its Layout
		navBarSubPanel = new JPanel();
		navBarSubPanel.setLayout(new BorderLayout());
		
		// Position Sub-Panel components
		navBarSubPanel.add(menuBar, BorderLayout.NORTH);
		navBarSubPanel.add(menuBar_2, BorderLayout.CENTER);
		
		// Position Components
		container.add(textArea, BorderLayout.CENTER);
		container.add(navBarSubPanel, BorderLayout.NORTH);
		
		// Housekeeping
		frame.setTitle(title);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(editorSize);
	}
}
