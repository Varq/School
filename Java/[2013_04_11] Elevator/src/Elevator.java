/**
 * Elevator.java
 * [2013/04/18]
 *
 * A class to simulate an elevator using a user-defined linked list.
 */

public class Elevator
{
	private final int MILLI_SCALE	= 100; // Speed scale (10 times faster than real time)
	private final int FLOOR_TIME	= 45; // Default time for doors open
	private final long MOVE_TIME	= 1 * MILLI_SCALE; // Time between floors
	
	private long currentTime;
	private long lastMoveTime = 0; // Time since last floor change
	private long holdTime; // Time elevator is holding for
	private long releaseTime; // Time at which to release the elevator from a hold
	
	public static enum Direction	{ UP, DOWN }; // Direction elevator is going
	public static enum Movement		{ MOVING, HOLDING }; // Elevator status
	public static enum Door			{ OPEN, CLOSED }; // Door status
	
	private Movement movement;
	private Door door;
	private Direction direction;
	
	private Floor currentFloor = null;
	private Floor bottomFloor;
	private Floor topFloor;
	
	boolean running = false;
	
	// Create an elevator of any size and starting point.
	// TODO: Add a second constructor which doesn't include express or missing parameters.
	public Elevator(int bottom, int top, int start, int[][] express, int[] missing)
	{
		// Error checking
		if(bottom > top)
			throw new IllegalArgumentException("Bottom floor must be greater than top floor.");
		else if(bottom == top)
			throw new IllegalArgumentException("Elevator requires more than 1 floor.");
		if(start > top || start < bottom)
			throw new IllegalArgumentException("Starting floor is not in bounds.");
		
		// Start making floors from the bottom
		for(int i = bottom; i <= top; i++)
		{
			// Check missing floors as to not create them
			boolean exists = true;
			for(int m = 0; m < missing.length; m++)
			{
				if(i == missing[m])
					exists = false;
				
				// Error checking
				if(start == missing[m])
					throw new IllegalArgumentException("Cannot start on a missing floor.");
				if(bottom == missing[m] || top == missing[m])
					throw new IllegalArgumentException("Missing floors include top or bottom.");
			}
			
			// Don't make floor 0
			if(i == Floor.NULL_FLOOR)
				exists = false;
			
			// Missing floors have been checked, create a floor
			if(exists)
			{
				boolean expressFloor = false;
				int floorTime = FLOOR_TIME; // Default the floor time
				Floor lastFloor = getCurrentFloor();
				
				// Check if this floor should be a express floor
				for(int e = 0; e < express.length; e++)
				{
					if(i == express[e][0])
					{
						expressFloor = true;
						floorTime = express[e][1];
					}
				}
				
				// Create the floor
				Floor f = new Floor(i, floorTime, lastFloor, null, expressFloor);
				setCurrentFloor(f);
				
				// If creating the first floor, don't set lower floor's above floor
				if(i != bottom)
					getCurrentFloor().getLastFloor().setNextFloor(getCurrentFloor());
			}
			
			// Set bottom floor
			if(i == bottom)
				bottomFloor = getCurrentFloor();
		}
		
		// Set top floor
		topFloor = getCurrentFloor();

		// Set the starting floor
		while(getCurrentFloor().getFloor() != start)
		{
			if(getCurrentFloor().getFloor() > start)
			{
				setCurrentFloor(getCurrentFloor().getLastFloor());
			}
			else if(getCurrentFloor().getFloor() < start)
			{
				setCurrentFloor(getCurrentFloor().getNextFloor());
			}
		}
		
		// Set elevator starting conditions
		setDoor(Door.CLOSED);
		setMovement(Movement.HOLDING);
		setDirection(Direction.UP);
	}
	
	public void enable()
	{
		running = true;
	}
	
