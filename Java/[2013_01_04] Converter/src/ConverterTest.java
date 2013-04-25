/**
 * Create a program that stores the values in the string
 * 
 * 
 * JASON KHAMPHILA
 * [2013/1/4]
 * 
 * This program simply cuts up the String "3259.6282 755 16532"
 * into tokens, then stores them into an Object array.
 */

import java.util.StringTokenizer;

public class ConverterTest
{
	// The string we're breaking up
	private static String values = new String("3259.6282 755 16532 -4000000000000000 hello. 1 -128");
	
	private static StringTokenizer tokenizer = new StringTokenizer(values);
	
	// The array used to hold all objects
	private static Object[] array = new Object[tokenizer.countTokens()];
	
	public static void main(String[] args)
	{
		// Sets the objects in the array to the items taken from the tokenizer
		// Also prints information for the user
		for(int i = 0; i < array.length; i++)
		{
			array[i] = setType(tokenizer.nextToken());
			System.out.println((i + 1) + "- " + printType(array[i]));
		}
	}
	
	// Prints out "OBJECT is a TYPE."
	public static String printType(Object o)
	{
		String str = new String();
		
		if(o instanceof String)
			str = new String("string......\t");
		else if(o instanceof Double)
			str = new String("double......\t");
		else if(o instanceof Long)
			str = new String("long int....\t");
		else if(o instanceof Integer)
			str = new String("integer.....\t");
		else if(o instanceof Short)
			str = new String("short int...\t");
		else if(o instanceof Byte)
			str = new String("byte........\t");
		
		return str + o.toString();
	}
	
	// Changes Strings into lowest compatible primitive
	public static Object setType(Object o)
	{
		try
		{
			// Checks if it is a number
			Double.valueOf((String) o);
			
			// Checks if it is a whole number
			if(Double.valueOf((String) o) % 1 == 0)
			{
				// Checks if it is within range of a short
				if(Long.valueOf((String) o) >= -2147483648 && Long.valueOf((String) o) <= 2147483647)
				{
					// Checks if it is within range of a int
					if(Integer.valueOf((String) o) >= -32768 && Integer.valueOf((String) o) <= 32767)
					{
						// Checks if it is within range of a byte
						if(Short.valueOf((String) o) >= -128 && Short.valueOf((String) o) <= 127)
						{
							// Sets as byte
							return Byte.valueOf((String) o);
						}
						
						// Sets as short
						return Short.valueOf((String) o);
					}
					
					// Sets as int
					return Integer.valueOf((String) o);
				}
				
				// Sets as long
				return Long.valueOf((String) o);
			}
			
			// Sets as double
			return Double.valueOf((String) o);
		}
		
		// Caught exception when trying to set non-numeral characters to double
		// Rare case: Number is too large
		catch(Exception e) 
		{
			// Sets as string
			return String.valueOf(o);
		}
	}
}
