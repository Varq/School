/**
 * ElevatorTest.java
 * [2013/4/11]
 * JASON KHAMPHILA
 * 
 * Modify the elevator code to include:
 * -25 floors
 * -No 13th floor
 * -Express 20: Go straight to 20th floor
 * -Express 21: Go straight to 21st floor
 * -At 20th floor, door stays open for 90 sec
 * -At 21st floor, door stays open for 60 sec
 * -At other floors, door stays open for 45 sec
 */

public class ElevatorTest
{
	private static long startTime = System.currentTimeMillis();
	
	public static void main(String[] args)
	{
		int[][] expressFloors 	= {	{20, 90},
									{21, 60}};
		int[] missingFloors		= {13};
		
		Elevator e = new Elevator(1, 25, 1, expressFloors, missingFloors);
		
		boolean f1	= true,
				f2	= true;

		e.enable();
		
		while(timePassed() <= 60)
		{
			e.run();
			
			if(f1 && timePassed() >= 0)
			{
				e.requestFloor(10);
				e.requestFloor(14);
				e.requestFloor(20);
				f1 = false;
			}
			if(f2 && timePassed() >= 4)
			{
				e.requestFloor(20);
				e.requestFloor(1);
				e.requestFloor(18);
				e.requestFloor(4);
				e.requestFloor(25);
				f2 = false;
			}
		}
	}
	
	public static long timePassed()
	{
		return (System.currentTimeMillis() - startTime) / 1000;
	}
}
