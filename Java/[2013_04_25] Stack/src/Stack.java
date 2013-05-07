/**
 * Stack.java
 * [2013_05_05]
 * Jason Khamphila
 *
 * Abstract class for a stack which stores T's.
 * 
 * All code can be found at:
 * https://github.com/Varq/School/tree/master/Java/%5B2013_04_25%5D%20Stack/src
 * or
 * http://goo.gl/7NoCG
 */

import java.io.IOException;
import java.util.NoSuchElementException;

public abstract class Stack<T>
{
	private int index;
	private int size;
	
	/**
	 * Creates a stack with no items
	 */
	public Stack()
	{
		index = -1;
		size = -1;
	}
	
	/**
	 * Creates a stack with no items and a maximum size
	 * @param size Maximum size of stack (mainly for array stacks)
	 * @throws IOException
	 */
	public Stack(int size) throws IOException
	{
		index = -1;
		if(size <= 0)
			throw new IOException("Stack must be size 1 or greater.");
		this.size = size;
	}
	
	/**
	 * Add a new item on the top of the stack
	 * @param item Item to add
	 */
	public abstract void push(T item);
	
	/**
	 * Remove and return an item from the top of the stack
	 * @return Item just removed
	 */
	public abstract T pull();
	
	/**
	 * Get data from the item on the top of the stack
	 * @return Item at the top of the stack
	 */
	public abstract T peek();
	
	/**
	 * Checks if the stack is empty
	 * @return If the stack is empty
	 */
	public boolean isEmpty()
	{
		if(getIndex() < 0)
			return true;
		return false;
	}
	
	/**
	 * Checks if the stack is full
	 * If size == -1, the stack can never be full
	 * @return IF the stack is full
	 */
	public boolean isFull()
	{
		if(getSize() == -1 || getIndex() + 1 < getSize())
			return false;
		return true;
	}
	
	/**
	 * Get the index of the top of the stack
	 * @return Index of the top of the stack
	 */
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * Get the maximum amount of items allowed in the stack
	 * @return Maximum size of stack
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Increase the index of the top of the stack by 1.
	 */
	protected void incrementIndex()
	{
		if(isFull())
			throw new StackOverflowError("Stack is full");
		index++;
	}
	
	/**
	 * Decrease the index of the top of the stack by 1.
	 */
	protected void decrementIndex()
	{
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");
		index--;
	}
}
