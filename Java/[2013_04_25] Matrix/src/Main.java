/**
 * Main.java
 * [2013/3/28]
 * 
 * A program to simply test the matrix class
 */

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// Names of files to create and use
		String file1 = new String("file1.dat");
		String file2 = new String("file2.dat");
		String file3 = new String("file3.dat");
		
		// Creates new matrices and writes to file
		MatrixWriter write1 = new MatrixWriter(file1,
				new double[][]{	{2, 1, 5},
								{3, 1, 2},
								{0, 2, 1}});
		MatrixWriter write2 = new MatrixWriter(file2,
				new double[][]{	{6, 5, 5},
								{2, 2, 2},
								{3, 1, 5}});
		MatrixWriter write3 = new MatrixWriter(file3,
				new double[][]{	{6, 5},
								{2, 3}});
		
		// Reads matrices from file
		MatrixReader m1 = new MatrixReader(file1);
		MatrixReader m2 = new MatrixReader(file2);
		MatrixReader m3 = new MatrixReader(file3);
		
		// Store matrices from files
		Matrix a = m1.getMatrix();
		Matrix b = m2.getMatrix();
		Matrix c = m3.getMatrix();
		
		// Create 2 new arrays using the add and subtract methods
		Matrix d = a.add(b);
		Matrix e = b.subtract(a);
		Matrix f = c.multiply(3);
		Matrix g = a.multiply(b);
		Matrix h = b.getTransposedMatrix();
		double i = a.getDeterminant();
		double j = b.getCofactor(0, 1);
		
		// Print out arrays to observe results
		System.out.println(	"-A = New Matrix\n" + a + "\n\n" +
							"-B = New Matrix\n" + b + "\n\n" +
							"-C = New Matrix\n" + c + "\n\n" +
							"-D = A + B\n" + d + "\n\n" +
							"-E = B - A 0, 0\n" + e + "\n\n" +
							"-F = C * 3\n" + f + "\n\n" +
							"-G = A * B\n" + g + "\n\n" +
							"-H = B Transposed\n" + h + "\n\n" +
							"-I = Determinant of A\n" + i + "\n\n" +
							"-J = Cofactor of B[0, 1]\n" + j);
	}
}
