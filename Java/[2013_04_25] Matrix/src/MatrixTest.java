/**
 * MatrixTest.java
 * [2013/3/28]
 * 
 * A program to simply test the matrix class
 */

import java.io.IOException;

public class MatrixTest
{
	public static void main(String[] args) throws IOException
	{
		// Create 3 new arrays with some values
		Matrix a = new Matrix(new double[][]{	{2, 1, 5},
												{3, 1, 2},
												{0, 2, 1}});
		Matrix b = new Matrix(new double[][]{	{6, 5, 5},
												{2, 2, 2},
												{3, 1, 5}});
		// Create 2 new arrays using the add and subtract methods
		Matrix d = a.getTransposedMatrix();
		// Matrix e = a.add(c).subtract(b);
		
		// Print out arrays to observe results
		System.out.println(	"-A = New Array\n" + a + "\n" +
							"-B = New Array\n" + b + "\n" +
							"-D = A Transposed\n" + d + "\n");
	}
}
