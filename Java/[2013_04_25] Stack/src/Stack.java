/**
 * Stack.java
 * [2013_05_05]
 * Jason Khamphila
 *
 *
 */

public abstract class Stack<T>
{
	private int index;
	private int size;
	
	public Stack()
	{
		index = -1;
		size = -1;
	}
	
	public Stack(int size)
	{
		index = -1;
		this.size = size;
	}
	
	public abstract void push(T item);
	
	public abstract T pull();
	
	public abstract T peek();
	
	public boolean isEmpty()
	{
		if(getIndex() < 0)
			return true;
		return false;
	}
	
	public boolean isFull()
	{
		if(getSize() == -1 || getIndex() + 1 < getSize())
			return false;
		return true;
	}
	public int getIndex()
	{
		return index;
	}
	
	public int getSize()
	{
		return size;
	}
	
	protected void incrementIndex()
	{
		index++;
	}
	
	protected void decrementIndex()
	{
		index--;
	}
}
