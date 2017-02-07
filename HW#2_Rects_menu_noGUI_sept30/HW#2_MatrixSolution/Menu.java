

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
		System.out.println("4. EXIT PROGRAM ");
	}
}
