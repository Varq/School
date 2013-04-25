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
			
			m += "]\n";
		}
		return m;
	}
}