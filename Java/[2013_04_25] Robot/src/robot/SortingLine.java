/**
 * SortingLine.java
 * [2013_04_24]
 * JASON KHAMPHILA
 * 
 * A simulation of a sorting line, where a robot picks up items from the home station
 * and places them in the right slots.
 * Sorting specifications:
 * -If item serial number first digit == 5, put in slot 5
 * -Else if item weight >= 700 weight, put in slot 7
 * -Else if item serial number is even, put in non-special non-full left slot
 * -Else (if item serial number is odd), put in non-special, non-full right slot
 * 
 * All slots except for 0, 5, and 7 have a max capacity of 8.
 */

package robot;

public class SortingLine
{
	private final int NUMBER_OF_STATIONS = 8;
	private Station[] stations = new Station[NUMBER_OF_STATIONS];
	private int maxCapacity; // Maximum capacity per station (excluding home station)
	private PickUpRobot robot;
	private int leftStation; // Left most station position to store items, increments if full
	private int rightStation; // Right most station position to store items, decrements if full
	private LinkedList<StorageStation> offStations; // Stations taken off the line when stations get full
	
	public SortingLine(int stationMaxCapacity, LinkedList<Item> itemsInStartingStation)
	{
		setCapacity(stationMaxCapacity);
		
		robot = new PickUpRobot(0, this);
		
		// Create the stations in the array
		for(int i = 0; i < NUMBER_OF_STATIONS; i++)
		{
			switch(i)
			{
			// The first station get items and a capacity equal to the number of items
			case 0:
				stations[i] = new Station(itemsInStartingStation.getLength());
				stations[i].setItems(itemsInStartingStation);
				break;
			// Other stations just get a maximum capacity set
			default:
				stations[i] = new Station(getCapacity());
				break;
			}
		}
		// Sets left and right most stations for storage
		setLeftStation(1);
		setRightStation(6);
		
		// Creates the StorageStation LinkedList if needed
		offStations = new LinkedList<StorageStation>();
	}
	
	public Station getStation(int station)
	{
		return stations[station];
	}
	
	public void setStation(int i, Station station)
	{
		stations[i] = station;
	}
	
	public LinkedList<StorageStation> getStorageStations()
	{
		return offStations;
	}
	
	// Current time used for simulation if timed
	public long getTime()
	{
		return System.currentTimeMillis();
	}
	
	public Station[] getStations()
	{
		return stations;
	}
	
	public PickUpRobot getRobot()
	{
		return robot;
	}
	
	public int getCapacity()
	{
		return maxCapacity;
	}
	
	public void setCapacity(int cap)
	{
		this.maxCapacity = cap;
	}
	
	// Get number of stations (Currently useless, because hard coded)
	public int getLength()
	{
		return stations.length;
	}
	
	public int getLeftStation()
	{
		return leftStation;
	}
	
	public void setLeftStation(int station)
	{
		this.leftStation = station;
	}
	
	// Moves the left target station to the next for storage
	public void incrementLeft()
	{
		setLeftStation(getLeftStation() + 1);
		// If next station is station 5, skip it
		while(getLeftStation() == 5 /*|| getLeftStation() == 7*/) // Will never get to 7
			setLeftStation(getLeftStation() + 1);
	}
	
	public int getRightStation()
	{
		return rightStation;
	}
	
	public void setRightStation(int station)
	{
		this.rightStation = station;
	}
	
	// Moves the left target station to the next for storage
	public void incrementRight()
	{
		setRightStation(getRightStation() - 1);
		// If next station is station 5, skip it
		while(getRightStation() == 5 /*|| getRightStation() == 7*/) // Will never get to 7
			setRightStation(getRightStation() - 1);
	}
	
	// Robot has alerted that a station is full
	// TODO: Add in time for simulation for station change
	public void alertFull(int station)
	{
		// If left station was full
		if(station == getLeftStation())
		{
			// Set up next left station
			incrementLeft();
			// If left station is also now right station, empty all full non-special stations
			if(getLeftStation() == getRightStation())
			{
				for(int i = 1; i < getLeftStation(); i++)
				{
					// Checks if station 5
					if(i == 5)
					{
						if(i + 1 < getLeftStation())
							i++;
						else
							break;
					}
					
					// Throws all full left stations into storage and creates new ones for the sorting line
					Station storage = new Station(getStation(i));
					offStations.addLast(new StorageStation("EVEN SERIAL", storage));
					setStation(i, new Station(getCapacity()));
				}
				// Reset left station
				setLeftStation(1);
				
				// If right station is full, start storing
				if(getStation(getRightStation()).isFull())
				{
					for(int i = 6; i >= getRightStation(); i--)
					{
						// Check if station 5
						if(i == 5)
						{
							if(i - 1 > getRightStation())
								i--;
							else
								break;
						}
						
						// Throws all full right stations into storage and creates new ones for the sorting line 
						Station storage = new Station(getStation(i));
						offStations.addLast(new StorageStation("ODD SERIAL", storage));
						setStation(i, new Station(getCapacity()));
					}
					
					// Sets the last unempty right station to the right most station
					setStation(6, new Station(getStation(getRightStation())));
					setStation(getRightStation(), new Station());
					// Reset right station
					setRightStation(6);
				}
			}
		}
		else if(station == getRightStation())
		{
			// Set up next right station
			incrementRight();
			// If left station is also now right station, empty all full non-special stations
			if(getLeftStation() == getRightStation())
			{
				for(int i = 6; i > getRightStation(); i--)
				{
					// Checks if station 5
					if(i == 5)
					{
						if(i - 1 > getRightStation())
							i--;
						else
							break;
					}
					// Throws all full right stations into storage and creates new ones for the sorting line
					Station storage = new Station(getStation(i));
					offStations.addLast(new StorageStation("ODD SERIAL", storage));
					setStation(i, new Station(getCapacity()));
				}
				// Reset right station
				setRightStation(6);

				// If right station is full, start storing
				if(getStation(getLeftStation()).isFull())
				{
					for(int i = 1; i <= getLeftStation(); i++)
					{
						// Checks if station 5
						if(i == 5)
						{
							if(i + 1 < getLeftStation())
								i++;
							else
								break;
						}

						// Throws all full left stations into storage and creates new ones for the sorting line
						Station storage = new Station(getStation(i));
						offStations.addLast(new StorageStation("EVEN SERIAL", storage));
						setStation(i, new Station(getCapacity()));
					}
					// Sets the last unempty right station to the right most station
					setStation(6, new Station(getStation(getLeftStation())));
					setStation(getLeftStation(), new Station());
					//Reset left station
					setLeftStation(1);
				}
			}
		}
		else if(station == 5)
		{
			// Throw the 5th station into storage and create a new on in it's stead
			Station storage = new Station(getStation(5));
			offStations.addLast(new StorageStation("5~ SERIAL", storage));
			setStation(5, new Station(getCapacity()));
		}
		else if(station == 7)
		{
			// Throw the 7th station into storage and create a new on in it's stead
			Station storage = new Station(getStation(7));
			offStations.addLast(new StorageStation("700 WEIGHT", storage));
			setStation(7, new Station(getCapacity()));
		}
	}
	
	public void act()
	{
		getRobot().act();
	}
}