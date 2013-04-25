
public class Hexadecimal
{
	private int value;
	
	public Hexadecimal(int i)
	{
		setValue(i);
	}
	
	public Hexadecimal(Hexadecimal h)
	{
		setValue(h.getValue());
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int i)
	{
		value = i;
	}
	
	public String toString()
	{
		String s = new String(String.format("%x", getValue()));
		return s;
	}
}
