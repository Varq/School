/**
 * Student.java
 * [2013/04/22]
 * JASON KHAMPHILA
 * 
 * A student object to simply data.
 */

package linkedList;

public class Student
{
	private int id;
	private String name;
	private int grade;
	
	public Student()
	{
		setId(0);
		setName(null);
		setGrade(0);
	}
	
	public Student(int id, String name, int grade)
	{
		setId(id);
		setName(name);
		setGrade(grade);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getGrade()
	{
		return grade;
	}
	
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	
	public String toString()
	{
		String s = new String(	"ID:\t" + id + "\n" +
								"Name:\t" + name + "\n" +
								"Grade:\t" + grade);
		return s;
	}
}
