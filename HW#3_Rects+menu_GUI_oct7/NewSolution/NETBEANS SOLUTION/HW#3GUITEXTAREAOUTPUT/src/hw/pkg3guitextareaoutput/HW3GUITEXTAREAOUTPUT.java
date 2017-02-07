/*
 HW#4RectWithMenuBlakeConrad.java
 Due oct 7 2015
 This program opens a GUI, then after punching in your information will draw a rectangle in the given text area.
 */

package hw.pkg3guitextareaoutput;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class HW3GUITEXTAREAOUTPUT extends JFrame{

	static int choice;
	static String areaChar = null;
	static String peramChar = null;
	static int x1, x2, y1, y2,majorX,majorY;
	static final int constant = 5;
	static String[][] DomainMatrix;
	static ArrayList<ArrayList<String>> al;
	static boolean YEA,YUP, PERMISSIONGRANTED;
	static DynamicWindow dw;
	static int start_i = 0;
	static int max;
	int finish_i = max;
	static int start_j = 0;
	static int finish_j = max;
	
	
	//make this a gui
	//5 labels
	//5 buttons to store that data
	JTextArea x1Label,x2Label,y1Label,y2Label,mainTextArea;
	JButton x1Button,x2Button,y1Button,y2Button,launchButton;
	
	public HW3GUITEXTAREAOUTPUT(){
		x1Label = new JTextArea("X1");
		x2Label = new JTextArea("X2");
		y1Label = new JTextArea("Y1");
		y2Label = new JTextArea("Y2");
		mainTextArea = new JTextArea(30,40);
		
		x1Button = new JButton("SaveX1");
		x1Button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				x1 = Integer.parseInt(x1Label.getText());
			}
			
		});
		
		x2Button = new JButton("SaveX2");
		x2Button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				x2 = Integer.parseInt(x2Label.getText());
			}
			
		});
		
		y1Button = new JButton("SaveY1");
		y1Button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				y1 = Integer.parseInt(y1Label.getText());
			}
			
		});
		
		y2Button = new JButton("SaveY2");
		y2Button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				y2 = Integer.parseInt(y2Label.getText());
			}
			
		});
		launchButton = new JButton("DRAW RECTANGLE");
		launchButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				findMajors();
				System.out.println("Major X: "  + majorX + " Major Y: " + majorY + "\n");
				setupDomainGrid();
				printMatrix();
				fillRectWithChar("#", "*");
				printMatrix();
				fillTextArea();
			}
			public void findMajors(){
				if(x1 > x2) majorX = x1;
				else majorX = x2;
				if(y1 > y2) majorY = y1;
				else majorY = y2;
			}
			public void fillTextArea(){
				mainTextArea.setFont(new Font("MonoSpaced", Font.CENTER_BASELINE, 12));
				for (int i = 0; i < DomainMatrix.length; i++) {
					for (int j = 0; j < DomainMatrix[0].length; j++) {
						mainTextArea.append(DomainMatrix[i][j] + " ");
					}
					mainTextArea.append("\n");
				}
			}
		});
		
		this.add(x1Label);
		this.add(x1Button);
		this.add(x2Label);
		this.add(x2Button);
		this.add(y1Label);
		this.add(y1Button);
		this.add(y2Label);
		this.add(y2Button);
		this.add(launchButton);
		this.add(mainTextArea);

		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
	}
	
	public static void main(String[] args) {
		
		HW3GUITEXTAREAOUTPUT m = new HW3GUITEXTAREAOUTPUT();
	}

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
}

class DynamicWindow extends JFrame{
	JLabel[][] labelMatrix;
	JPanel panel;
	JTextArea ta;
	String[][] dm;
	public DynamicWindow(String[][] DomainMatrix){

		this.dm = DomainMatrix;
		init();
	}
	
	void init(){
		
		ta = new JTextArea(50,50);
		
		for (int i = 0; i < dm.length; i++) {
			for (int j = 0; j < dm[0].length; j++) {
				ta.append(dm[i][j] + " ");
			}
			ta.append("\n");
		}
		
		this.add(ta);
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
