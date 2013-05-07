/**
 * ArrayStack.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 * A stack class which stores items in an array.
 * 
 * All code can be found at:
 * https://github.com/Varq/School/tree/master/Java/%5B2013_04_25%5D%20Stack/src
 * or
 * http://goo.gl/7NoCG
 */

import java.io.IOException;
import java.util.NoSuchElementException;

public class ArrayStack<T> extends Stack<T>
{
	T[] array;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int size) throws IOException
	{
		super(size);
		array = (T[]) new Object[size];
	}
	
	public void push(T item)
	{
		if(isFull())
			throw new StackOverflowError("Stack is full.");

		incrementIndex();
		array[getIndex()] = item;
	}

	public T pull()
	{
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");
		
		T item = array[getIndex()];
		array[getIndex()] = null;
		decrementIndex();
		return item;
	}

	public T peek()
	{
		return array[getIndex()];
	}
}
