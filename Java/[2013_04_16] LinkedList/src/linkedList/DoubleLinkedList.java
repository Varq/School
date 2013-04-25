package linkedList;

import java.util.NoSuchElementException;

public class DoubleLinkedList
{
	private Node first;
	private Node last;
	private int length;
	
	public DoubleLinkedList()
	{
		setFirst(new Node(null, null, null));
		setLast(new Node(null, null, null));
		setLength(0);
	}
	
	public boolean isEmpty()
	{
		return getLength() <= 0;
	}
	
	private Node nodeAt(int index)
	{
		if(index >= length || index <= 0)
			throw new IndexOutOfBoundsException("Index: " + index + "Length: " + getLength());
		
		Node node = getFirst().getNext();
		
		for(int i = 0; i < index; i ++)
		{
			node = node.getNext();
		}
		
		return node;
	}
	
	public void addPrev(Node node)
	{
		node.setPrev(node);
	}
	
	public void addNext(Node node)
	{
		node.setNext(node);
	}
	
	public void addNode(Object data, int index)
	{
		if(index <= 0 || index > getLength() + 1)
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Object getData(int i)
	{
		return nodeAt(i).getData();
	}
	
	public Object removeNode(Node node)
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("List is empty");
		}
		
		Object data = node.getData();
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		return data;
	}
	
	public boolean remove(Object data)
	{
		Node node = first.getNext();
		while(node != last)
		{
			if(node.getData().equals(data))
			{
				removeNode(node);
				return true;
			}
			node = node.getNext();
		}
		
		return false;
	}
	
	public Object remove(int i)
	{
		return removeNode(nodeAt(i));
	}
	
	public Node getFirst()
	{
		return first;
	}
	
	public void setFirst(Node node)
	{
		first = node;
	}
	
	public void addFirst(Node node)
	{
		// TODO
	}
	
	public Node getLast()
	{
		return last;
	}
	
	private void setLast(Node node)
	{
		last = node;
	}
	
	public void addLast(Node node)
	{
		// TODO
	}
	
	public int getLength()
	{
		return length;
	}
	
	private void setLength(int length)
	{
		this.length = length;
	}
	
	private class Node
	{
		public Object data;
		private Node prev;
		private Node next;
		
		public Node(Object data, Node next, Node prev)
		{
			setData(data);
			setPrev(prev);
			setNext(next);
		}
		
		public Object getData()
		{
			return data;
		}
		
		public void setData(Object data)
		{
			this.data = data;
		}
		
		public Node getPrev()
		{
			return prev;
		}
		
		public void setPrev(Node node)
		{
			this.prev = node;
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