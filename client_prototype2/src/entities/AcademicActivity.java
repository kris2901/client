package entities;


public class AcademicActivity {
	
	private String AcademicActivityID;
	private String AcademicActivityName;
	private String TypeOfActivity;

	public AcademicActivity(String ID, String Name, String Type)
	{
		AcademicActivityID=ID;
		AcademicActivityName=Name;
		TypeOfActivity=Type;
	}
	
	public void setAcademicActivityID(String ID)
	{
		AcademicActivityID=ID;
	}
	
	public String getAcademicActivityID()
	{
		return AcademicActivityID;
	}
	
	public void setAcademicActivityName(String Name)
	{
		AcademicActivityName=Name;
	}
	
	public String getAcademicActivityName()
	{
		return AcademicActivityName;
	}
	
	public void setTypeOfActivity(String Type)
	{
		TypeOfActivity=Type;
	}
	
	public String getTypeOfActivity()
	{
		return TypeOfActivity;
	}
	

}
