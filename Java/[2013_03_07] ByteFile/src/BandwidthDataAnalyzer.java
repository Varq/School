/**
 * Main class which runs asks the user for the file to analyze
 * and how much percent loss they would like to calculate.
 * 
 * Analyzes byte files which are created from BandwidthWriter.java
 */
import java.io.*;

public class BandwidthDataAnalyzer
{
	public static void main(String[] args) throws IOException
	{
		// Output text file formatting
		final String TAB_A_1 = "\t|";
		final String TAB_A_2 = "\t\t|";
		final String TAB_B_1 = "\t["; // Unused
		final String TAB_B_2 = "\t\t[";
		final String DB = "dB";
		final String MHZ = "MHz";
		final String BIT_S = "bit/s";
		
		BandwidthData[] data; // Stores data from file
		int numberOfEntries; // Number of entries in file
		File file; // File to read from
		boolean quit = false; // Check if user quits
		int percent; // Percent loss to be calculated
		
		// File input
		FileInputStream fis;
		BufferedInputStream bis;
		DataInputStream dis;
		
		// Text file output
		File outputFile = new File("analysis.txt");
		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		// Keyboard stream
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		// Ask for file until a valid one is entered or when "quit" is entered
		System.out.print("What file do you want to analyze? ");
		
		file = new File(br.readLine());
		
		 while(!file.exists() || quit)
		{
			System.out.println("File does not exist. Try again or type 'quit' to stop.");
			file = new File(br.readLine());
			
			if(file.getName().equals("quit"))
				quit = true;
		}
		
		// If user enter a filename
		if(!quit)
		{
			// Set up file input stream
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			// Get the number of entries in file
			numberOfEntries = getLength(file);
			
			System.out.println("There are " + numberOfEntries + " records.");
			
			// Get the percent loss to be calculated
			System.out.print("How much percent loss do you want to calculate? ");
			String tmp = br.readLine();
			percent = Integer.parseInt(tmp);
			
			// Begin writing to text file
			pw.println("BANDWIDTH" + TAB_A_1 + "DB1" + TAB_A_2 + "DB2" + TAB_A_2 + "DB3" + TAB_A_2 +
					"AVERAGE");
			
			data = new BandwidthData[numberOfEntries];
			
			for(int i = 0; i < numberOfEntries; i++)
			{
				// Store data from file into the array
				int bandwidth = dis.readInt();
				int db1 = dis.readInt();
				int db2 = dis.readInt();
				int db3 = dis.readInt();
				data[i] = new BandwidthData(bandwidth, db1, db2, db3);
				
				// Write data and calculations to output file
				pw.println(">" + data[i].getBandwidth() + MHZ + TAB_B_2 + data[i].getDb(0) + DB +
						TAB_B_2 + data[i].getDb(1) + DB + TAB_B_2 + data[i].getDb(2) + DB + TAB_A_2);
				pw.println("--Capacity:" + TAB_A_1 + data[i].getCapacity(0) + BIT_S + TAB_A_1 +
						data[i].getCapacity(1) + BIT_S + TAB_A_1 + data[i].getCapacity(2) + BIT_S + TAB_A_1 +
						data[i].getAverageCapacity() + BIT_S);
				pw.println("--" + percent + "% Loss:" + TAB_A_1 + data[i].getLoss(0, percent) + BIT_S + TAB_A_1 +
						data[i].getLoss(1, percent) + BIT_S + TAB_A_1 + data[i].getLoss(2, percent) + BIT_S +
						TAB_A_1 + data[i].getAverageLoss(percent) + BIT_S);
			}
			
			System.out.println("Saved to " + outputFile.getName() + "...");
			
			// Close streams
			br.close();
			bw.close();
			dis.close();
		}
	}
	
	// Calculates the number of entries in a file created by BandwidthWriter.java
	public static int getLength(File f)
	{
		int entrySize = 16; // 4 integers = 16
				
		long size = f.length();
		int length = (int) (size/entrySize);
		return length;
	}
}
