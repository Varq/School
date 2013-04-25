/**
 * Creates a byte file with the repeated format:
 * int bandwidth, int dB1, int dB2, int dB3
 * Size = 16
 */

import java.io.*;

public class BandwidthWriter
{
	private String fileName;
	private FileOutputStream fos;
	private BufferedOutputStream bos;
	private DataOutputStream dos;
	
	public BandwidthWriter(String m_fileName) throws FileNotFoundException
	{
		fileName = m_fileName;
		fos = new FileOutputStream(fileName);
		bos = new BufferedOutputStream(fos);
		dos = new DataOutputStream(bos);
	}
	
	public void writeData(int bandwidth, int noise1, int noise2, int noise3) throws IOException
	{
		dos.writeInt(bandwidth);
		dos.writeInt(noise1);
		dos.writeInt(noise2);
		dos.writeInt(noise3);
	}
	
	public void close() throws IOException
	{
		dos.close();
	}
}
