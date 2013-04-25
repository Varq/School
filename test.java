public class MovieTester
{
	private String nameEx1 = "Funny Bunny";
	private String[] starsEx1 = {"Lil' Bunny", "Big Ol' Bunny"};
	private String showtimesEx1 = {"14:00"};
	private int lenghtEx1 = 102;
	
	private String nameEx2 = "A Movie About A Single Guy Eating Dinner";
	private String[] starsEx2 = {"Norm Al"};
	private String showtimesEx2 = {"17:00"};
	private int lenghtEx2 = 11;
	
	private String nameEx3 = "Computer Hackers Of Tomorrow";
	private String[] starsEx3 = {"Genius Red, Smarty Lee, Slacks McFry"};
	private String showtimesEx3 = {"07:00", "10:00", "13:00", "16:00", "19:00", "21:00"};
	private int lenghtEx3 = 55;
	
	public static void main(String[] args)
	{
		// An example simply displaying movie data
		private Movie movieA = new Movie(nameEx1, starsEx1, showtimesEx1, lenghtEx1);
		movieA.display();
		
		// An example of changing movie data because of an supposed error
		// Also shows setting each member separately
		private Movie movieB = new Movie();
		movieB.setName(nameEx2);
		movieB.setStars(starsEx2);
		movieB.setShowtimes(showtimesEx2);
		movieB.setLenght(lenghtEx2);
		movieB.display();
		
		// An example using each display method on it's on
		// Uses a different order
		private Movie movieC = new Movie(nameEx3, starsEx3, showtimesEx3, lenghtEx3);
		movieC.displayStars();
		movieC.displayName();
		movieC.displayShowtimes();
		movieC.displayLenght();
	}
}