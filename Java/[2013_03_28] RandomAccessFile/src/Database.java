/**
 * Database.java
 * [2013/3/28]
 * JASON KHAMPHILA
 * 
 * This is a data object which takes a file as a parameter
 * to write or read the data as follows:
 * -Last name
 * -First name
 * -Years employed?
 * -Age
 * -Income
 * -Tax
 */

import java.io.*;

public class Database
{
	// Constant values
	public static final int BYTE_SIZE = 8;
	public static final int NAME_LENGTH = 16;
	public static final int NAME_SIZE = Character.SIZE / BYTE_SIZE * NAME_LENGTH;
	public static final int LAST_NAME_START = 0;
	public static final int FIRST_NAME_START = NAME_SIZE;
	public static final int YEAR_START = FIRST_NAME_START + NAME_SIZE;
	public static final int AGE_START = YEAR_START + (Byte.SIZE / BYTE_SIZE);
	public static final int INCOME_START = AGE_START + (Byte.SIZE / BYTE_SIZE);
	public static final int TAX_START = INCOME_START + (Double.SIZE / BYTE_SIZE);
	public static final int RECORD_SIZE = TAX_START + (Double.SIZE / BYTE_SIZE);
	private static final String TAB = "\t"; // Used for formatting console output
	
	private String fileName;
	private RandomAccessFile file;
	private double percentTax = 25; // The tax applied to income as a percent
	
	/**
	 * Creates a new Database object for the file with specified name.
	 * @param name - name of file
	 * @param length - number of records
	 * @throws IOException - because RandomAccessFile says so
	 */
	public Database(String name, int length) throws IOException
	{
		setFile(name);
		setLength(length);
	}
	
	/**
	 * Creates a new Database object for the specified file.
	 * @param f - File
	 * @throws IOException - because RandomAccessFile says so
	 */
	public Database(File f) throws IOException
	{
		setFile(f.getName());
	}
	
	/**
	 * Sets the file to be read/written to.
	 * @param name - name of file
	 * @throws FileNotFoundException - becauseRandomAccessFile says so
	 */
	public void setFile(String name) throws FileNotFoundException
	{
		fileName = name;
		file = new RandomAccessFile(fileName, "rw");
	}
	
	/**
	 * @return name of file
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * Sets the tax rate and rewrites all tax values in database.
	 * @param percent
	 * @throws IOException
	 */
	public void setTax(double percent) throws IOException
	{
		percentTax = percent;
		
		int index = 0;
		while(index < getLength())
		{
			writeTax(index);
			index++;
		}
	}
	
	/**
	 * @return tax percentage
	 */
	public double getTax()
	{
		return percentTax;
	}
	
	/**
	 * Sets the number of records for file to hold.
	 * @param records - number of records
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void setLength(int records) throws IOException
	{
		file.setLength(records * RECORD_SIZE);
	}
	
	/**
	 * @return number of records in the file
	 * @throws IOException - because RandomAccessFile says so
	 */
	public int getLength() throws IOException
	{
		return (int) (file.length() / RECORD_SIZE);
	}
	
