/**
 * ThreeVariablesRunner.java
 *
 * JASON KHAMPHILA
 * [2013/1/22]
 * 
 * A test of the ThreeVariables class.
 * This code is so great. Believe me.
 **/

public class ThreeVariablesRunner
{
	// Check if first line in console, to prevent extra new line
	private static boolean firstLine = true;
	
	// The objects to be played with
	private static ThreeVariables m1;
	private static ThreeVariables m2;
	
	public static void main(String[] args)
	{
		showMessage("Setting m1 to default constructor...\n" +
				"Setting m2 to overloaded constructor...");
		
		m1 = new ThreeVariables();
		m2 = new ThreeVariables(11, 1.3, '$');
		printThreeVariables();
		
		showMessage("Changing all of m1 contents...");
		
		m1.set(4, 4.4, 'D');
		printThreeVariables();
		
		showMessage("Changing contents of m1 to m2...");
		
		m1 = m2;
		printThreeVariables();
		
		showMessage("Creating new instance with default constructors for m2 contents...");
		
		m2 = new ThreeVariables();
		printThreeVariables();
		
		showMessage("Changing the double value of m2...");
		
		m2.setDoubleY(48.23);
		printThreeVariables();
	}
	
	// Prints readable info about m1 and m2
	public static void printThreeVariables()
	{
		System.out.println("m1 contains: " + m1);
		System.out.println("m2 contains: " + m2);
	}
	
	// Basically System.out.println() with some extra stuff
	public static void showMessage(String str)
	{
		if(firstLine)
		{
			System.out.println(str + "\n===");
			firstLine = !firstLine;
			return;
		}

		System.out.println("\n" + str + "\n===");
	}
}
