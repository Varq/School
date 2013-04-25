/**
 * Station.java
 * [2013_04_24]
 * JASON KHAMPHILA
 * 
 * A object used to simulate the function of a station which holds Items.
 */

package robot;
import java.util.NoSuchElementException;

public class Station
{
	private int maximum; // Maximum capacity for this station
	private LinkedList<Item> items; // Items the station carries
	
	public Station()
	{
		items = new LinkedList<Item>();
		setMaximum(0);
	}
	
	public Station(int maxCapacity)
	{
		setMaximum(maxCapacity);
		setItems(new LinkedList<Item>());
	}
	
	public Station(int maxCapacity, LinkedList<Item> items)
	{
		setMaximum(maxCapacity);
		setItems(items);
	}
	
	// Create a new station based off another one
	public Station(Station station)
	{
		setMaximum(station.getMaximum());
		setItems(station.getItems());
	}
	
	public int getCount()
	{
		return items.getLength();
	}
	
	public boolean isFull()
	{
		return getCount() >= getMaximum();
	}
	
	public boolean isEmpty()
	{
		return getCount() <= 0;
	}
	
	public int getMaximum()
	{
		return maximum;
	}
	
	public void setMaximum(int maximum)
	{
		this.maximum = maximum;
	}
	
	public LinkedList<Item> getItems()
	{
		return items;
	}
	
	public void setItems(LinkedList<Item> newItems)
	{
		if(newItems.getLength() > getMaximum())
			throw new IndexOutOfBoundsException("Contains too many items.");
		
		this.items = newItems;
	}
	
	// Add a new item to the station
	public boolean addItem(Item item)
	{
		if(items.getLength() == maximum)
		{
			return false;
		}
		else
		{
			items.addLast(item);
			return true;
		}
	}
	
	// Remove the latest item added to the station
	public Item removeItem()
	{
		if(items.isEmpty())
			throw new NoSuchElementException();
		
		else
		{
			return items.removeLast();
		}
	}
	
	public String toString()
	{
		return items.toString();
	}
}