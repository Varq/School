/**
 * ExpressionEvaluator.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 * A program which can take an infix expression and evaluate it.
 * It can also turn an infix expression into a postfix expression (Strings).
 * Supports: numbers, +, -, *, /, () (for both precedence and multiplication)
 * 
 * All code can be found at:
 * https://github.com/Varq/School/tree/master/Java/%5B2013_04_25%5D%20Stack/src
 * or
 * http://goo.gl/7NoCG
 */

import java.io.IOException;

public class ExpressionEvaluator
{
	public static enum StackType { ARRAY, SINGLE_LINKED, DOUBLE_LINKED };
	public static enum ExpressionType { INFIX, POSTFIX };
	
	/**
	 * Evaluates an expression
	 * @param expression Expression to evaluate
	 * @param stackType Type of stack to use
	 * @param expressionType Type of expression
	 * @return result of the expression
	 * @throws IOException
	 */
	public static double evaluate(String expression, StackType stackType, ExpressionType expressionType) throws IOException
	{
		Stack<String> postfixStack = null;
		Stack<Double> numberStack = null;
		
		// Check which stack type is used
		// Then create a postfix stack and empty number stack to be used later
		if(expressionType.equals(ExpressionType.INFIX))
		{
			if(stackType.equals(StackType.SINGLE_LINKED))
			{
				postfixStack = (LinkedListStack<String>) setExpressionStack(toPostfixExpression(expression, StackType.SINGLE_LINKED), StackType.SINGLE_LINKED);
				numberStack = new LinkedListStack<Double>();
			}
			else if(stackType.equals(StackType.DOUBLE_LINKED))
			{
				postfixStack = (DoubleLinkedListStack<String>) setExpressionStack(toPostfixExpression(expression, StackType.DOUBLE_LINKED), StackType.DOUBLE_LINKED);
				numberStack = new DoubleLinkedListStack<Double>();
			}
			else if(stackType.equals(StackType.ARRAY))
			{
				postfixStack = (ArrayStack<String>) setExpressionStack(toPostfixExpression(expression, StackType.ARRAY), StackType.ARRAY);
				numberStack = new ArrayStack<Double>(expression.length());
			}
		}
		else if(expressionType.equals(ExpressionType.POSTFIX))
		{
			if(stackType.equals(StackType.SINGLE_LINKED))
			{
				postfixStack = (LinkedListStack<String>) setExpressionStack(expression , StackType.SINGLE_LINKED);
				numberStack = new LinkedListStack<Double>();
			}
			else if(stackType.equals(StackType.DOUBLE_LINKED))
			{
				postfixStack = (DoubleLinkedListStack<String>) setExpressionStack(expression, StackType.DOUBLE_LINKED);
				numberStack = new DoubleLinkedListStack<Double>();
			}
			else if(stackType.equals(StackType.ARRAY))
			{
				postfixStack = (ArrayStack<String>) setExpressionStack(expression, StackType.ARRAY);
				numberStack = new ArrayStack<Double>(expression.length());
			}
		}
		
		// While the postfix stack isn't empty, push results into the number stack
		while(!postfixStack.isEmpty())
		{
			// Found an operator, perform operation on the two top items in the number stack
			// Push the result back into the number stack
			if(isOperator(postfixStack.peek()))
			{
				numberStack.push(performOperation(numberStack.pull(), numberStack.pull(), postfixStack.pull()));
			}
			// Found a number, push it onto the number stack
			else
			{
				numberStack.push(Double.valueOf(postfixStack.pull()));
			}
		}
		
		// Postfix stack is now empty, number stack will have the result in it
		return numberStack.pull();
	}
	
	/**
	 * Evaluates an infix expression
	 * @param expression Expression to evaluate
	 * @param stackType Type of stack to use
	 * @return result of the expression
	 * @throws IOException
	 */
	public static double evaluate(String expression, StackType stackType) throws IOException
	{
		return evaluate(expression, stackType, ExpressionType.INFIX);
	}
	
	/**
	 * Perform a operation on the two values
	 * EX: (value1, value2, "-") yields (value2 - value1)
	 * @param value1
	 * @param value2
	 * @param operation +, -, *, /
	 * @return Result of value2 operated on value1
	 * @throws IOException
	 */
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
	
