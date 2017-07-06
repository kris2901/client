package entities;

public class Assignment {
	
	private String AssignmentName;
	private String DueDate;
	private String Format;
	
	public Assignment(String Name, String Date, String format)
	{
		AssignmentName=Name;
		DueDate=Date;
		Format=format;
	}

	public void setAssignmentName(String Name)
	{
		AssignmentName=Name;
	}
	
	public String getAssignmentName()
	{
		return AssignmentName;
	}
	public void setDueDate(String Date)
	{
		DueDate=Date;
	}
	
	public String getDueDate()
	{
		return DueDate;
	}
	
	public void setFormat(String format)
	{
		Format=format;
	}
	
	public String getFormat()
	{
		return Format;
	}
	

}
