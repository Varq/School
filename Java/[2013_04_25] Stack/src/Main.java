/**
 * Main.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		String input = null;
		String infix = null;
		String postfix = null;
		
		do
		{
			System.out.print(	"Current Infix Expression: " + infix + "\n" +
								"Current Postfix Expression: " + postfix + "\n" +
								"1 > Enter new infix expression\n" +
								"2 > Evaluate expression by array\n" +
								"3 > Evaluate expression by single linked list\n" +
								"4 > Evaluate expression by double linked list\n" +
								"5 > Quit\n" +
								">> ");
			input = br.readLine();
			
			if(input.equals("1"))
			{
				System.out.print("Enter a new expression: ");
				infix = br.readLine();
				if(!ExpressionEvaluator.isLegalExpression(infix))
				{
					System.out.println("Expression is invalid.");
					infix = null;
				}
				postfix = ExpressionEvaluator.toPostfixExpression(infix, "array");
			}
			else if(input.equals("2"))
			{
				if(infix == null)
					System.out.println("Please enter an expression first.");
				else
				{
					long before = System.nanoTime();
					System.out.println("Evaluation: " + ExpressionEvaluator.evaluate(infix, "array"));
					long total = System.nanoTime() - before;
					System.out.println("Time Elapsed: " + total);
				}
			}
			else if(input.equals("3"))
			{
				if(infix == null)
					System.out.println("Please enter an expression first.");
				else
				{
					long before = System.nanoTime();
					System.out.println("Evaluation: " + ExpressionEvaluator.evaluate(infix, "single linked list"));
					long total = System.nanoTime() - before;
					System.out.println("Time Elapsed: " + total);
				}
			}
			else if(input.equals("4"))
			{
				if(infix == null)
					System.out.println("Please enter an expression first.");
				else
				{
					long before = System.nanoTime();
					System.out.println("Evaluation: " + ExpressionEvaluator.evaluate(infix, "double linked list"));
					long total = System.nanoTime() - before;
					System.out.println("Time Elapsed: " + total);
				}
			}
			System.out.println();
		}
		while(!input.equals(new String("quit").toUpperCase()) && !input.equals("5"));
	}
}
