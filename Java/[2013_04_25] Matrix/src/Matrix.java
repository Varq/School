/**
 * Matrix.java
 * [2013/3/28]
 * JASON KHAMPHILA
 * 
 * A object that holds a 2-dimensional array as if it were a matrix.
 * 
 * Supports:
 * -Any size matrix
 * -Addition
 * -Subtraction
 * -Scalar Multiplication
 * -Matrix Multiplication
 * -Transposition
 * -Cofactors
 * -Determinants
 */

import java.io.IOException;

public class Matrix
{
	// An int[][] to hold the data
	private double [][] array;
	
	private int rowSize;
	private int columnSize;
	
	// Creates a new matrix filled with 0s
	public Matrix(int r, int c)
	{
		setRowSize(r);
		setColumnSize(c);
		array = new double[getRowSize()][getColumnSize()];
	}
	
	// Creates a new matrix based on the int[][] passed in
	public Matrix(double[][] arr)
	{
		array = arr;
		setRowSize(arr.length);
		setColumnSize(arr[0].length);
	}
	
	public void setRowSize(int r)
	{
		rowSize = r;
	}
	
	public int getRowSize()
	{
		return rowSize;
	}
	
	public void setColumnSize(int c)
	{
		columnSize = c;
	}
	
	public int getColumnSize()
	{
		return columnSize;
	}
	
	public boolean isSquare()
	{
		return getColumnSize() == getRowSize();
	}
	
	// Returns value at row r, column c of the 3x3
	public double getValue(int r, int c)
	{
		return array[r][c];
	}
	
	// Changes the value at row r, column c to the value passed in
	public void setValue(int r, int c, double value)
	{
		array[r][c] = value;
	}
	
	// Return a matrix of two matrices added
	public Matrix add(Matrix m) throws IOException
	{
		if(getRowSize() != m.getRowSize() || getColumnSize() != m.getColumnSize())
			throw new IOException("Matrix sizes do not match.");
		
		Matrix mx = new Matrix(getRowSize(), getColumnSize());

		// Set values in mx to added totals of this matrix and m
		for(int r = 0; r < getRowSize(); r++)
		{
			for(int c = 0; c < getColumnSize(); c++)
			{
				mx.setValue(r, c, getValue(r, c) + m.getValue(r,  c));
			}	
		}
		
		return mx;
	}
	
	
	// Return a matrix of two matrices subtracted
	public Matrix subtract(Matrix m) throws IOException
	{
		if(getRowSize() != m.getRowSize() || getColumnSize() != m.getColumnSize())
			throw new IOException("Matrix sizes do not match.");
		
		Matrix mx = new Matrix(getRowSize(), getColumnSize());
		
		// Set values in mx to subtracted totals of this matrix and m
		for(int r = 0; r < getRowSize(); r++)
		{
			for(int c = 0; c < getColumnSize(); c++)
			{
				mx.setValue(r, c, getValue(r, c) - m.getValue(r,  c));
			}	
		}
		
		return mx;
	}
	
	// Return a matrix of the matrix multiplied by a single number
	public Matrix multiply(double d)
	{
		Matrix mx = new Matrix(getRowSize(), getColumnSize());
		
		// Multiply all values of this matrix by d
		for(int r = 0; r < getRowSize(); r++)
		{
			for(int c = 0; c < getColumnSize(); c++)
			{
				mx.setValue(r, c, getValue(r,c)  * d);
			}
		}
		
		return mx;
	}
	
	// Return a matrix of two matrices multiplied together
	public Matrix multiply(Matrix m) throws IOException
	{
		if(getColumnSize() != m.getRowSize())
			throw new IOException("Column of first matrix does not match rows of second matrix.");
		
		Matrix mx = new Matrix(getRowSize(), m.getColumnSize());
		
		for(int r = 0; r < getRowSize(); r++)
		{
			for(int c = 0; c < getColumnSize(); c++)
			{
				double cellTotal = 0;
				
				for(int index = 0; index < getRowSize(); index++)
				{
					cellTotal += (getValue(r, index) * m.getValue(index, c));
				}
				
				mx.setValue(r, c, cellTotal);
			}
		}
		
		return mx;
	}
	
	// Return a transposed version of the matrix
	public Matrix getTransposedMatrix()
	{
		Matrix mx = new Matrix(getColumnSize(), getRowSize());
		
		for(int r = 0; r < getRowSize(); r++)
		{
			for(int c = 0; c < getColumnSize(); c++)
			{
				mx.setValue(c,r, getValue(r,c));
			}
		}
		
		return mx;
	}
	
	// Return the determinant of the matrix
	// I'm not entirely sure if this will work on matrices with sizes greater than 3x3
	// I'm not an expert on matrices...
	public double getDeterminant()
	{
		if(!isSquare())
			throw new IllegalArgumentException("Requires a square matrix");
		
		// If size 2, go through 1 iteration, otherwise, run through by size
		int iterations = getRowSize() == 2 ? 1 : getRowSize();
		
		double total = 0;
		
		for(int i = 0; i < iterations; i++)
		{
			// These values are multiplied, so they start at 1
			double plusTotal = 1;
			double minusTotal = 1;
			
			// Run through the matrix getting the diagonals
			for(int r = 0; r < getRowSize(); r++)
			{
				// Get the diagonal value starting from top-left to bottom-right
				int c1 = i + r >= getRowSize() ?
						i + r - getRowSize() : i + r;
				// Get the diagonal value starting from top-right to bottom left
				int c2 = (getRowSize() - 1 - i) - r < 0 ?
						(getRowSize() * 2) - 1 - i - r: (getRowSize() - 1 - i) - r;
				
				// Value to add to total
				double plusValue = getValue(r, c1);
				// Value to subtract from total
				double minusValue = getValue(r, c2);
				
				// Multiply the values to total
				plusTotal *= plusValue;
				minusTotal *= minusValue;
			}
			total += plusTotal - minusTotal;
		}
		return total;
	}
	
	// Returns the minor of the matrix
	public double getMinor(int r, int c)
	{
		if(!isSquare())
			throw new IllegalArgumentException("Requires a square matrix");
		
		// Create a new matrix 1 row size less than the current matrix
		Matrix mx = new Matrix(getRowSize() - 1, getRowSize() - 1);
		
		// Fill the rows and columns with that of the old matrix, but skip values
		//   of row r, and column c
		for(int i = 0; i < getRowSize() - 1; i++)
		{
			for(int j = 0; j < getRowSize() - 1; j++)
			{
				int row = i >= r ? i + 1 : i;
				int column = j >= c ? j + 1 : j;
				mx.setValue(i, j, getValue(row, column));
			}
		}
		
		// If the size is 1x1, return the value in the matrix
		if(mx.getRowSize() == 1)
			return mx.getValue(0, 0);
		// Else get the determinant of mx
		return mx.getDeterminant();
	}
	
	// Returns the cofactor of the matrix, which is just the minor which changes sign
	//   depending on the cell in the matrix
	public double getCofactor(int r, int c)
	{
		if((r + c) % 2 == 0)
			return getMinor(r, c);
		return -getMinor(r, c);
	}
	
	// Prints the matrix to console in the following format:
	// [	0	0	0	]
	// [	0	0	0	]
	// [	0	0	0	]
	public String toString()
	{
		String m = new String();
		
		for(int r = 0; r < getRowSize(); r++)
		{
			m += "[\t";
			
			for(int c = 0; c < getColumnSize(); c++)
			{
				m += getValue(r, c) + "\t";
			}
			
			m += r < getRowSize() - 1 ? "]\n" : "]";
		}
		return m;
	}
}