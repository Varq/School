/**
 * StorageStation.java
 * [2013_04_24]
 * JASON KHAMPHILA
 * 
 * A object that extends Station to just add a label
 */

package robot;

public class StorageStation extends Station
{
	private String label;
	
	public StorageStation(String label, Station station)
	{
		super(station);
		setLabel(label);
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
}
