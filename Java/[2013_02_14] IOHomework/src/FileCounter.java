/**
 * Read lines from keyboard input.
 * Read the text into a string.
 * Count lines, words, and characters.
 * Store the text in a file.
 * 
 * JASON KHAMPHILA
 * [2013/2/14]
 */

import java.io.*;
import java.util.StringTokenizer;

public class FileCounter
{
	private String str;
	private String fileName; // Name of file to write to
	private int charCount;
	private int lineCount;
	private int wordCount;
	
	// User keyboard input stream
	private InputStreamReader sr = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(sr);
	
	// File writing stream
	private FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter pw;
	
	// Create a new FileCounter which writes to outputFilename
	public FileCounter(String outputFilename)
	{
		fileName = outputFilename;
		try
		{
			// Set file name of FileWriter
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {}
	}
	
	// Gets user input stored into a string and then counts the items in the string
	public void getUserInput() throws java.io.IOException
	{
		String inLine;
		
		str = new String();

		// When user enters a blank line, stop accepting input
		while(!(inLine = new String(br.readLine())).equals(""))
		{
			str = str + inLine + "\n";
		}
		
		countString();
	}
	
	// Stores the string to a file
	public void outputToFile()
	{
		pw = new PrintWriter(bw);
		pw.print(str);
		pw.close();
	}
	
	// Counts the chararacters, lines, and words in the string.
	public void countString()
	{
		// Count chars here
		charCount = str.length();
		
		// Count lines here
		lineCount = 0;
		int index = 0;
		
		while(str.indexOf("\n", index) > 0)
		{
			lineCount++;
			index = str.indexOf("\n", index) + 1;
		}
		
		// Count words here
		StringTokenizer tokenizer = new StringTokenizer(str);
		
		wordCount = tokenizer.countTokens();
	}
	
	// Prints to console the counts for the string
	public void getCounts()
	{
		System.out.println(
				"===File Counts===" + "\n" +
				"Characters:\t" + charCount + "\n" +
				"Lines:\t" + lineCount + "\n" +
				"Words:\t" + wordCount + "\n");
	}
}