	/**
	 * Write the specified last name to the specified record.
	 * @param record - record to write to
	 * @param name - last name
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void writeLastName(int record, String name) throws IOException
	{
		if(inBounds(record))
		{
			if(name.length() > NAME_LENGTH - 1)
				throw new IllegalArgumentException("Name cannot be longer than 15 characters.");
			
			file.seek(record * RECORD_SIZE + LAST_NAME_START);
			
			file.writeChars(name + "\n");
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
		
	}
	
	/**
	 * @param record - record to read from
	 * @return the last name at specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public String readLastName(int record) throws IOException
	{
		if(inBounds(record))
		{
			file.seek((RECORD_SIZE * record) + LAST_NAME_START);
			
			String lastName = readName();
			
			return lastName;
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * Write the specified first name to the specified record
	 * @param record - record to write to
	 * @param name - first name
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void writeFirstName(int record, String name) throws IOException
	{
		if(inBounds(record))
		{
			if(name.length() > 15)
				throw new IllegalArgumentException("Name cannot be longer than 15 characters.");
			
			file.seek(record * RECORD_SIZE + FIRST_NAME_START);
			
			file.writeChars(name + "\n");
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
		
	}
	
	/**
	 * @param record - record to read from
	 * @return the first name at specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public String readFirstName(int record) throws IOException
	{
		if(inBounds(record))
		{
			file.seek((RECORD_SIZE * record) + FIRST_NAME_START);
			
			String lastName = new String();
			char current = file.readChar();
			int i = 0;
			
			while(current != '\n' && i < NAME_LENGTH)
			{
				lastName = lastName + current;
				current = file.readChar();
			}
			
			int skips = NAME_LENGTH - lastName.length();
			file.skipBytes(skips * 2);
			
			return lastName;
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * Write the specified year to the specified record
	 * @param record - record to write to
	 * @param year - years employed
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void writeYear(int record, short year) throws IOException
	{
		if(inBounds(record))
		{
			file.seek(record * RECORD_SIZE + YEAR_START);
			
			file.writeByte(year);
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
		
	}
	
	/**
	 * @param record - record to read from
	 * @return the year at specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public byte readYear(int record) throws IOException
	{
		if(inBounds(record))
		{
			file.seek((RECORD_SIZE * record) + YEAR_START);
			
			byte year = file.readByte();
			
			return year;
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * Write the specified age to the specified record
	 * @param record - record to write to
	 * @param age - age in years
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void writeAge(int record, byte age) throws IOException
	{
		if(inBounds(record))
		{
			if(age < 0 || age > 127)
				throw new IllegalArgumentException("Invalid age.");
			
			file.seek(record * RECORD_SIZE + AGE_START);
			
			file.writeByte(age);
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
		
	}
	
	/**
	 * @param record - record to read from
	 * @return the age at specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public byte readAge(int record) throws IOException
	{
		if(inBounds(record))
		{
			file.seek((RECORD_SIZE * record) + AGE_START);
			
			byte age = file.readByte();
			
			return age;
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * Write the specified income to the specified record
	 * @param record - record to write to
	 * @param income - annual salary
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void writeIncome(int record, double income) throws IOException
	{
		if(inBounds(record))
		{
			file.seek(record * RECORD_SIZE + INCOME_START);
			
			file.writeDouble(income);
			writeTax(record);
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
		
	}
	
	/**
	 * @param record - record to read from
	 * @return the income at specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public double readIncome(int record) throws IOException
	{
		if(inBounds(record))
		{
			file.seek((RECORD_SIZE * record) + INCOME_START);
			
			double income = file.readDouble();
			
			return income;
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * Rewrites the tax value in accordance to income
	 * @param record - record to write to
	 * @throws IOException - because RandomAccessFile says so
	 */
	private void writeTax(int record) throws IOException
	{
		if(inBounds(record))
		{
			double income = readIncome(record);
			file.seek(record * RECORD_SIZE + TAX_START);
			
			file.writeDouble(income * (percentTax / 100));
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * @param record - record to read from
	 * @return the caluculated tax at specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public double readTax(int record) throws IOException
	{
		if(inBounds(record))
		{
			file.seek((RECORD_SIZE * record) + TAX_START);
			
			double tax = file.readDouble();
			
			return tax;
		}
		else
		{
			throw new IllegalArgumentException("Record is not in bounds.");
		}
	}
	
	/**
	 * Write an entire record.
	 * @param record - record to write to
	 * @param lastName - last name
	 * @param firstName - first name
	 * @param year - years employed
	 * @param age - age in years
	 * @param income - annual salary
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void writeRecord(int record, String lastName, String firstName, int year, int age, double income) throws IOException
	{
		writeLastName(record, lastName);
		writeFirstName(record, firstName);
		writeYear(record, (short) year);
		writeAge(record, (byte) age);
		writeIncome(record, income);
	}
	
	/**
	 * @param record - record to read from
	 * @return a string of all items in the specified record
	 * @throws IOException - because RandomAccessFile says so
	 */
	public String readRecord(int record) throws IOException
	{
		String str = readLastName(record) + TAB + readFirstName(record) + TAB +
				readYear(record) + TAB + readAge(record) + TAB +
				readIncome(record) + TAB + readTax(record);
		return str;
	}
	
	/**
	 * @return the characters read from database until '\n' is found or until character limit is reached
	 * @throws IOException because RandomAccessFile says so
	 */
	private String readName() throws IOException
	{
		// Create new string to hold name
		String name = new String();
		
		// char to hold current character
		char current = file.readChar();
		int i = 0;
		
		// Read chars until '\n' is found or name exceeds limit
		while(current != '\n' && i < NAME_LENGTH)
		{
			name = name + current;
			current = file.readChar();
		}
		
		// Skip bytes to end of string in database JUST IN CASE
		int skips = NAME_LENGTH - name.length();
		file.skipBytes(skips * 2);
		
		return name;
	}
	
	/**
	 * @param record - record to be checked
	 * @return if the record is within bounds
	 * @throws IOException - because RandomAccessFile says so
	 */
	private boolean inBounds(int record) throws IOException
	{
		if(record < getLength())
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Closes RandomdAccessFile so data can be written and such.
	 * @throws IOException - because RandomAccessFile says so
	 */
	public void close() throws IOException
	{
		file.close();
	}
	
	public String toString()
	{
		String s = "LAST\tFIRST\tYEAR\tAGE\tINCOME\tTAX\n";
		try
		{
			for(int i = 0; i < getLength(); i++)
			{
				s = s + readRecord(i);
				
				if(i != getLength() - 1)
					s = s + "\n";
			}
		} catch (IOException e) {};
		
		return s;
	}
}
