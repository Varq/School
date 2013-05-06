import java.io.IOException;
import java.io.RandomAccessFile;


public class MatrixReader
{
	RandomAccessFile file;
	Matrix matrix;
	
	public MatrixReader(String fileName) throws IOException
	{
		file = new RandomAccessFile(fileName, "r");
		setMatrix();
	}
	
	public void setMatrix() throws IOException
	{
		matrix = new Matrix(file.readInt(), file.readInt());
		
		for(int r = 0; r < matrix.getRowSize(); r++)
		{
			for(int c = 0; c < matrix.getColumnSize(); c++)
			{
				matrix.setValue(r, c, file.readDouble());
			}
		}
		file.close();
	}
	
	public Matrix getMatrix()
	{
		return matrix;
	}
}
