/**
 * Write a program to read a string constant consisting of a lot of different characters.
 * The contents must have uppercase, lowercase, special characters, and line breaks.
 * 
 * 1. Count number of upper case
 * 2. Count number of letters, numbers, and special characters
 * 3. Find the position of all $
 * 4. Replace all $ with #
 * 5. Delete | and &
 * 6. Change the first character of every word to upper case
 * 7. Count number of words
 * 8. Change the first line of text to upper case
 * 9. Count the number of lines
 * 
 * JASON KHAMPHILA
 * [2013/1/24]
 * 
 * -Does all of the above. Can actually be used for any size string, even empty.
 * -Does not perform or report #3 on it's on, for this was combined with #4.
 * -Performs all "count" operations in one method.
 * 
 * Assumes words contain at least one letter.
 */

public class StringManipulator
{
	// String given
	private String originalString;
	
	// String to be manipulated
	private StringBuffer editedString;
	
	// counts for various characters
	private int upperCaseCount; // Upper-case letters
	private int lowerCaseCount; // Lower-case letters
	private int letterCount;
	private int numberCount;
	private int specialCount; // Special characters
	private int wordCount; // assumes words contain at least one letter
	private int lineCount; // assumes line breaks are '\n'
	private int totalCount; // total character count, includes line breaks
	
	public StringManipulator(String str)
	{
		setString(str);
		editedString = new StringBuffer(originalString);
	}
	
	public void setString(String str)
	{
		originalString = str;
	}
	
	public String getOriginalString()
	{
		return originalString;
	}
	
	public StringBuffer getEditedString()
	{
		return editedString;
	}
	
	/*
	 * Counts and stores of the current editedString:
	 * -Upper Case
	 * -Lower Case
	 * -Letters
	 * -Numbers
	 * -Special Characters
	 * -Words
	 * -Lines
	 */
	public void countEverything()
	{
		// reset counts
		upperCaseCount = 0;
		lowerCaseCount = 0;
		letterCount = 0;
		numberCount = 0;
		specialCount = 0;
		wordCount = 0;
		lineCount = 0;
		totalCount = editedString.length();
		
		// allows the count of a new word
		boolean newWord = true;
		
		for(int i = 0; i < editedString.length(); i++)
		{
			// reads the character at index
			char ch = editedString.charAt(i);
			
			// Is it a upper-case letter?
			if(Character.isUpperCase(ch))
			{
				letterCount++;
				upperCaseCount++;
				newWord = true;
			}
			// Is it a lower-case letter?
			else if(Character.isLowerCase(ch))
			{
				letterCount++;
				lowerCaseCount++;
				newWord = true;
			}
			// Is it a number?
			else if(Character.isDigit(ch))
			{
				numberCount++;
			}
			// Is it white space?
			else if(Character.isWhitespace(ch))
			{
				// Is it a new line?
				switch(ch)
				{
				case '\n':
				case '\r':
					lineCount++;
					break;
				}
				
				// If-set of characters after the previous whitespace had letter
				// Then-count a new word
				if(newWord)
				{
					wordCount++;
					newWord = false;
				}
			}
			// It's none of the above? Then it's a special character
			else if(ch != -1)
			{
				specialCount++;
			}
		}
		
		// Accounts for last word and line if string is not empty.
		if(editedString.length() > 0)
		{
			lineCount++;
			wordCount++;
		}
	}
	
	// Perform all tasks in the comment of the program
	public void manipulate()
	{
		findAndReplace("$", "#");
		deleteString("&");
		deleteString("|");
		capitalizeFirstLetter();
		capitalizeFirstLine();
	}
	
	public String counts()
	{
		return ("Upper Case:\t" + upperCaseCount + "\n" +
				"Lower Case:\t" + lowerCaseCount + "\n" +
				"Letters:\t" + letterCount + "\n" +
				"Numbers:\t" + numberCount + "\n" +
				"Special:\t" + specialCount + "\n" +
				"Words:\t\t" + wordCount + "\n" +
				"Lines:\t\t" + lineCount + "\n" +
				"Total Chars:\t" + totalCount + "\n");
	}
	
	// Replace all instances of a string in the string.
	public void findAndReplace(String target, String replacement)
	{
		int index;
		
		// As long as target is in string, keep replacing it
		while((index = editedString.indexOf(target)) != -1)
		{
			editedString.replace(index, index + 1, "#");
		}
	}
	
	// Delete all instances of a string in the string
	public void deleteString(String target)
	{
		int index;
		
		// As long as target is in string, keep deleting it
		while((index = editedString.indexOf(target)) != -1)
		{
			editedString.delete(index, index + 1);
		}
	}
	
	// Capitalize first letter of each word in the string
	// Accounts for words that don't start with a letter
	public void capitalizeFirstLetter()
	{
		int index = 0; // Index of letter to change to upper-case
		boolean hasWordsLeft = true;
		
		// Repeat until no words left
		while(hasWordsLeft)
		{
			// Change the character to upper-case
			editedString.replace(index, index + 1, editedString.substring(index, index + 1).toUpperCase());
			
			// Search for next white space
			while(index < editedString.length() && !Character.isWhitespace(editedString.charAt(index)))
			{
				index++;
			}
			
			// Sets index to character after white space
			index++;
			
			// Set index to first character that is a letter
			if(index < editedString.length() && !Character.isLetter(editedString.charAt(index)))
				index++;
			
			// End of string, no more words left
			if(index >= editedString.length())
			{
				hasWordsLeft = false;
			}
		}
	}
	
	// Capitalize the first line in string
	public void capitalizeFirstLine()
	{
		int indexN = editedString.indexOf("\n");
		int indexR = editedString.indexOf("\r");
		int lineBreak;
		
		if(indexR == -1 || indexN < indexR)
			lineBreak = indexN;
		else
			lineBreak = indexR;
				
		// Sets lineBreak to index OR if single line, end of string.
		if(lineBreak < 0)
			lineBreak = editedString.length();
		
		editedString.replace(0, lineBreak, editedString.substring(0, lineBreak).toUpperCase());
	}
}