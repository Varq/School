/**
 * LinkedList.java
 * [2013/04/22]
 * JASON KHAMPHILA
 * 
 * A single linked list that can take any object to be used as a type.
 */

package robot;
import java.util.NoSuchElementException;

public class LinkedList<T>
{
	private Node first;
	private Node last;
	private int length;
	
	public LinkedList()
	{
		createNewList();
	}
	
	public void createNewList()
	{
		setFirst(new Node(null, null));
		setLast(new Node(null, null));
		setLength(0);
	}
	
	public boolean isEmpty()
	{
		return getLength() <= 0;
	}
	
	// Returns the node at index
	private Node nodeAt(int index)
	{
		if(index < 0 || index >= getLength())
			throw new IndexOutOfBoundsException("Index: " + index + "\tLength: " + getLength());
		
		// Start from first node and get next node until at index
		Node node = getFirst();
		for(int i = 0; i < index; i ++)
		{
			node = node.getNext();
		}
		return node;
	}
	
	// Get data from a node in the linked list
	public T getData(int index)
	{
		if(index < 0 || index >= getLength())
			throw new IndexOutOfBoundsException("Index: " + index + "\tLength: " + getLength());
		
		return nodeAt(index).getData();
	}
	
	// Add a node to an empty list
	public void addFirstTime(T data)
	{
		Node node = new Node(data, null);
		setFirst(node);
		setLast(getFirst());
		increment();
	}
	
	// Add a node to the very first spot in the list
	public void addFirst(T data)
	{
		if(isEmpty())
		{
			addFirstTime(data);
		}
		else
		{
			Node node = new Node(data, getFirst());
			setFirst(node);
			increment();
		}
	}
	
	// Add a node to the very last spot in the list
	public void addLast(T data)
	{
		if(isEmpty())
		{
			addFirstTime(data);
		}
		else
		{
			Node node = new Node(data, null);
			getLast().setNext(node);
			setLast(node);
			increment();
		}
	}
	
	// Add a node at a particular index
	public void add(T data, int index)
	{
		if(index < 0 || index > getLength())
			throw new IndexOutOfBoundsException();
		
		if(isEmpty())
		{
			addFirstTime(data);
		}
		else if(index == 0)
		{
			addFirst(data);
		}
		else if(index == getLength())
		{
			addLast(data);
		}
		else
		{
			Node node = new Node(data, nodeAt(index));
			nodeAt(index - 1).setNext(node);
			increment();
		}
	}
	
	// Remove the very first node in the list
	public T removeFirst()
	{
		if(isEmpty())
			throw new NoSuchElementException("List is empty");

		T data = getFirst().getData();
		if(getLength() == 1)
		{
			createNewList();
		}
		else
		{
			setFirst(getFirst().getNext());
			decrement();
		}
		return data;
	}
	
	// Removes the very last node in the list
	public T removeLast()
	{
		if(isEmpty())
			throw new NoSuchElementException("List is empty");
		
		T data = getLast().getData();
		if(getLength() == 1)
		{
			createNewList();
		}
		else
		{
			nodeAt(getLength() - 2).setNext(null);
			setLast(nodeAt(getLength() - 2));
			decrement();
		}
		return data;
	}
	
	// Removes the node at index
	public T remove(int index)
	{
		if(isEmpty())
			throw new NoSuchElementException("List is empty");
		if(index < 0 || index >= getLength())
			throw new IndexOutOfBoundsException("Index: " + index + "\tLength: " + getLength());
		
		if(index == 0)
		{
			return removeFirst();
		}
		else if(index == getLength() - 1)
		{
			return removeLast();
		}
		else
		{
			T data = getData(index);
			nodeAt(index - 1).setNext(nodeAt(index).getNext());
			decrement();
			return data;
		}
	}
	
	public Node getFirst()
	{
		return first;
	}
	
	public void setFirst(Node node)
	{
		first = node;
	}
	
	public Node getLast()
	{
		return last;
	}
	
	private void setLast(Node node)
	{
		last = node;
	}
	
	public int getLength()
	{
		return length;
	}
	
	private void setLength(int length)
	{
		this.length = length;
	}
	
	private void increment()
	{
		setLength(getLength() + 1);
	}
	
	private void decrement()
	{
		setLength(getLength() - 1);
	}
	
	public String toString()
	{
		String str = new String();
		for(int i = 0; i < getLength(); i++)
		{
			str = str + getData(i);
			if(i != getLength() - 1)
				str = str + "\n";
		}
		return str;
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