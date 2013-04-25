/**
 * [2013/4/9]
 * JASON KHAMPHILA
 * 
 * Write a program to accept a string as an parameter.
 * This method should use recursion display individual characters.
 * Convert the characters to upper case.
 */

public class CapitalizeStringRecursively
{
	public static void main(String[] args)
	{
		System.out.println(capitalizeString("Doing this program recursively is a sort of silly... I guess"));
	}
	
	public static String capitalizeString(String str)
	{
		String s = new String();
		s = s + Character.toUpperCase(str.charAt(0));
		
		if(str.length() > 1)
			return s + capitalizeString(str.substring(1));
		else
			return s;
	}
}

// ===Output===
// DOING THIS PROGRAM RECURSIVELY IS A SORT OF SILLY...