/**
 * 
 * 
 * JASON KHAMPHILA
 */

public class Employee
{
	private String name;
	private int id;
	private double payrate;
	private double hoursWorked;
	
	public Employee(String m_name, int m_id, double m_payrate, double m_hoursWorked)
	{
		setName(m_name);
		setId(m_id);
		setPayrate(m_payrate);
		setHoursWorked(m_hoursWorked);
	}
	
	public void setName(String m_name)
	{
		name = m_name;
	}
	
	public void setId(int m_id)
	{
		id = m_id;
	}
	
	public void setPayrate(double m_payrate)
	{
		payrate = m_payrate;
	}
	
	public void setHoursWorked(double m_hoursWorked)
	{
		hoursWorked = m_hoursWorked;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public double getPayrate()
	{
		return payrate;
	}
	
	public double getHoursWorked()
	{
		return hoursWorked;
	}
}