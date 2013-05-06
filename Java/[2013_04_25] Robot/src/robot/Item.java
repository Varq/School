/**
 * Item.java
 * [2013_04_24]
 * JASON KHAMPHILA
 * 
 * An object which holds a serial number and weight.
 * Also calculates most significant bit of serial number.
 */

package robot;

public class Item
{
	// Used for random item generation and toString padded numbers
	public static final int MAX_SERIAL_NUMBER = 99_999;
	public static final int MAX_WEIGHT = 1000;
	
	private int serialNumber;
	private int weight;
	
	public Item()
	{
		setSerialNumber(0);
		setWeight(0);
	}
	
	public Item(int serialNumber, int weight)
	{
		setSerialNumber(serialNumber);
		setWeight(weight);
	}
	
	public int getSerialNumber()
	{
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber)
	{
		if(serialNumber > MAX_SERIAL_NUMBER || serialNumber < 0)
			throw new IndexOutOfBoundsException("Serial must be positive and less than " + (MAX_SERIAL_NUMBER + 1));
		
		this.serialNumber = serialNumber;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	// Return the first digit in the serial number
	public int getMostSignificantBit()
	{
		if(serialNumber <= (MAX_SERIAL_NUMBER / 10))
			return 0;
		
		int i = serialNumber;
		
		while(i > 10)
		{
			i = i / 10;
		}
		
		return i;
	}
	
	public boolean isSerialNumberEven()
	{
		return serialNumber % 2 == 0;
	}
	
	public String toString()
	{
		String numberToPad = new String(String.valueOf(getSerialNumber()));
		String weightToPad = new String(String.valueOf(getWeight()));
		
		String serialPad = new String();
		String weightPad = new String();
		for(int i = 0; i < String.valueOf(MAX_SERIAL_NUMBER).length(); i++)
			serialPad += '0';
		for(int i = 0; i < String.valueOf(MAX_WEIGHT).length(); i++)
			weightPad += '0';
			
		String paddedSerial = (serialPad + numberToPad).substring(numberToPad.length());
		String paddedWeight = (weightPad + weightToPad).substring(weightToPad.length());
		return "ID: " + paddedSerial + "\tWeight: " + paddedWeight;
	}
}
