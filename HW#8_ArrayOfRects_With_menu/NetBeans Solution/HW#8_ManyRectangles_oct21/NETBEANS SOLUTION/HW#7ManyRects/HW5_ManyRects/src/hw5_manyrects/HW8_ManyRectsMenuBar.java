/*
HW8 Many Rects
Blake Conrad
* Due: ??? Turned in Oct 21st 2015
 */
package hw5_manyrects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HW8_ManyRectsMenuBar extends JFrame
{
	private JMenuBar menuBar;
	private JMenu setCoordinatesMenu;
	private JMenu actionMenu;
	private JMenu utilitiesMenu;
	private JMenuItem manyRectsItem;
	private JMenuItem setX1;
	private JMenuItem setX2;
	private JMenuItem setY1;
	private JMenuItem setY2;
	private JMenuItem displayRectItem;
	private JMenuItem displayManyRectItem;
	private JTextArea textArea;
	
	static int x1;
	static int x2;
	static int y1;
	static int y2 = -1;
	static int majorX,majorY;
	
	static int start_i = 0;
	static int max;
	int finish_i = max;
	static int start_j = 0;
	static int finish_j = max;
	
	static String areaChar = null;
	static String peramChar = null;
	static String[][] DomainMatrix;
	
	ArrayList<MyRectangle> AL = new ArrayList<MyRectangle>();
	
	public static void main(String[] args){
		
		new HW8_ManyRectsMenuBar();
	}
	
	public HW8_ManyRectsMenuBar(){
		this.setLayout(new BorderLayout());
		setup();
		this.setTitle("*** Rectangle Maker GUI With JMenu Options ***");
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private void setup()
        {
		//All setup, listeners, and adding done here.
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);	
		
		utilitiesMenu = new JMenu("Utilities");
		manyRectsItem = new JMenuItem("Many Rects Setup");
		manyRectsItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String rectCount_str = JOptionPane.showInputDialog("Enter Number Of Rects To Display");
				int rectCount = Integer.parseInt(rectCount_str);
				for(int i = 1; i <= rectCount; i++){
					String x1_str = JOptionPane.showInputDialog("Enter x1");
					String x2_str = JOptionPane.showInputDialog("Enter x2");
					String y1_str = JOptionPane.showInputDialog("Enter y1");
					String y2_str = JOptionPane.showInputDialog("Enter y2");
					int x1_i = Integer.parseInt(x1_str);
					int x2_i = Integer.parseInt(x2_str);
					int y1_i = Integer.parseInt(y1_str);
					int y2_i = Integer.parseInt(y2_str);
					MyRectangle mr = new MyRectangle(x1_i, x2_i, y1_i, y2_i);
					AL.add(mr);
				}
			}
			
		});
		
		actionMenu = new JMenu("Actions");
		menuBar = new JMenuBar();
		displayManyRectItem = new JMenuItem("Display Many Rects");
		displayManyRectItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < AL.size(); i++){
					x1 = AL.get(i).x1;
					x2 = AL.get(i).x2;
					y1 = AL.get(i).y1;
					y2 = AL.get(i).y2;
					
					if(x1 > -1 && x2 > -1 && y1 > -1 && y2 > -1){
						findMajors();
						System.out.println("Major X: "  + majorX + " Major Y: " + majorY + "\n");
						setupDomainGrid();
						printMatrix();
						fillRectWithChar("#", "*");
						printMatrix();
						fillTextArea();
					}
				}
			}
			public void findMajors(){
				if(x1 > x2) majorX = x1;
				else majorX = x2;
				if(y1 > y2) majorY = y1;
				else majorY = y2;
			}
			public void fillTextArea(){
				textArea.setFont(new Font("MonoSpaced", Font.CENTER_BASELINE, 12));
				for (int i = 0; i < DomainMatrix.length; i++) {
					for (int j = 0; j < DomainMatrix[0].length; j++) {
						textArea.append(DomainMatrix[i][j] + " ");
					}
					textArea.append("\n");
				}
			}
		});
		
		setCoordinatesMenu = new JMenu("Single Rect Setup");
		setX1 = new JMenuItem("Set X1");
		setX1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Enter X1");
				x1 = Integer.parseInt(input);
			}
		});
		
		setX2 = new JMenuItem("Set X2");
		setX2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Enter X2");
				x2 = Integer.parseInt(input);
			}
		});
		
		setY1 = new JMenuItem("Set Y1");
		setY1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Enter Y1");
				y1 = Integer.parseInt(input);
			}
		});
		
		setY2 = new JMenuItem("Set Y2");
		setY2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Enter Y2");
				y2 = Integer.parseInt(input);
			}
		});
		
		displayRectItem = new JMenuItem("Display Single Rect");
		displayRectItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(x1 > -1 && x2 > -1 && y1 > -1 && y2 > -1){
					findMajors();
					System.out.println("Major X: "  + majorX + " Major Y: " + majorY + "\n");
					setupDomainGrid();
					printMatrix();
					fillRectWithChar("#", "*");
					printMatrix();
					fillTextArea();
				}
			}
			public void findMajors(){
				if(x1 > x2) majorX = x1;
				else majorX = x2;
				if(y1 > y2) majorY = y1;
				else majorY = y2;
			}
			public void fillTextArea(){
				textArea.setFont(new Font("MonoSpaced", Font.CENTER_BASELINE, 12));
				for (int i = 0; i < DomainMatrix.length; i++) {
					for (int j = 0; j < DomainMatrix[0].length; j++) {
						textArea.append(DomainMatrix[i][j] + " ");
					}
					textArea.append("\n");
				}
			}

		});
		
		// add things
		utilitiesMenu.add(manyRectsItem);
		setCoordinatesMenu.add(setX1);
		setCoordinatesMenu.add(setX2);
		setCoordinatesMenu.add(setY1);
		setCoordinatesMenu.add(setY2);
		actionMenu.add(displayRectItem);
		actionMenu.add(displayManyRectItem);
		menuBar.add(setCoordinatesMenu);
		menuBar.add(actionMenu);
		menuBar.add(utilitiesMenu);
		this.setJMenuBar(menuBar);
		this.add(scrollPane, BorderLayout.CENTER);
	}
        
        
        // Performance functions
	private static void fillRectWithChar(String aChar, String pChar) {
		for (int k = x1; k <= x2; ++k) {
			for (int h = y1; h <= y2; ++h) {
				if(((k == x1) || (k == x2)) || (((h == y1) || (h==y2)))){ //if rows match up or if columns match up
					DomainMatrix[k][h] = pChar;
				}
				else{
					DomainMatrix[k][h] = aChar;
				}
			}
		}
	}
		
	public static void printMatrix(){
		
		for (int i = 0; i < DomainMatrix.length; i++) {
			for (int j = 0; j < DomainMatrix[0].length; j++) {
				System.out.print(DomainMatrix[i][j] + " ");
				//al.get(i).get(j);
			}
			System.out.println();
		}
	}

	private static void setupDomainGrid() {

		if(majorX > majorY) max = majorX; else max = majorY;
		DomainMatrix = new String[max+5][max+5];// [row][col]
		
		System.out.println("MAX LENGTH: " + DomainMatrix.length);
		System.out.println("MAX LENGTH[0]: " + DomainMatrix[0].length);
		
		start_i = 0;
		finish_j = max+5;
		start_j = 0;
		finish_j = max+5;
		
		
		for(int row = 0; row < DomainMatrix.length; ++row){
			for(int col = 0; col < DomainMatrix[0].length; ++col){
				if(row == 0){
					DomainMatrix[row][col] = Integer.toString(col);
				}
				else if(col == 0){
					DomainMatrix[row][col] = Integer.toString(row);
				}
				else{
					DomainMatrix[row][col] = ".";
				}
			}
		}
	}
	public class MyRectangle {
		int x1,x2,y1,y2;
		
		public MyRectangle(int x1, int x2, int y1, int y2){
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
	}
}


