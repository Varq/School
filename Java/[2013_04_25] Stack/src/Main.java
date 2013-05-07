/**
 * Main.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 * A class to test the ExpressionEvaluator class.
 * The time for calculation is displayed, but the times seem to be inconsistent for the first run
 * of each type of evaluation. Unfortunately, the results prove to be inconclusive.
 * 
 * All code can be found at:
 * https://github.com/Varq/School/tree/master/Java/%5B2013_04_25%5D%20Stack/src
 * or
 * http://goo.gl/7NoCG
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// Input reader
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = null;
		
		// Strings for expressions
		String infix = null;
		String postfix = null;
		
		// If true, program will end
		boolean quit = false;
		
		// While the user hasn't entered the quit input, continue running
		do
		{
			// Prompt user for options
			System.out.print(	"Current Infix Expression: " + infix + "\n" +
								"Current Postfix Expression: " + postfix + "\n" +
								"1 > Enter new infix expression\n" +
								"2 > Evaluate expression by array\n" +
								"3 > Evaluate expression by single linked list\n" +
								"4 > Evaluate expression by double linked list\n" +
								"5 > Quit\n" +
								">> ");
			input = br.readLine();
			
			// Enter a new infix expression
			if(input.equals("1"))
			{
				// Take expression input by user
				System.out.print("Enter a new expression: ");
				String oldInfix = infix;
				infix = br.readLine();
				try
				{
					// Create a postfix expression
					postfix = ExpressionEvaluator.toPostfixExpression(infix, ExpressionEvaluator.StackType.ARRAY);
				}
				catch(Exception e)
				{
					// Invalid expression
					System.out.println(e.getLocalizedMessage());
					infix = oldInfix;
				}
			}
			// Evaluate expression by array
			else if(input.equals("2"))
			{
				// No expression has been entered yet
				if(infix == null)
					System.out.println("Please enter an expression first.");
				else
				{
					long before = System.nanoTime();
					System.out.println("Evaluation: " + ExpressionEvaluator.evaluate(postfix,
							ExpressionEvaluator.StackType.ARRAY, ExpressionEvaluator.ExpressionType.POSTFIX));
					long total = System.nanoTime() - before;
					System.out.println("Time Elapsed: " + total);
				}
			}
			// Evaluate expression by single linked list
			else if(input.equals("3"))
			{
				// No expression has been entered yet
				if(infix == null)
					System.out.println("Please enter an expression first.");
				else
				{
					long before = System.nanoTime();
					System.out.println("Evaluation: " + ExpressionEvaluator.evaluate(postfix,
							ExpressionEvaluator.StackType.SINGLE_LINKED, ExpressionEvaluator.ExpressionType.POSTFIX));
					long total = System.nanoTime() - before;
					System.out.println("Time Elapsed: " + total);
				}
			}
			// Evaluate expression by double linked list
			else if(input.equals("4"))
			{
				// No expression has been entered yet
				if(infix == null)
					System.out.println("Please enter an expression first.");
				else
				{
					long before = System.nanoTime();
					System.out.println("Evaluation: " + ExpressionEvaluator.evaluate(postfix,
							ExpressionEvaluator.StackType.DOUBLE_LINKED, ExpressionEvaluator.ExpressionType.POSTFIX));
					long total = System.nanoTime() - before;
					System.out.println("Time Elapsed: " + total);
				}
			}
			// Quit
			else if(input.equals("5"))
			{
				quit = true;
			}
			// Invalid Input
			else
			{
				System.out.println("Invalid Input. Try again.");
			}
			System.out.println();
		}
		while(!quit);
	}
}
