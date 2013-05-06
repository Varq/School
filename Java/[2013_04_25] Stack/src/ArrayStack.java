/**
 * ArrayStack.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 * 
 */

import java.util.NoSuchElementException;

public class ArrayStack<T> extends Stack<T>
{
	T[] array;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int size)
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
