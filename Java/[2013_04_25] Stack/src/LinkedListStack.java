/**
 * LinkedListStack.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 */

import java.util.NoSuchElementException;

public class LinkedListStack<T> extends Stack<T>
{
	private Node top;
	
	public LinkedListStack()
	{
		super();
		top = new Node(null, null);
	}
	
	public LinkedListStack(int size)
	{
		super(size);
		top = new Node(null, null);
	}
	
	public void push(T item)
	{
		if(isFull())
			throw new StackOverflowError("Stack is full");

		incrementIndex();
		Node node = new Node(item, top);
		top = node;
	}
	
	public T pull()
	{
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");

		T item = top.getData();
		top = top.getNext();
		decrementIndex();
		return item;
	}
	
	public T peek()
	{
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");
		
		return top.getData();
	}
	
	private class Node
	{
		public T data;
		private Node next;
		
		public Node(T data, Node next)
		{
			setData(data);
			setNext(next);
		}
		
		public T getData()
		{
			return data;
		}
		
		public void setData(T data)
		{
			this.data = data;
		}
		
		public Node getNext()
		{
			return next;
		}
		
		public void setNext(Node node)
		{
			this.next = node;
		}
	}
}