	/**
	 * Creates an stack based on an expression string.
	 * @param expression Expression to turn into stack
	 * @param singleLinked Type of stack to use
	 * @return Stack of expression
	 * @throws IOException
	 */
	private static Stack<String> setExpressionStack(String expression, StackType singleLinked) throws IOException
	{
		// Create a stack of requested stack type
		Stack<String> expressionStack = null;
		if(singleLinked.equals(StackType.ARRAY))
		{
			expressionStack = new ArrayStack<String>(expression.length());
		}
		else if(singleLinked.equals(StackType.SINGLE_LINKED))
		{
			expressionStack = new LinkedListStack<String>();
		}
		else if(singleLinked.equals(StackType.DOUBLE_LINKED))
		{
			expressionStack = new DoubleLinkedListStack<String>();
		}
		
		// Go through the string given
		for(int i = 0; i < expression.length(); i++)
		{
			// Item to push into expression stack
			String item;
			
			// Found whitespace
			if(Character.isWhitespace(expression.charAt(i)))
			{
				// Do nothing
			}
			// Found a number
			else if(Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')
			{
				// Finds out how long the number is
				int endNumber = 1;
				while(i + endNumber < expression.length() &&
						(Character.isDigit(expression.charAt(i + endNumber)) || expression.charAt(i + endNumber) == '.'))
				{
					endNumber++;
				}
				
				// Sets item to the number
				item = new String(expression.substring(i, i + endNumber));
				if(item.indexOf('.') != -1 && item.indexOf('.') != item.length() - 1)
				{
					if(item.substring(item.indexOf('.') + 1).indexOf('.') > -1)
						throw new IOException("Invalid Input: Multiple decimal points.");
				}
				
				// Push item to stack
				expressionStack.push(item);
				// Set i to end of the number just added from the string
				i = i + endNumber - 1;
			}
			// Found an operator
			else
			{
				// Push it to the stack
				item = new String(expression.charAt(i) + "");
				expressionStack.push(item);
			}
		}
		
		// Reverse stack so it's in proper order
		expressionStack = reverseStack(expressionStack);
		
		return expressionStack;
	}
	
	/**
	 * Checks a string to see if it a valid operator
	 * @param string operator
	 * @return if the string is an operator
	 */
	private static boolean isOperator(String string)
	{
		if(string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/") ||
				string.equals("(") || string.equals(")"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks the precedence of two operators
	 * @param operator1
	 * @param operator2
	 * @return A value greater than 0 if operator1 is higher, equal to 0 if they're the same, or less than 0 if operator1 is lower
	 * @throws IOException
	 */
	private static int checkPrecedence(String operator1, String operator2) throws IOException
	{
		int item1 = getPrecedence(operator1);
		int item2 = getPrecedence(operator2);
		
		return item1 - item2;
	}
	
	/**
	 * Gets the precedence of an operator
	 * @param item
	 * @return
	 * @throws IOException
	 */
	private static int getPrecedence(String item) throws IOException
	{
		switch(item.charAt(0))
		{
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '(':
			return 3;
		default:
			throw new IOException("Invalid Operator: \"" + item.charAt(0) + "\" is not a operator.");
		}
	}
	
	/**
	 * Reverses a stack
	 * @param stack
	 * @return The reversed stack
	 * @throws IOException 
	 */
	private static Stack<String> reverseStack(Stack<String> stack) throws IOException
	{
		// Checks the stack type
		Stack<String> reverse = null;
		if(stack instanceof LinkedListStack)
			reverse = new LinkedListStack<String>();
		if(stack instanceof ArrayStack)
			reverse = new ArrayStack<String>(stack.getSize());
		if(stack instanceof DoubleLinkedListStack)
			reverse = new DoubleLinkedListStack<String>();
		
		// Push all contents of stack onto reverse stack
		while(!stack.isEmpty())
		{
			reverse.push(stack.pull());
		}
		
		return reverse;
	}
	
	/**
	 * Check if the parentheses match in an infix expression
	 * TODO: Fix so it checks if parentheses are in the right place EX: )2+4(
	 * @param infix Expression string
	 * @return true if the parentheses all match
	 */
	private static boolean checkParentheses(String infix)
	{
		int indexLeft = infix.indexOf('(');
		int indexRight = infix.indexOf(')');
		int leftCount = 0;
		int rightCount = 0;
		
		// Count the number of left parentheses
		while(indexLeft != infix.length() && indexLeft != -1)
		{
			leftCount++;
			indexLeft = infix.indexOf('(', indexLeft + 1);
		}
		// Count the number of right parentheses
 		while(indexRight != infix.length() && indexRight != -1)
		{
			rightCount++;
			indexRight = infix.indexOf(')', indexRight + 1);
		}
		
 		// If parentheses count doesn't match, false, else true
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
	
	/**
	 * Check to see if the string contains any valid operators
	 * @param infix Expression string
	 * @return true if there are operators
	 */
	private static boolean containsOperators(String infix)
	{
		if(infix.indexOf('+') == -1 && infix.indexOf('-') == -1 && infix.indexOf('*') == -1 &&
				infix.indexOf('/') == -1 && infix.indexOf('(') == -1 && infix.indexOf(')') == -1
				/*&& infix.indexOf('^') == -1*/)
			return false;
		return true;
	}
	
	/**
	 * Checks if legal expression
	 * TODO: Still needs a lot of work
	 * @param infix Expression string
	 * @return true if valid expression
	 * @throws IOException
	 */
	public static boolean isLegalExpression(String infix) throws IOException
	{
		if(infix.length() < 3)
			throw new IOException("Invalid Expression: Expression too short.");
		if(!containsOperators(infix))
			throw new IOException("Invalid Expression: Contains no operators.");
		if(!checkParentheses(infix))
			throw new IOException("Invalid Expression: Mismatched parentheses.");
		// TODO: Fix check operators so it works with parentheses
		/*if(!checkOperators(infix))
			throw new IOException("Invalid Expression: Operators are adjacent.");*/
		return true;
	}
	
	/**
	 * Creates a postfix expression string from an infix expression string
	 * @param infix Expression string
	 * @param stackType Type of stack to use
	 * @return string of postfix expression
	 * @throws IOException
	 */
	public static String toPostfixExpression(String infix, StackType stackType) throws IOException
	{
		isLegalExpression(infix);
		// Check stack type to use
		Stack<String> stack = null;
		Stack<String> operators = null;
		Stack<String> postfix = null;
		if(stackType.equals(StackType.SINGLE_LINKED))
		{
			stack = (LinkedListStack<String>) setExpressionStack(infix, StackType.SINGLE_LINKED);
			operators = new LinkedListStack<String>();
			postfix = new LinkedListStack<String>();
		}
		else if(stackType.equals(StackType.DOUBLE_LINKED))
		{
			stack = (DoubleLinkedListStack<String>) setExpressionStack(infix, StackType.DOUBLE_LINKED);
			operators = new DoubleLinkedListStack<String>();
			postfix = new DoubleLinkedListStack<String>();
		}
		else if(stackType.equals(StackType.ARRAY))
		{
			stack = (ArrayStack<String>) setExpressionStack(infix, StackType.ARRAY);
			operators = new ArrayStack<String>(stack.getSize());
			postfix = new ArrayStack<String>(stack.getSize());
		}
		
		boolean lastWasNumber = false;
		boolean lastWasRightParen = false;
		
		// While stack is not empty
		while(!stack.isEmpty())
		{
			// Found an operator
			if(isOperator(stack.peek()))
			{
				// Found a "(" next to a number, throw a "*" in the operator stack
				if(stack.peek().equals("(") && lastWasNumber)
				{
					operators.push("*");
					operators.push(stack.pull());
					lastWasRightParen = false;
				}
				// Operator stack is empty
				else if(operators.isEmpty())
				{
					// Push to operator stack
					operators.push(stack.pull());
					lastWasRightParen = false;
				}
				// Found a ')'
				else if(stack.peek().equals(")"))
				{
					// Take ')' off the stack
					stack.pull();
					
					// Pull items in operator stack until '(' is found
					while(!operators.peek().equals("("))
					{
						postfix.push(operators.pull());
					}
					// Take '(' off operator stack
					operators.pull();
					lastWasRightParen = true;
				}
				// Operator from stack is of lesser precedence than operator in operator stack
				else if(checkPrecedence(stack.peek(), operators.peek()) < 0)
				{
					// While not empty and haven't seen '(', push all items from operator stack to postfix stack
					while(!operators.isEmpty() && !operators.peek().equals("("))
					{
						postfix.push(operators.pull());
					}
					
					operators.push(stack.pull());
					lastWasRightParen = false;
				}
				// Operator from stack is of higher precedence than operator in operator stack
				else if(checkPrecedence(stack.peek(), operators.peek()) >= 0)
				{
					// Push from stack to operator stack
					operators.push(stack.pull());
					lastWasRightParen = false;
				}
				lastWasNumber = false;
			}
			// Found a number, push to postfix stack
			else
			{
				if(lastWasRightParen)
					operators.push("*");
				postfix.push(stack.pull());
				lastWasNumber = true;
				lastWasRightParen = false;
			}
		}
		// Push all remaining items in operator stack to postfix stack
		while(!operators.isEmpty())
		{
			postfix.push(operators.pull());
		}
		
		// Create a string from the postfix expression
		String expression = new String();
		while(!postfix.isEmpty())
		{
			expression = postfix.pull() + " " + expression;
		}
		
		return expression;
	}
}