/**
 * Test.java
 * [2013/04/22]
 * JASON KHAMPHILA
 * 
 * A class to test LinkedList.java and Student.java.
 */

package linkedList;

public class Test
{
	public static void main(String[] args)
	{
		// Create the LinkedList
		LinkedList<Student> list = new LinkedList<Student>();
		
		// Add Students to the list
		list.addLast(new Student(667, "Lucy Fur", 67));
		list.addLast(new Student(668, "Gee Brow", 97));
		list.addLast(new Student(669, "Reds Macrogi", 50));
		list.addLast(new Student(670, "Space Collisions", 100));
		list.addLast(new Student(671, "Higgs Boison", 88));
		
		// Output
		System.out.println("===Initial List===");
		for(int i = 0; i < list.getLength(); i++)
		{
			System.out.println(list.getData(i) + "\n===");
		}
		
		// Perform actions on the list
		list.add(new Student(223, "Risk On Acove", 0), 3);
		list.remove(0);
		
		// Output after changes
		System.out.println("===Edited List===");
		for(int i = 0; i < list.getLength(); i++)
		{
			System.out.println(list.getData(i) + "\n===");
		}
	}
}
