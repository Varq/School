public class FileTest
{
	public static void main(String[] args) throws java.io.IOException
	{
		FileCounter fc = new FileCounter("output.txt");	
		
		fc.getUserInput();
		fc.outputToFile();
		fc.getCounts();
	}
}