/**
 * TextAnalyzer.java
 * [2013/3/21] JASON KHAMPHILA
 * 
 * This program can analyze an input text file by:
 * -Finding and storing the shortest and longest line by
 *   number of characters
 * -Counting the number of characters, words, and blocks
 *   (A block is 512 bytes)
 * 
 * This program can also output an edited input text file
 *   with the following edits:
 * -Capitalize the first letter of each line
 * -Remove multiple adjacent spaces
 */

import java.io.*;
import java.util.StringTokenizer;

public class TextAnalyzer
{
	private static final int BLOCK_SIZE = 512;
	
	private String text = new String(); // input
	private String editedText; // output
	
	private int characterCount;
	private int wordCount;
	private int blockCount;
	
	private String fileName;
	
	private String shortLine;
	private String longLine;
	
	// To read file input
	private FileReader fr;
	private BufferedReader br;
	
	// To write to file output
	private FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter pw;
	
	// Takes in a String fileName to use as the input file
	public TextAnalyzer(String input, String output) throws IOException
	{
		// Sets the file input and output
		fr = new FileReader(input);
		br = new BufferedReader(fr);
		
		fileName = new String(input);
		
		fw = new FileWriter(output);
		bw = new BufferedWriter(fw);
		pw = new PrintWriter(bw);
		
		// Stores the text into a string
		while(br.ready())
		{
			text = new String(text + br.readLine() + "\n");
		}
		
		// Remove last "\n" character
		text = new String(text.substring(0, text.length() - 1));
		
		// Stores text into a string to be edited for output
		editedText = new String(text);
	}
	
	// Finds the shortest and longest line in the text and stores them.
	// In cases of multiple lines have the same length, it will store the first one.
	public void findShortLongLines() throws IOException
	{
		boolean firstTime = true; // first
		int index = 0; // index of first character in line
		int lineBreak = text.indexOf("\n"); // index of new line
		String str; // string to check
		
		do
		{
			// Find new line character
			lineBreak = text.indexOf("\n", index);
			
			// If new line isn't found, set str to index to end of text
			if(lineBreak == -1)
				str = new String(text.substring(index, text.length()));
			// If new is found, set str to index to new line
			else
				str = new String(text.substring(index, lineBreak));
			
			// If first time, set the first line as longest and shortest
			if(firstTime)
			{
				longLine = new String(str);
				shortLine = new String(str);
				firstTime = false;
			}
			
			// Not first time
			else
			{
				// If str is longer than current longest line, replace it
				if(str.length() > longLine.length())
				{
					longLine = new String(str);
				}
				// If str is shorter than current shortest line, replace it
				if(str.length() < shortLine.length())
				{
					shortLine = new String(str);
				}
			}
			
			// Set up new index
			// If lineBreak = -1, then break, because there are no more lines left to check
			index = lineBreak + 1;
		} while(index != 0);
	}
	
	// Count words, characters, and blocks in text
	public void countText()
	{
		// Count words
		StringTokenizer tokenizer = new StringTokenizer(text);
		wordCount = tokenizer.countTokens();
		
		// Count characters
		characterCount = text.length();
		
		// Count blocks, 2 is number of bytes in a character.
		blockCount = ((characterCount * 2) / BLOCK_SIZE) + 1;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public int getCharacterCount()
	{
		return characterCount;
	}
	
	public int getWordCount()
	{
		return wordCount;
	}
	
	public int getBlockCount()
	{
		return blockCount;
	}
	
	public String getShortestLine()
	{
		return shortLine;
	}
	
	public String getLongestLine()
	{
		return longLine;
	}
	
	// Capitalize the first letter of each line
	public void capitalizeLines()
	{
		StringBuffer str = new StringBuffer(editedText); // buffer to edit
		String upper; // string to hold a capitalized first character
		int lineBreak = -1; // new line index, but for first line, it's -1
		
		do
		{
			int index = lineBreak + 1; // index of first letter
			
			// While the char at index is not a character, go to the next character
			while(!Character.isLetter(str.charAt(index)) && index < str.length())
			{
				index++;
			}
			
			// Get a capitalized string version of character after new line
			upper = Character.toString(Character.toUpperCase(str.charAt(index)));
			// Replace that character
			str.replace(index, index + 1, upper);
			
			// Find the next line index
			lineBreak = str.indexOf("\n", index);
			
		// While there are new lines, repeat
		} while(lineBreak != -1);
		
		// Replace edited text
		editedText = new String(str);
	}
	
	// Remove multiple spaces in the editedText
	public void removeMultipleSpaces()
	{
		StringBuffer str = new StringBuffer(editedText); // buffer to edit
		
		// While there are spaces next to each other
		while(str.indexOf("  ") >= 0)
		{
			// Delete a space
			str.delete(str.indexOf("  "), str.indexOf("  ") + 1);
		}
		
		// Replace the edited text
		editedText = new String(str);
	}
	
	// Write to output file
	public void writeToFile()
	{
		pw.print(editedText);
		pw.close();
	}
}
