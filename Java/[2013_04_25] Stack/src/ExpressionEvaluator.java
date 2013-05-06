/**
 * ExpressionEvaluator.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 * 
 */

import java.io.IOException;

public class ExpressionEvaluator
{
	public static double evaluate(String infix, String stackType) throws IOException
	{
		Stack<String> postfixStack = null;
		Stack<Double> numberStack = null;
		
		if(stackType.equals("single linked list"))
		{
			postfixStack = (LinkedListStack<String>) setPostfixStack(toPostfixExpression(infix, "single linked list"), "single linked list");
			numberStack = new LinkedListStack<Double>();
		}
		else if(stackType.equals("double linked list"))
		{
			postfixStack = (DoubleLinkedListStack<String>) setPostfixStack(toPostfixExpression(infix, "double linked list"), "double linked list");
			numberStack = new DoubleLinkedListStack<Double>();
		}
		else if(stackType.equals("array"))
		{
			postfixStack = (ArrayStack<String>) setPostfixStack(toPostfixExpression(infix, "array"), "array");
			numberStack = new ArrayStack<Double>(infix.length());
		}
		
		while(!postfixStack.isEmpty())
		{
			if(isOperator(postfixStack.peek()))
			{
				numberStack.push(performOperation(numberStack.pull(), numberStack.pull(), postfixStack.pull()));
			}
			else
			{
				numberStack.push(Double.valueOf(postfixStack.pull()));
			}
		}
		
		return numberStack.pull();
	}
	
	private static double performOperation(double value1, double value2, String operation) throws IOException
	{
		switch(operation.charAt(0))
		{
		case '+':
			return value2 + value1;
		case '-':
			return value2 - value1;
		case '*':
			return value2 * value1;
		case '/':
			return value2 / value1;
		default:
			throw new IOException("Invalid Operator: \"" + operation.charAt(0) + "\" is not a operator.");
		}
	}
	
