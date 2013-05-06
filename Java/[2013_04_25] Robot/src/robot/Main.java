/**
 * Main.java
 * [2013_04_24]
 * JASON KHAMPHILA
 * 
 * A class to test the SortingLine class and all other robot related components
 */

package robot;

public class Main
{
	public static void main(String[] args)
	{
		int itemsToSort = 20; // Number of items for the robot to sort
		int stationCapacity = 8; // Max capacity each station can hold
		
		// Create random Items to put in slot 0
		LinkedList<Item> items = new LinkedList<Item>();
		for(int i = 0; i < itemsToSort; i++)
		{
			int id = (int)(Math.random() * (Item.MAX_SERIAL_NUMBER + 1));
			int weight = (int)(Math.random() * 1000) + 1;
			
			items.addLast(new Item(id, weight));
		}
		
		// Create a new sorting line
		SortingLine line = new SortingLine(stationCapacity, items);
		
		// Print stations before sort (All items should start in station 0)
		System.out.println("=[SORTING START]=");
		for(int i = 0; i < line.getStations().length; i ++)
		{
			System.out.println("[STATION " + i + "]\n" + line.getStation(i) + "\n");
		}
		
		// Start the robot
		line.getRobot().start();
		// Get the last status update from the robot
		String lastUpdate = line.getRobot().toString();
		
		while(line.getRobot().isRunning())
		{
			// If the status has changed, change the string
			String update = line.getRobot().toString();
			// Take actions
			line.act();
			if(!lastUpdate.equals(update))
			{
				System.out.println(line.getRobot());
				lastUpdate = update;
			}
		}
		
		// Print out stations and storage stations
		System.out.println("=[SORTING COMPLETE]=");
		for(int i = 0; i < line.getStations().length; i ++)
		{
			System.out.println("[STATION " + i + "]\n" + line.getStation(i) + "\n");
		}
		System.out.println("====================");
		for(int i = 0; i < line.getStorageStations().getLength(); i++)
		{
			System.out.println("[STORAGE " + line.getStorageStations().getData(i).getLabel() + "]\n" + line.getStorageStations().getData(i) + "\n");
		}
	}
}