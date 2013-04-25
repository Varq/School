/**
 * A class that stores data and calculates capacity and loss.
 * For use with BandwidthDataAnalyzer.java
 */

public class BandwidthData
{
	private int bandwidth;
	private int[] dbData = new int[3];
	
	public BandwidthData(int m_bandwidth, int m_db1, int m_db2, int m_db3)
	{
		bandwidth = m_bandwidth;
		dbData[0] = m_db1;
		dbData[1] = m_db2;
		dbData[2] = m_db3;
	}
	
	public int getBandwidth()
	{
		return bandwidth;
	}
	
	// Get the dB in the array (array starts at 0)
	public int getDb(int i)
	{
		return dbData[i];
	}
	
	// Calculates the capacity of the bandwidth with a certain dB in the array (array starts at 0)
	public int getCapacity(int i)
	{
		double capacity = (bandwidth * Math.log10(1 + toSignalNoise(dbData[i]))) / Math.log10(2);
		return (int)capacity;
	}
	
	// Calculates the average capacity of all dB entries
	public int getAverageCapacity()
	{
		double average = (getCapacity(0) + getCapacity(1) + getCapacity(2)) / 3;
		return (int)average;
	}
	
	// Calcuates the loss of capacity by percent of a certain dB in the array (array starts at 0)
	public int getLoss(int i, int percent)
	{
		double capacity = getCapacity(i) * ((100 - (double) percent)/100);
		
		return (int)capacity;
	}
	
	// Calculates the average loss of all dB entries
	public int getAverageLoss(int percent)
	{
		double capacity = getAverageCapacity() * ((100 - (double) percent)/100);
		
		return (int)capacity;
	}
	
	// Converts Decibels to Signal/Noise
	public double toSignalNoise(double decibel)
	{
		double sn = 10 * Math.log10(decibel);
		return sn;
	}
	
	// Inverse of toSignalNoise just for completion's sake
	public double toDecibel(double signalNoise)
	{
		double db = Math.pow(10, signalNoise/10);
		return db;
	}
}
