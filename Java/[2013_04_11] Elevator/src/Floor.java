/**
 * Floor.java
 * [2013/04/18]
 * 
 * A linked list object that holds data for an elevator floor.
 */

public class Floor
{
	public static final int NULL_FLOOR = 0;
	private int level;
	private int floorTime;
	private boolean request;
	private boolean express;
	private Floor above;
	private Floor below;
	
	public Floor()
	{
		setFloor(NULL_FLOOR);
		setTime(0);
		setRequested(false);
		setExpress(false);
		setNextFloor(null);
		setLastFloor(null);
	}
	
	public Floor(int floor, int floorTime, Floor last, Floor next, boolean express)
	{
		setFloor(floor);
		setTime(floorTime);
		setRequested(false);
		setExpress(express);
		setLastFloor(last);
		setNextFloor(next);
	}
	
	public int getFloor()
	{
		return level;
	}
	
	public void setFloor(int floor)
	{
		level = floor;
	}
	
	public int getTime()
	{
		return floorTime;
	}
	
	public void setTime(int time)
	{
		floorTime = time;
	}
	
	public boolean isRequested()
	{
		return request;
	}
	
	public void setRequested(boolean ask)
	{
		request = ask;
	}
	
	public boolean isExpress()
	{
		return express;
	}
	
	public void setExpress(boolean fast)
	{
		express = fast;
	}
	
	public Floor getNextFloor()
	{
		return above;
	}
	
	public void setNextFloor(Floor f)
	{
		above = f;
	}
	
	public Floor getLastFloor()
	{
		return below;
	}
	
	public void setLastFloor(Floor f)
	{
		below = f;
	}
}
