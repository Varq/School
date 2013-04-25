import java.io.*;

public class StringTest
{
	public static void main(String[] args) throws IOException
	{
		String inputFile = "input.txt";
		FileReader fr = new FileReader(inputFile);
		BufferedReader br = new BufferedReader(fr);
		
		StringBuffer strb = new StringBuffer();
		
		while(br.ready())
		{
			strb.append(br.readLine() + "\n");
		}
		
		String outputFile = "output.txt";
		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		System.out.println("Input: " + inputFile + "\n" +
				"Output: " + outputFile + "\n");
		
		String str = new String(strb);
		
		StringManipulator mani = new StringManipulator(str); 
		
		System.out.println("===ORIGINAL===\n" + mani.getOriginalString());
		
		System.out.println("Counts Before Manipulation:");
		mani.countEverything();
		System.out.println(mani.counts());
		
		mani.manipulate();
		
		System.out.println("===EDITED===\n" + mani.getEditedString());
		System.out.println("Counts After Manipulation:");
		mani.countEverything();
		System.out.println(mani.counts());
		
		// Prints to output file
		pw.println(mani.getEditedString());
		pw.close();
	}
}
