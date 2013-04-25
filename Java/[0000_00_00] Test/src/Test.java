import java.io.*;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		int i = 4123;
		
		while(i > 10)
		{
			i = i / 10;
		}
		
		System.out.println(i);
	}
}
