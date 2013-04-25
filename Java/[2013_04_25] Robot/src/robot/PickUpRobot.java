/**
 * PickUpRobot.java
 * [2013_04_24]
 * JASON KHAMPHILA
 * 
 * A robot simulation to pick up items on a sorting line.
 * The robot can pick up and drop off items if it is in the slot (by moving forward, the robot can be in the slot).
 * The robot must be out of the slot to move left and right (by moving backward, the robot can be out of the slot).
 * Robot goes to 0 (home slot) position to pick up single items.
 * Items > 700 weight will be dropped in slot 7 (last slot).
 * Items with 5 as the first digit in the serial number will be dropped in slot 5.
 * For any other cases, items with even number serial numbers will be dropped in the left most slot,
 *   while odd numbers will be dropped in the right most slot (excluding slots 0, 5, and 7).
 */

package robot;

public class PickUpRobot
{
	private SortingLine line;
	
	private int position;
	private int home;
	private boolean inStation;
	private Item item;
	private int target;
	
	private long lastAct;
	// For the sake of testing purposing, time will not matter, so it set to 0
	private final long TIME_BETWEEN_ACT = 0;
	
	private boolean running;
	private boolean ready;
	
	public PickUpRobot(int home, SortingLine line)
	{
		setHome(home);
		setPosition(home);
		setLine(line);
		setCurrentItem(null); // The robot should start with no item
		moveForward(); // The robot starts inside of the slot
	}
	
	public SortingLine getLine()
	{
		return line;
	}
	
	// Returns current time
	public long getTime()
	{
		return getLine().getTime();
	}
	
	// Set the time when the robot has last acted
	// Used to simulate time
	private void setLastActTime()
	{
		this.lastAct = getTime();
	}
	
	// Get the time the robot has last acted
	// Used to simulate time
	public long getLastActTime()
	{
		return lastAct;
	}
	
	// If the robot is free to take action
	// Used to simulate time
	public boolean canAct()
	{
		return (getTime() - getLastActTime()) >= TIME_BETWEEN_ACT;
	}
	
	public void setLine(SortingLine line)
	{
		this.line = line;
	}
	
	// Gets the current station the robot is in (not station number)
	public Station getStation()
	{
		return line.getStation(getPosition());
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public int getHome()
	{
		return home;
	}
	
	public void setHome(int newHome)
	{
		this.home = newHome;
	}
	
	// Used to allow robot to take action after it has determined it target location
	public void setReady(boolean ready)
	{
		this.ready = ready;
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	public void start()
	{
		running = true;
	}
	
	public void stop()
	{
		running = false;
	}
	
	public boolean isReady()
	{
		return ready;
	}
	
	private void setPosition(int position)
	{
		this.position = position;
	}
	
	public int getLeftStation()
	{
		return getLine().getLeftStation();
	}
	
	public int getRightStation()
	{
		return getLine().getRightStation();
	}
	
	public boolean inStation()
	{
		return inStation;
	}
	
	// If it currently is carrying an item
	public boolean hasItem()
	{
		return !(getCurrentItem() == null);
	}
	
	public Item getCurrentItem()
	{
		return item;
	}
	
	private void setCurrentItem(Item item)
	{
		this.item = item;
	}
	
	public int getTarget()
	{
		return target;
	}
	
	public void setTarget(int target)
	{
		this.target = target;
	}
	
	// Determines which slot to move to next
	public void determineTarget()
	{
		// Is currently not carrying an item
		if(!hasItem()) 
		{
			// If is in home slot and it's empty
			if(getPosition() == getHome() && getStation().isEmpty())
			{
				// Set invalid target, and stop robot
				setTarget(-1);
				stop();
				return;
			}
			// Not at home slot, go back home to get a new item
			else
			{
				setTarget(getHome());
			}
		}
		// Carrying a item
		else
		{
			// If first digit in item serial number is 5, go to station 5
			if(getCurrentItem().getMostSignificantBit() == 5)
			{
				setTarget(5);
			}
			// If current item weights 700 weight or more, go to station 7
			else if(getCurrentItem().getWeight() >= 700)
			{
				setTarget(7);
			}
			// If serial number is even, go to non-full left station
			else if(getCurrentItem().isSerialNumberEven())
			{
				setTarget(getLeftStation());
			}
			// If serial number is odd, go to non-full right station
			else
			{
				setTarget(getRightStation());
			}
		}
		
		// Robot is ready to take action
		setReady(true);
	}
	
	public boolean moveLeft()
	{
		if(!inStation() && getPosition() - 1 >= 0)
		{
			setPosition(getPosition() - 1);
			return true;
		}
		return false;
	}
	
	public boolean moveRight()
	{
		if(!inStation() && getPosition() + 1 <= line.getLength() - 1)
		{
			setPosition(getPosition() + 1);
			return true;
		}
		return false;
	}
	
	// Move into the station
	public boolean moveForward()
	{
		if(inStation())
			return false;
		else
		{
			inStation = true;
			return true;
		}
	}
	
	// Move out of the station
	public boolean moveBackward()
	{
		if(!inStation())
			return false;
		else
		{
			inStation = false;
			return true;
		}
	}
	
	// Pick up item at current station
	// Returns false if not possible
	public boolean pickUpItem()
	{
		if(inStation() && !getStation().isEmpty())
		{
			setCurrentItem(getStation().removeItem());
			return true;
		}
		return false;
	}
	
	// Drop off item at current station
	// Return false if not possible
	public boolean dropOffItem()
	{
		if(inStation() && !getStation().isFull())
		{
			getStation().addItem(getCurrentItem());
			setCurrentItem(null);
			return true;
		}
		return false;
	}
	
	// Perform actions
	// To be put in a loop in main
	public void act()
	{
		// If started and enough time has passed to simulate
		if(isRunning() && canAct())
		{
			// If not ready, find the target
			if(!isReady())
			{
				determineTarget();
			}
			if(isReady())
			{
				if(inStation())
				{
					if(getPosition() == getTarget())
					{
						// If ready, in the correct station, and has an item
						if(hasItem())
						{
							// Drop off item
							dropOffItem();
							// If station is full, alert sorting line (Sets left/right station)
							if(getStation().isFull())
							{
								getLine().alertFull(getPosition());
							}
							
							// Needs to find new target station
							setReady(false);
						}
						else
						{
							// Pick up an item (will fail to pick up an item when complete)
							pickUpItem();
							
							// Needs to find a new target station
							setReady(false);
						}
					}
					
					// If in the non-target station, move out of station
					else
					{
						moveBackward();
					}
				}
				
				// If out of a station, move to target station
				else
				{
					if(getPosition() > getTarget())
					{
						moveLeft();
					}
					else if(getPosition() < getTarget())
					{
						moveRight();
					}
					// If in the right position, move into station
					else
					{
						moveForward();
					}
				}
			}
			// Set last time for time simulation
			setLastActTime();
		}
	}
	
	public String toString()
	{
		return "Position:\t" + getPosition() +
				"\nIn Station:\t" + inStation() +
				"\nItem:\t\t" + getCurrentItem();
	}
}