	public void hold()
	{
		running = false;
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	// Return current system time for simulation
	private long getCurrentTime()
	{
		return currentTime;
	}
	
	private void updateTime()
	{
		currentTime = System.currentTimeMillis();
	}
	
	// Set hold and release time of elevator
	private void setHoldTime(int seconds)
	{
		holdTime = seconds * MILLI_SCALE;
		releaseTime = getCurrentTime() + holdTime;
	}
	
	private long getHoldTime()
	{
		return holdTime;
	}
	
	private long getReleaseTime()
	{
		return releaseTime;
	}
	
	private long getLastMoveTime()
	{
		return lastMoveTime;
	}
	
	private void setLastMoveTime(long time)
	{
		lastMoveTime = time;
	}
	
	public Floor getCurrentFloor()
	{
		return currentFloor;
	}
	
	private void setCurrentFloor(Floor f)
	{
		currentFloor = f;
	}
	
	public Movement getMovement()
	{
		return movement;
	}
	
	public void setMovement(Movement state)
	{
		movement = state;
	}
	
	public Door getDoor()
	{
		return door;
	}
	
	public void setDoor(Door state)
	{
		door = state;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void setDirection(Direction state)
	{
		direction = state;
	}
	
	// Checks to see if any express floors have been requested
	public boolean isExpressMode()
	{
		Floor index = bottomFloor;
		
		while(index.getNextFloor() != null)
		{
			if(index.isExpress() && index.isRequested())
				return true;
			index = index.getNextFloor();
		}
		
		return false;
	}
	
	// Returns the next floor to go to
	public int getNextFloor()
	{
		// Checks express floors first
		if(isExpressMode())
		{
			// The following bit compares the distance of the express floors
			// and goes to the closest one first
			Floor indexUp = getCurrentFloor(),
					indexDown = getCurrentFloor();
			boolean stopCount = false;
			
			while(indexUp != null && !stopCount)
			{
				if(indexUp.isExpress() && indexUp.isRequested())
				{
					stopCount = true;
					break;
				}
				else if(indexUp == topFloor)
				{
					indexUp.setFloor(Floor.NULL_FLOOR);
				}
				indexUp = indexUp.getNextFloor();
			}
			
			stopCount = false;
			
			while(indexDown != null && !stopCount)
			{
				if(indexDown.isExpress() && indexDown.isRequested())
				{
					stopCount = true;
					break;
				}
				else if(indexDown == topFloor)
				{
					indexDown.setFloor(Floor.NULL_FLOOR);
				}
				indexDown = indexDown.getLastFloor();
			}
			
			// Chooses which express floor to go to first by distance
			if(indexUp == null)
				return indexDown.getFloor();
			else if(indexDown == null)
				return indexUp.getFloor();
			else
			{
				if( Math.abs(getCurrentFloor().getFloor() - indexDown.getFloor()) >
						Math.abs(indexUp.getFloor() - getCurrentFloor().getFloor()) )
					return indexDown.getFloor();
				else
					return indexUp.getFloor();
			}
		}
		
		// If the elevator is going up, check above requested floors
		else if(direction == Direction.UP)
		{
			Floor index = getCurrentFloor();
			while(index != null)
			{
				if(index.isRequested())
				{
					return index.getFloor();
				}
				index = index.getNextFloor();
			}
			
			// No more above floors requested, change directions
			setDirection(Direction.DOWN);
		}
		// If the elevator is going down, check below requested floors
		else if(direction == Direction.DOWN)
		{
			Floor index = getCurrentFloor();
			while(index != null)
			{
				if(index.isRequested())
				{
					return index.getFloor();
				}
				index = index.getLastFloor();
			}

			// No more below floors requested, change directions
			setDirection(Direction.UP);
		}
		
		// Return bad floor, just in case my logic screwed up
		return Floor.NULL_FLOOR;
	}
	
	// Set a floor to be requested
	public void requestFloor(int newFloor)
	{
		// Error checking
		if(newFloor > topFloor.getFloor() || newFloor < bottomFloor.getFloor())
			throw new IllegalArgumentException("Floor " + newFloor + " not in bounds.");
		
		// Start from the bottom floor to find floor to request
		Floor index = bottomFloor;
		
		while(index.getFloor() < topFloor.getFloor() &&
				index.getFloor() != newFloor)
		{
			index = index.getNextFloor();	
		}
		
		// Error checking
		if(index.getFloor() != newFloor)
			throw new IllegalArgumentException("Floor " + newFloor + " does not exist.");
		
		// Floor exists, set it to requested
		index.setRequested(true);
		
		System.out.println("[REQ]\t\t" + newFloor);
	}
	
	// Hold the elevator open
	private void holdElevator()
	{
		setMovement(Movement.HOLDING);
		setDoor(Door.OPEN);
		setHoldTime(getCurrentFloor().getTime());
		hold();
		System.out.println(getStatus());
	}
	
	// Resume from a hold
	private void resumeElevator()
	{
		setDoor(Door.CLOSED);
		enable();
		System.out.println(getStatus());
	}
	
	// Move toward the next floor
	private void goToNextFloor()
	{
		int nextFloor = getNextFloor();
		
		// Simulated time between floors
		if(getCurrentTime() - getLastMoveTime() >= MOVE_TIME)
		{
			// If not on the right floor, change floor
			if(getCurrentFloor().getFloor() != nextFloor
					&& nextFloor != Floor.NULL_FLOOR)
			{
				// Next floor is above, go up
				if(getCurrentFloor().getFloor() < nextFloor)
				{
					setDirection(Direction.UP);
					setCurrentFloor(getCurrentFloor().getNextFloor());
					System.out.println(getStatus());
				}
				// Next floor is below, go down
				else if(getCurrentFloor().getFloor() > nextFloor)
				{
					setDirection(Direction.DOWN);
					setCurrentFloor(getCurrentFloor().getLastFloor());
					System.out.println(getStatus());
				}
				
				// Change elevator status
				setMovement(Movement.MOVING);
				
				// Set time for simulated time
				setLastMoveTime(getCurrentTime());
			}
		}
	}
	
	// Simple string to print to console
	public String getStatus()
	{
		String s = new String("[");
		
		if(isRunning())
		{
			s = s + "ACTIVE] ";
			if(getDirection() == Direction.UP)
				s = s + "U";
			else
				s = s + "D";
		}
		else
			s = s + " HOLD ] -" + getHoldTime()/MILLI_SCALE;
		
		return s + "\t" + getCurrentFloor().getFloor();
	}
	
	// Elevators acts
	public void run()
	{
		// Update time for simulation
		updateTime();
		
		if(isRunning())
		{
			// If on the right floor
			if(getNextFloor() == getCurrentFloor().getFloor())
			{
				// If the floor is requested, open the door
				// Also unrequest the floor now
				if(getCurrentFloor().isRequested())
				{
					holdElevator();
					getCurrentFloor().setRequested(false);
				}
			}
			
			// If door is closed, start moving to next floor
			if(getDoor() == Door.CLOSED)
			{
				goToNextFloor();
			}
		}
		// Elevator is now holding
		else
		{
			// If past hold time, resume elevator movement
			if(getCurrentTime() >= getReleaseTime())
			{
				resumeElevator();
			}
			
			// If current floor is requested while holding, unrequest it.
			if(getNextFloor() == getCurrentFloor().getFloor())
			{
				if(getCurrentFloor().isRequested())
				{
					getCurrentFloor().setRequested(false);
				}
			}
		}
	}
}