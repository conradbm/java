/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw.pkg3matrixrectangleguisolution;

public class Menu extends Main{

	public Menu(){
		printOptions();
		java.util.Scanner in = new java.util.Scanner(System.in);
		int choice = in.nextInt();
		Main.choice = choice;
	}
	public void printOptions(){
		System.out.println("*** HELLO! ***");
		System.out.println("1. ENTER RECT: X1,X2,Y1,Y2");
		System.out.println("2. ENTER AREA CHARACTER ");
		System.out.println("3. ENTER PARAMETER CHARACTER ");
		System.out.println("4. PRINT RECT IN DOS ");
		System.out.println("5. CREATE A GUI OF YOUR RECT ");
		System.out.println("6. EXIT PROGRAM ");
	}
}
