/**
 * [2013/4/9]
 * JASON KHAMPHILA
 * 
 * Determine the address of every address in the array:
 * double[10][5]
 */

public class AddressTest
{
	public static void main(String[] args)
	{
		int rows = 10;
		int columns = 5;
		
		// Random base address to test
		int baseAddress = 0xFFF0BC34;
		
		AddressFinder finder = new AddressFinder(AddressFinder.DataType.SHORT,
				baseAddress, rows, columns);
		
		// Go through every value and print the address of the element
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				System.out.print("[" + i + "][" + j + "] : ");
				System.out.println( finder.convertToHex(finder.getAddress(i, j)) );
			}
		}
	}
}