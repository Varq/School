/*************************
 * JASON KHAMPHILA
 * [2013/1/17]
 *
 * A simply class storing data for a movie in a thearter
 *************************/

public class Movie
{
	private String _name;		// Name of the movie
	private String[] _stars;	// List of stars in the movie
	private String[] _showtimes;	// The times the movie is shown
	private int _lenght;		// The lenght of the movie in minutes
	
	// Default constructor which sets to an UNAVAILABLE movie
	public Movie()
	{
		setName("UNAVAILABLE");
		setStars({"UNAVAILABLE"});
		setShowtimes({"UNAVAILABLE"});
		setLenght(0);
	}
	
	// Overloaded constructor which can accept parameters
	public Movie(String name, String[] stars, String showtimes, int lenght)
	{
		setName(name);
		setStars(stars);
		setShowtimes(showtimes);
		setLenght(lenght);
	}
	
	// Sets string to name of movie
	public void setName(String name)
	{
		_name = name;
	}
	
	// Sets string array to stars of movie
	public void setStars(String[] stars)
	{
		_stars = stars;
	}
	
	// Sets string array to showtimes of movie
	public void setShowtimes(String showtimes)
	{
		_showtimes = showtimes;
	}
	
	// Sets int to lenght of movie
	public void setLenght(int lenght)
	{
		_lenght = lenght;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public String[] getStars()
	{
		return _stars;
	}
	
	public String[] getShowtimes()
	{
		return _showtimes;
	}
	
	public int getLenght()
	{
		return _lenght;
	}
	
	public void displayName()
	{
		System.out.print("Title: " + getName() + "/n");
	}
	
	public void displayStars()
	{
		System.out.print("Starring: " + printArray(getStars()) + "/n");
	}
	
	public void displayShowtimes()
	{
		System.out.print("Showtimes :" + printArray(getShowtimes()) + "/n");
	}
	
	public void displayLenght()
	{
		System.out.print("Lenght: " + getLenght() + "/n");
	}
	
	public void display()
	{
		displayName();
		displayStars();
		displayShowtimes();
		displayLenght();
	}
	
	// Prints a array with commas inbetween each item in the array
	private printArray(String[] array)
	{
		for(int i = 0, i < array.lenght(), i++)
		{
			System.out.print(array[i]);
			
			// if not the last item, add separators between each item.
			if(i < array.lenght())
			 System.out.print(", ");
		}
	}
}