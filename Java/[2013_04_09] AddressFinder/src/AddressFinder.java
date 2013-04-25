/**
 * [2013/4/9]
 * JASON KHAMPHILA
 * 
 * This program can find an the address of any element in an array
 * if given the base address.
 */
public class AddressFinder
{
	// Finals for readability
	// Used when determining what datatype is being read in.
	public static enum DataType { INTEGER, DOUBLE, SHORT, CHAR, LONG };
	
	// Size of a byte in bits
	private final int BYTE_SIZE = 8;
	
	private int baseAddress;
	private int byteSize;
	
	// Commented out because it is unused
	//private int rowSize;
	// Size of the column of the array.
	private int columnSize;
	
	// Takes in DataType, baseAddress, number of rows and columns in array
	public AddressFinder(DataType type, int baseAddress, int rows, int columns)
	{
		setType(type);
		setBaseAddress(baseAddress);
		// rowSize is unused
		//rowSize = rows;
		columnSize = columns;
	}
	
	// Determine DataType so byteSize can be set.
	public void setType(DataType type)
	{
		switch(type)
		{
		case INTEGER:
			byteSize = Integer.SIZE/BYTE_SIZE;
			break;
		case DOUBLE:
			byteSize = Double.SIZE/BYTE_SIZE;
			break;
		case SHORT:
			byteSize = Short.SIZE/BYTE_SIZE;
			break;
		case CHAR:
			byteSize = Short.SIZE/BYTE_SIZE;
			break;
		case LONG:
			byteSize = Long.SIZE/BYTE_SIZE;
		default:
			break;
		}
	}
	
	// Set base address
	public void setBaseAddress(int h)
	{
		baseAddress = h;
	}
	
	// Return base address
	public int getBaseAddress()
	{
		return baseAddress;
	}
	
	// Get address at specified row and column
	public int getAddress(int r, int c)
	{
		int offset = (c * byteSize) + (r * columnSize * byteSize);
		return offset + getBaseAddress();
	}
	
	// Return a string with the int converted to hex
	public String convertToHex(int h)
	{
		String s = new String(String.format("%x", h));
		return s;
	}
}