	private static Stack<String> setInfixStack(String infix, String dataType) throws IOException
	{
		Stack<String> infixStack = null;
		if(dataType.equals("array"))
		{
			infixStack = new ArrayStack<String>(infix.length());
		}
		else if(dataType.equals("single linked list"))
		{
			infixStack = new LinkedListStack<String>();
		}
		else if(dataType.equals("double linked list"))
		{
			infixStack = new DoubleLinkedListStack<String>();
		}
		
		for(int i = 0; i < infix.length(); i++)
		{
			String item;
			
			if(Character.isWhitespace(infix.charAt(i)))
			{
				// Does nothing
			}
			else if(Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')
			{
				int endNumber = 1;
				while(i + endNumber < infix.length() &&
						(Character.isDigit(infix.charAt(i + endNumber)) || infix.charAt(i + endNumber) == '.'))
				{
					endNumber++;
				}
				
				item = new String(infix.substring(i, i + endNumber));
				if(item.indexOf('.') != -1 && item.indexOf('.') != item.length() - 1)
				{
					if(item.substring(item.indexOf('.') + 1).indexOf('.') > -1)
						throw new IOException("Invalid Input: Multiple decimal points.");
				}
				infixStack.push(item);
				i = i + endNumber - 1;
			}
			else
			{
				item = new String(infix.charAt(i) + "");
				infixStack.push(item);
			}
		}
		
		infixStack = reverseStack(infixStack);
		
		return infixStack;
	}
	
	private static Stack<String> setPostfixStack(String postfix, String dataType) throws IOException
	{
		Stack<String> postfixStack = null;
		if(dataType.equals("array"))
		{
			postfixStack = new ArrayStack<String>(postfix.length());
		}
		else if(dataType.equals("single linked list"))
		{
			postfixStack = new LinkedListStack<String>();
		}
		else if(dataType.equals("double linked list"))
		{
			postfixStack = new DoubleLinkedListStack<String>();
		}
		
		for(int i = 0; i < postfix.length(); i++)
		{
			String item;
			
			if(Character.isWhitespace(postfix.charAt(i)))
			{
				// Does nothing
			}
			else if(Character.isDigit(postfix.charAt(i)) || postfix.charAt(i) == '.')
			{
				int endNumber = 1;
				while(i + endNumber < postfix.length() &&
						(Character.isDigit(postfix.charAt(i + endNumber)) || postfix.charAt(i + endNumber) == '.'))
				{
					endNumber++;
				}
				
				item = new String(postfix.substring(i, i + endNumber));
				if(item.indexOf('.') != -1 && item.indexOf('.') != item.length() - 1)
				{
					if(item.substring(item.indexOf('.') + 1).indexOf('.') > -1)
						throw new IOException("Invalid Input: Multiple decimal points.");
				}
				postfixStack.push(item);
				i = i + endNumber - 1;
			}
			else
			{
				item = new String(postfix.charAt(i) + "");
				postfixStack.push(item);
			}
		}
		
		postfixStack = reverseStack(postfixStack);
		
		return postfixStack;
	}
	
	private static boolean isOperator(String string)
	{
		if(string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/") ||
				string.equals("(") || string.equals(")"))
		{
			return true;
		}
		return false;
	}
	
	private static int checkPrecedence(String operator1, String operator2) throws IOException
	{
		int item1 = getPrecedence(operator1);
		int item2 = getPrecedence(operator2);
		
		return item1 - item2;
	}
	
	private static int getPrecedence(String item) throws IOException
	{
		switch(item.charAt(0))
		{
		case '(':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			throw new IOException("Invalid Operator: \"" + item.charAt(0) + "\" is not a operator.");
		}
	}
	
	private static Stack<String> reverseStack(Stack<String> stack)
	{
		Stack<String> reverse = null;
		if(stack instanceof LinkedListStack)
			reverse = new LinkedListStack<String>();
		if(stack instanceof ArrayStack)
			reverse = new ArrayStack<String>(stack.getSize());
		if(stack instanceof DoubleLinkedListStack)
			reverse = new DoubleLinkedListStack<String>();
		
		while(!stack.isEmpty())
		{
			reverse.push(stack.pull());
		}
		
		return reverse;
	}
	
	private static boolean checkParentheses(String infix)
	{
		int indexLeft = 0;
		int indexRight = 0;
		int leftCount = 0;
		int rightCount = 0;
		
		while(indexLeft != infix.length() && infix.indexOf('(', indexLeft) != -1)
		{
			leftCount++;
			indexLeft = infix.indexOf('(', indexLeft) + 1;
		}
 		while(indexRight != infix.length() && infix.indexOf('(', indexRight) != -1)
		{
			rightCount++;
			indexRight = infix.indexOf('(', indexRight) + 1;
		}
		
		if(leftCount != rightCount)
			return false;
		return true;
	}
	
	// TODO: Fix this to work with parantheses
	/*private static boolean checkOperators(String infix)
	{
		for(int i = 1; i < infix.length(); i++)
		{
			if(!Character.isLetterOrDigit(infix.charAt(i)) && !Character.isLetterOrDigit(infix.charAt(i - 1)))
				return false;
		}
		
		return true;
	}*/
	
	private static boolean containsOperators(String infix)
	{
		if(infix.indexOf('+') == -1 && infix.indexOf('-') == -1 && infix.indexOf('*') == -1 &&
				infix.indexOf('/') == -1 && infix.indexOf('^') == -1)
			return false;
		return true;
	}
	
	public static boolean isLegalExpression(String infix) throws IOException
	{
		if(infix.length() < 3)
			return false;
			//throw new IOException("Invalid Expression: Expression too short.");
		if(!containsOperators(infix))
			return false;
			//throw new IOException("Invalid Expression: Contains no operators.");
		if(!checkParentheses(infix))
			return false;
			//throw new IOException("Invalid Expression: Mismatched parentheses.");
		// TODO: Fix check operators so it works with parentheses
		/*if(!checkOperators(infix))
			throw new IOException("Invalid Expression: Operators are adjacent.");*/
		return true;
	}
	
	public static String toPostfixExpression(String infix, String stackType) throws IOException
	{
		Stack<String> stack = null;
		Stack<String> operators = null;
		Stack<String> postfix = null;
		
		if(stackType.equals("single linked list"))
		{
			stack = (LinkedListStack<String>) setInfixStack(infix, "single linked list");
			operators = new LinkedListStack<String>();
			postfix = new LinkedListStack<String>();
		}
		else if(stackType.equals("double linked list"))
		{
			stack = (DoubleLinkedListStack<String>) setInfixStack(infix, "double linked list");
			operators = new DoubleLinkedListStack<String>();
			postfix = new DoubleLinkedListStack<String>();
		}
		else if(stackType.equals("array"))
		{
			stack = (ArrayStack<String>) setInfixStack(infix, "array");
			operators = new ArrayStack<String>(stack.getSize());
			postfix = new ArrayStack<String>(stack.getSize());
		}
		
		while(!stack.isEmpty())
		{
			if(isOperator(stack.peek()))
			{
				if(operators.isEmpty())
				{
					operators.push(stack.pull());
				}
				else if(stack.peek().equals(")"))
				{
					stack.pull();
					
					while(!operators.isEmpty())
					{
						postfix.push(operators.pull());
						if(operators.peek().equals("("))
						{
							operators.pull();
							break;
						}
					}
				}
				else if(checkPrecedence(stack.peek(), operators.peek()) < 0)
				{
					while(!operators.isEmpty() && !stack.peek().equals("("))
					{
						postfix.push(operators.pull());
					}
					
					operators.push(stack.pull());
				}
				else if(operators.peek().equals("(") || checkPrecedence(stack.peek(), operators.peek()) >= 0)
				{
					operators.push(stack.pull());
				}
			}
			else
			{
				postfix.push(stack.pull());
			}
		}
		while(!operators.isEmpty())
		{
			postfix.push(operators.pull());
		}
		
		String expression = new String();
		
		while(!postfix.isEmpty())
		{
			expression = postfix.pull() + " " + expression;
		}
		
		return expression;
	}
}