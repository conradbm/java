package pkg;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Main {

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
	
	public static void main(String[] args) {
				
		do{
			new Menu();
			if(choice == 1){ // GET RECT, ESTABLISH MATRIX
				Scanner in = new Scanner(System.in);
				
				System.out.println("Enter X1: ");
				x1 = in.nextInt();
				System.out.println("Enter X2: ");
				x2 = in.nextInt();
				System.out.println("Enter Y1: ");
				y1 = in.nextInt();
				System.out.println("Enter Y2: ");
				y2 = in.nextInt();
				
				if(x1 > x2) majorX = x1;
				else majorX = x2;
				if(y1 > y2) majorY = y1;
				else majorY = y2;
				
				setupDomainGrid();
				if(YEA && YUP)
					fillRectWithChar(areaChar, peramChar);
				else{
					fillRectWithChar("#", "*");
				}
				PERMISSIONGRANTED = true;
			}
			
			if(choice == 2){ //GET AREA CHAR
				YEA = true;
				Scanner in = new Scanner(System.in);
				areaChar = in.nextLine();
			}
			if(choice == 3){ //GET PARAMETER CHAR
				YUP = true;
				Scanner in = new Scanner(System.in);
				peramChar = in.nextLine();
			}
			if(choice == 4){ // PRINT YOUR RECT IN DOS
				printMatrix();
			}
			if((choice == 5) && PERMISSIONGRANTED){ // CREATE A GUI OF YOUR RECT
				dw = new DynamicWindow(DomainMatrix);
			}
			if(choice == 6) break;
			
		}while(choice <= 5 && choice >= 1);
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
		//System.out.println(".length " + DomainMatrix.length); // column length
		//System.out.println("[0].length " + DomainMatrix[0].length); // row length
		
		for (int i = 0; i < DomainMatrix.length; i++) {
			for (int j = 0; j < DomainMatrix[0].length; j++) {
				System.out.print(DomainMatrix[i][j] + " ");
				//al.get(i).get(j);
			}
			System.out.println();
		}
	}

	private static void setupDomainGrid() {
		/*
		for(ArrayList<String> s : al){
			for(String s2 : s){
				s2 = null;
			}
		}
		*/
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
					//al.get(row).set(col, Integer.toString(col));
					DomainMatrix[row][col] = Integer.toString(col);
				}
				else if(col == 0){
					//al.get(col).set(row, Integer.toString(row));
					DomainMatrix[row][col] = Integer.toString(row);
				}
				else{
					//al.get(row).set(".")
					DomainMatrix[row][col] = ".";
				}
			}
		}
		
		
		
		/* will print them with the correct axis, just not the recet
		int n = 0;
		for(int row = DomainMatrix.length; row >= 0; --row){
			for(int col = 0; col <= DomainMatrix[0].length; ++col){
				if(row == 0){
					//al.get(row).set(col, Integer.toString(col));
					//DomainMatrix[row][col] = Integer.toString(col);
					System.out.print(Integer.toString(col) + "  ");
				}
				else if(col == 0){
					//al.get(col).set(row, Integer.toString(row));
					//DomainMatrix[row][col] = Integer.toString(row);
					System.out.print(Integer.toString(row) + "  ");

				}
				
				else{
					//al.get(row).set()
					//DomainMatrix[row][col] = ".";
					System.out.print(".");
				}
			}
			System.out.println();
		}
		*/
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