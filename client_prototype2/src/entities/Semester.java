package entities;

public class Semester {

	private String SemesterID;
	private char SemesterType;
	private String SemesterStatus;
	private String SemesterPermission;
	private String SemesterAcademicActivities;
	
	public Semester(String ID, char Type, String Status, String Permission, String AcademicActivities)
	{
		SemesterID=ID;
		SemesterType=Type;
		SemesterStatus=Status;
		SemesterPermission=Permission;
		SemesterAcademicActivities=AcademicActivities;
	}
	
	public void setSemesterID(String ID)
	{
		SemesterID=ID;
	}
	
	public String getSemesterID()
	{
		return SemesterID;
	}
	
	public void setSemesterType(char Type)
	{
		SemesterType=Type;
	}
	
	public char getSemesterType()
	{
		return SemesterType;
	}
	
	public void setSemesterStatus(String Status)
	{
		SemesterStatus=Status;
	}
	
	public String getSemesterStatus()
	{
		return SemesterStatus;
	}
	
	public void setSemesterPermission(String Permission)
	{
		SemesterPermission=Permission;
	}
	
	public String getSemesterPermission()
	{
		return SemesterPermission;
	}
	
	public void setSemesterAcademicActivities(String AcademicActivities)
	{
		SemesterAcademicActivities=AcademicActivities;
	}
	
	public String getSemesterAcademicActivities()
	{
		return SemesterAcademicActivities;
	}
}
