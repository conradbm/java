/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw.pkg2matrixrectanglesolution;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	static int choice;
	static String areaChar = null;
	static String peramChar = null;
	static int x1, x2, y1, y2,majorX,majorY;
	static final int constant = 5;
	static String[][] DomainMatrix;
	static ArrayList<ArrayList<String>> al;
	static boolean YEA,YUP;

	public static void main(String[] args) {
				
		do{
			new Menu();
			if(choice == 1){
				Scanner in = new Scanner(System.in);
				
				System.out.println("Enter X1: ");
				x1 = in.nextInt();
				System.out.println("Enter X2: ");
				x2 = in.nextInt();
				System.out.println("Enter Y1: ");
				y1 = in.nextInt();
				System.out.println("Enter Y1: ");
				y2 = in.nextInt();
				
				if(x1 > x2) majorX = x1;
				else majorX = x2;
				if(y1 > y2) majorY = y1;
				else majorY = y2;
				

				setupDomainGrid();
				printMatrix();
				if(YEA && YUP)
					fillRectWithChar(areaChar, peramChar);
				else{
					fillRectWithChar("X", "*");
				}
				printMatrix();
				
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
			if(choice == 4){
				break;
			}
		}while(choice <= 3 && choice >= 1);
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
		
		System.out.println("  Y -->");
		System.out.println("X");
		System.out.println("'|'");
		System.out.println(" | ");
		
		for (int i = 0; i < DomainMatrix.length; i++) {
			for (int j = 0; j < DomainMatrix[0].length; j++) {
				System.out.print(DomainMatrix[i][j] + " ");
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
		DomainMatrix = new String[majorX + 5][majorY + 5];// [row][col]
		for(int row = 0; row < majorX + constant; ++row){
			for(int col = 0; col < majorY + constant; ++col){
				if(row == 0){
					//al.get(row).set(col, Integer.toString(col));
					DomainMatrix[row][col] = Integer.toString(col);
				}
				else if(col == 0){
					//al.get(col).set(row, Integer.toString(row));
					DomainMatrix[row][col] = Integer.toString(row);
				}
				else{
					//al.get(row).set()
					DomainMatrix[row][col] = ".";
				}
			}
		}
	}
}

