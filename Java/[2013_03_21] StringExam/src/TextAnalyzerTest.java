/**
 * TextAnalyzerText.java
 * [2013/3/21] JASON KHAMPHILA
 * A class simply to show how the TextAnalyzer class operates
 */

import java.io.IOException;

public class TextAnalyzerTest
{
	public static void main(String[] args) throws IOException
	{
		// File names
		String input = new String("input.txt");
		String output = new String("output.txt");
		
		TextAnalyzer ta = new TextAnalyzer(input, output);
		
		// Find short and long lines
		ta.findShortLongLines();
		// Count items in text
		ta.countText();
		
		System.out.println("File name:\t\t" + ta.getFileName());
		System.out.println("Shortest line size:\t" + ta.getShortestLine().length());
		System.out.println("Longest line size:\t" + ta.getLongestLine().length());
		System.out.println("Differece in size:\t" + (ta.getLongestLine().length() - ta.getShortestLine().length()));
		System.out.println("Character Count:\t" + ta.getCharacterCount());
		System.out.println("Word Count:\t\t" + ta.getWordCount());
		System.out.println("Block Count:\t\t" + ta.getBlockCount());
		
		// Edit text for output
		ta.capitalizeLines();
		ta.removeMultipleSpaces();
		ta.writeToFile();
	}
}
