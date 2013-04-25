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
	int serialNumber;
	int weight;
	
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
		if(serialNumber > 100_000_000 || serialNumber < 0)
			throw new IndexOutOfBoundsException("Serial must be positive and less than 100_000_000");
		
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
		if(serialNumber <= 9_999_999)
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
		String number = new String(String.valueOf(getSerialNumber()));
		String paddedSerial = ("00000000" + number).substring(number.length());
		return "ID: " + paddedSerial + "\tWeight: " + getWeight();
	}
}
