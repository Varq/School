/**
 * ThreeVariables.java
 *
 * JASON KHAMPHILA
 * [2013/1/22]
 * 
 * A class that hold 3 variables. For whatever reason, each variable is either an int, double, or char.
 * I really don't see any need to have comments in this. Unless you have no clue how code works.
 **/
 
 public class ThreeVariables
 {
	private int _x;
	private double _y;
	private char _z;
	 
	public ThreeVariables()
	{
		setIntX(2);
		setDoubleY(9.5);
		setCharZ('B');
	}
	
	public ThreeVariables(int x, double y, char z)
	{
		setIntX(x);
		setDoubleY(y);
		setCharZ(z);
	}
	
	public int getIntX()
	{
		return _x;
	}
	
	public double getDoubleY()
	{
		return _y;
	}
	
	public char getCharZ()
	{
		return _z;
	}
	
	public void set(int x, double y, char z)
	{
		setIntX(x);
		setDoubleY(y);
		setCharZ(z);
	}
	
	public void setIntX(int x)
	{
		_x = x;
	}
	
	public void setDoubleY(double y)
	{
		_y = y;
	}
	
	public void setCharZ(char z)
	{
		_z = z;
	}
	
	public String toString()
	{
		return getIntX() + ", " + getDoubleY() + ", " + getCharZ();
	}
 }