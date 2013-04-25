/**
 * Creates a byte file using BandwidthWriter.java
 */

import java.io.*;

public class BandwidthTest
{
	public static void main(String[] args) throws IOException
	{
		// Write the bandwidth data to the file
		final int BANDWIDTH_A	= 520;
		final int NOISE_A_1		= 30;
		final int NOISE_A_2		= 20;
		final int NOISE_A_3		= 10;
		
		final int BANDWIDTH_B	= 390;
		final int NOISE_B_1		= 30;
		final int NOISE_B_2		= 22;
		final int NOISE_B_3		= 18;
		
		BandwidthWriter bandWrite = new BandwidthWriter("bandwidth.dat");
		bandWrite.writeData(BANDWIDTH_A, NOISE_A_1, NOISE_A_2, NOISE_A_3);
		bandWrite.writeData(BANDWIDTH_B, NOISE_B_1, NOISE_B_2, NOISE_B_3);
		bandWrite.close();
	}
}
