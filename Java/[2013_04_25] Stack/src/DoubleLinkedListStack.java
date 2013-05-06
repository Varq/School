/**
 * DoubleLinkedListStack.java
 * [2013_05_05]
 * Jason Khamphila
 * 
 */

import java.util.NoSuchElementException;

public class DoubleLinkedListStack<T> extends Stack<T>
{
	private Node top;
	
	public DoubleLinkedListStack()
	{
		super();
		top = new Node(null, null, null);
	}
	
	public DoubleLinkedListStack(int size)
	{
		super(size);
		top = new Node(null, null, null);
	}

	public void push(T item)
	{
		if(isFull())
			throw new StackOverflowError("Stack is full");

		incrementIndex();
		Node node = new Node(item, top, null);
		top.setPrev(node);
		top = node;
	}
	
	public T pull()
	{	
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");
	
		T item = top.getData();
		top = top.getNext();
		top.setPrev(null);
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
		private Node prev;
		
		public Node(T data, Node next, Node prev)
		{
			setData(data);
			setNext(next);
			setPrev(prev);
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
		
		@SuppressWarnings("unused")
		public Node getPrev()
		{
			return prev;
		}
		
		public void setPrev(Node node)
		{
			
		}
	}
}
