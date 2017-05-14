import java.awt.Color;
import java.util.ArrayList;


public class OliveJar {
	
	// Properties
	public ArrayList<Olive> olives;
	
	// Public Initializers -- Things each constructor needs
	{
		// Make sure every oliveJar gets a golden olive
		System.out.println("Initializing..");
		olives = new ArrayList<Olive>();
	}
	
	// Constructors
	public OliveJar(){
		System.out.println("Constructing ...");
	}
	
	public OliveJar(int nOlives, String incName, Color incColor){
		for (int i = 0; i < nOlives; i++) {
			olives.add(new Olive(incName, incColor));
		}
	}
	
	// Methods
	public void addOlive(String oliveName , Color color){
		olives.add(new Olive(oliveName, color));
		System.out.println("Added: " + oliveName + "\t" + color);
	}
	
	public void reportOlives(){
		for (Olive o : olives) {
			System.out.println("Name: " + o.oliveName + "\t" + "Color:" + o.color);
		}
	}
	
	// Member Classes
	private class Olive{
		
		// Properties
		public static final long BLACK = 0x000000;
		public String oliveName;
		public Color color;
		
		// Static Initializers
		
		// Constructors
		public Olive(){
			System.out.println("Olive Created ...");
		}
		
		public Olive(String name, Color c){
			oliveName = name;
			color = c;
			System.out.println("Created: " + oliveName + "\t" + color);
		}
	}
}
