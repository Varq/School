import java.io.IOException;
import java.io.RandomAccessFile;

public class MatrixWriter
{
	RandomAccessFile file;
	Matrix matrix;
	
	public MatrixWriter(String fileName, double[][] matrix) throws IOException
	{
		this.matrix = new Matrix(matrix);
		file = new RandomAccessFile(fileName, "rw");
		write();
	}

	public void write() throws IOException
	{
		file.writeInt(matrix.getRowSize());
		file.writeInt(matrix.getColumnSize());
		for(int r = 0; r < matrix.getRowSize(); r++)
		{
			for(int c = 0; c < matrix.getColumnSize(); c++)
			{
				file.writeDouble(matrix.getValue(r, c));
			}
		}
		file.close();
	}
}
