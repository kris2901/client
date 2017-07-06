package entities;

public class StudyUnit {

	private String StudyUnitID;
	private String StudyUnitName;
	
	public StudyUnit(String ID, String Name)
	{
		StudyUnitID=ID;
		StudyUnitName=Name;
	}
	
	public void setStudyUnitID(String ID)
	{
		StudyUnitID=ID;
	}
	
	public String getStudyUnitID()
	{
		return StudyUnitID;
	}
	
	public void setStudyUnitName(String Name)
	{
		StudyUnitName=Name;
	}
	
	public String getStudyUnitName()
	{
		return StudyUnitName;
	}
}
