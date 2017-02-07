/*
 * HW6 
 */
package hw6_manyrects_nogui;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class HW10_ManyRects_outIO_NoGUI{


	static int choice;
	static Scanner in = new Scanner(System.in);
	ArrayList<MyRectangle> AL = new ArrayList<MyRectangle>();
	
	/* FOR THE RECT */
	public static int x1,x2,y1,y2,majorX,majorY;
	static int start_i = 0;
	static int max;
	int finish_i = max;
	static int start_j = 0;
	static int finish_j = max;
	
	static String areaChar = null;
	static String peramChar = null;
	static String[][] DomainMatrix;

        FileInputStream ins = null;
        FileOutputStream outs = null;
        public static PrintWriter pw;
        
	public static void main(String[] args) throws IOException {
		new HW10_ManyRects_outIO_NoGUI();
	}
	public HW10_ManyRects_outIO_NoGUI() throws IOException{
            do{
                menu();
                Scanner s = new Scanner(System.in);
                String numberOfRects_str;
                int numberOfRects;
                if(choice == 1)
                {
                   
        
                    String inFileName = "inputFile.txt";
                    String outFileName = "outputFile.txt";
        
                    File inputF = new File(inFileName);
                    File outputF = new File(outFileName);
        
                    Scanner inputFile = new Scanner(inputF);
        
                    pw = new PrintWriter(outputF);
        
                    int number = inputFile.nextInt();
                    
                    for(int i = 0; i < number; i++){
                        //get the next 4 ints
                        int x1 = inputFile.nextInt();
                        int x2 = inputFile.nextInt();
                        int y1 = inputFile.nextInt();
                        int y2 = inputFile.nextInt();
                        MyRectangle mr = new MyRectangle(x1,x2,y1,y2);
                        AL.add(mr);
                    }
                    
                    
            	
            }
            else if(choice == 2){
               display();
               pw.close();

            }
            else if(choice == 3){
                System.exit(0);
            }
        }while(choice >= 1 && choice <= 3);
	}
	
	public void menu(){
		System.out.println("\n1. Setup Array Of Rectangles\n");
		System.out.println("2. Print Rectangles\n");
		System.out.println("3. Exit Program\n");
		choice = in.nextInt();
	}

	public void display(){
		for(int i = 0; i < AL.size(); i++){
			x1 = AL.get(i).x1;
			x2 = AL.get(i).x2;
			y1 = AL.get(i).y1;
			y2 = AL.get(i).y2;
			
			if(x1 > -1 && x2 > -1 && y1 > -1 && y2 > -1){
				findMajors();
				setupDomainGrid();
				fillRectWithChar("#", "*");
				printMatrix();
				//fillTextArea();
			}
		}
	}
	
	/* RECT FUNCTIONS */
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
	/*
	public void fillTextArea(){
		textArea.setFont(new Font("MonoSpaced", Font.CENTER_BASELINE, 12));
		for (int i = 0; i < DomainMatrix.length; i++) {
			for (int j = 0; j < DomainMatrix[0].length; j++) {
					textArea.append(DomainMatrix[i][j] + " ");
			}
			textArea.append("\n");
		}
	}
	 */
	public static void printMatrix(){
		
		for (int i = 0; i < DomainMatrix.length; i++) {
			for (int j = 0; j < DomainMatrix[0].length; j++) {
				System.out.print(DomainMatrix[i][j] + " ");
                                pw.print(DomainMatrix[i][j] + " ");
			}
			System.out.println();
                        pw.println();
		}
	}
	public void findMajors(){
		if(x1 > x2) majorX = x1;
		else majorX = x2;
		if(y1 > y2) majorY = y1;
		else majorY = y2;
	}
	private static void setupDomainGrid() {

		if(majorX > majorY) max = majorX; else max = majorY;
		DomainMatrix = new String[max+5][max+5];// [row][col]

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
