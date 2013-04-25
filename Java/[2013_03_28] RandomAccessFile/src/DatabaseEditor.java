/**
 * Write a program to create a database file.
 * 
 * The file created contains records with the following fields:
 * -Last name (16 char = 32)
 * -First name (16 char = 32)
 * -Year (1 short = 2)
 * -Age (1 byte = 1)
 * -Annual Income (1 int = 4)
 * -Tax Paid (25%) (1 int = 4)
 * 
 * Total Size = 75
 * 
 * The data should be inputed by the user.
 * The user should create 5 records.
 * Change the income field for all records.
 * Increase tax rate for all records by 1%.
 * Check file status before reading.
 * 
 * JASON KHAMPHILA
 */

import java.io.*;

public class DatabaseEditor
{
	private static final String INVALID_ENTRY = "Invalid entry. Re-enter.";
	private InputStreamReader r = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(r);
	
	boolean running = true;
	String input;
	
	public void run() throws IOException
	{
		while(running)
		{
			System.out.println("What would you like to do?");
			System.out.println("1. Create a new database.");
			System.out.println("2. Edit an existing database.");
			System.out.println("3. Read an existing database.");
			System.out.println("4. Quit.");
			System.out.print(">> ");
			
			input = new String(br.readLine());
			
			if(input.equals("1"))
				writeRecord();
			else if(input.equals("2"))
				editRecord();
			else if(input.equals("3"))
				readRecord();
			else if(input.equals("4"))
				quit();
			else
			{
				System.out.println(INVALID_ENTRY);
			}
		}
	}
	
	private void writeRecord() throws IOException
	{
		String fileName = new String();
		boolean ready = false;
		boolean startWrite = false;
		
		while(ready == false)
		{
			boolean fileNameSet = false;
			while(fileNameSet == false)
			{
				System.out.print("What do you want to name it? ");
				input = br.readLine();
				
				if(input.equals(""))
				{
					System.out.println(INVALID_ENTRY);
				}
				else
				{
					fileName = input;
					fileNameSet = true;
				}
			}
			
			File f = new File(fileName);
			boolean overwriteClear = false;
			while(overwriteClear == false)
			{
				if(f.exists())
				{
					System.out.print(fileName + " already exists. Overwrite? [y/n] ");
					input = br.readLine().toLowerCase();
					if(input.equals("y") || input.equals("yes"))
					{
						overwriteClear = true;
						startWrite = true;
						ready = true;
					}
					else if(input.equals("n") || input.equals("no"))
					{
						ready = true;
						overwriteClear = true;
					}
					else
					{
						System.out.println(INVALID_ENTRY);
					}
				}
				else
				{
					overwriteClear = true;
					startWrite = true;
				}
			}
		}
		
		if(startWrite == true)
		{
			int records = 0;
			
			System.out.print("How many records to input? ");
			input = br.readLine();
			records = Integer.valueOf(input);
			
			Database d = new Database(fileName, records);
			System.out.print("Set the tax (in percentage): ");
			d.setTax(Integer.valueOf(br.readLine()));
			
			for(int i = 0; i < d.getLength(); i++)
			{
				System.out.print("R-" + i + " Last Name: ");
				d.writeLastName(i, br.readLine());
				System.out.print("R-" + i + " First Name: ");
				d.writeFirstName(i, br.readLine());
				System.out.print("R-" + i + " Years Employed: ");
				d.writeYear(i, Short.valueOf(br.readLine()));
				System.out.print("R-" + i + " Age: ");
				d.writeAge(i, Byte.valueOf(br.readLine()));
				System.out.print("R-" + i + " Income: ");
				d.writeIncome(i, Double.valueOf(br.readLine()));
			}
			d.close();
			System.out.println(d.getFileName() + " write complete!\n");
		}
	}
	
	private void editRecord() throws IOException
	{
		System.out.println("What file to edit? ");
		input = br.readLine();
		File f = new File(input);
		
		if(f.exists())
		{
			Database d = new Database(f);
			boolean editing = true;
			while(editing == true)
			{
				System.out.println(d);
				System.out.println("Editing options:");
				System.out.println("1. Last name");
				System.out.println("2. First name");
				System.out.println("3. Years employed");
				System.out.println("4. Age");
				System.out.println("5. Income");
				System.out.println("6. Tax");
				System.out.println("7. End Editing");
				System.out.print(">> ");
				
				input = br.readLine();
				if(input.equals("1"))
				{
					System.out.print("Which record? ");
					int record = Integer.valueOf(br.readLine());
					System.out.print("What last name? ");
					String name = br.readLine();
					d.writeLastName(record, name);
				}
				else if(input.equals("2"))
				{
					System.out.print("Which record? ");
					int record = Integer.valueOf(br.readLine());
					System.out.print("What first name? ");
					String name = br.readLine();
					d.writeFirstName(record, name);
				}
				else if(input.equals("3"))
				{
					System.out.print("Which record? ");
					int record = Integer.valueOf(br.readLine());
					System.out.print("What year? ");
					byte year = Byte.valueOf(br.readLine());
					d.writeYear(record, year);
				}
				else if(input.equals("4"))
				{
					System.out.print("Which record? ");
					int record = Integer.valueOf(br.readLine());
					System.out.print("What age? ");
					byte age = Byte.valueOf(br.readLine());
					d.writeAge(record, age);
				}
				else if(input.equals("5"))
				{
					System.out.print("Which record? ");
					int record = Integer.valueOf(br.readLine());
					System.out.print("What income? ");
					double income = Double.valueOf(br.readLine());
					d.writeIncome(record, income);
				}
				else if(input.equals("6"))
				{
					System.out.print("What tax? ");
					input = br.readLine();
					d.setTax(Double.valueOf(input));
				}
				else if(input.equals("7"))
				{
					editing = false;
					d.close();
					System.out.println(d.getFileName() + " editing complete!\n");
				}
				else
				{
					System.out.println(INVALID_ENTRY);
				}
			}
		}
		else
		{
			System.out.println(input + " does not exist.\n");
		}
	}
	
	private void readRecord() throws IOException
	{
		System.out.print("What file do you want to read? ");
		input = br.readLine();
		File f = new File(input);
		Database d;
		
		if(f.exists())
		{
			d = new Database(f);
			System.out.println(d);
			d.close();
			System.out.println(d.getFileName() + " read complete!\n");
		}
		else
		{
			System.out.println(input + " does not exist.\n");
		}
	}
	
	private void quit()
	{
		running = false;
		System.out.println("~Goodbye~");
	}
}
