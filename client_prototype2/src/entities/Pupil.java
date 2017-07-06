package entities;

public class Pupil {

	private String LearnedCourses;
	private String ParentID;
	private String ParentName;
	private String RegisterToClassID;
	
	public Pupil(String learnedCourses, String ID, String Name, String ClassID)
	{
		LearnedCourses=learnedCourses;
		ParentID=ID;
		ParentName=Name;
		RegisterToClassID=ClassID;
	}
	
	public void setLearnedCourses(String learnedCourses)
	{
		LearnedCourses=learnedCourses;
	}
	
	public String getLearnedCourses()
	{
		return LearnedCourses;
	}
	
	public void setParentID(String ID)
	{
		ParentID=ID;
	}
	
	public String getParentID()
	{
		return ParentID;
	}
	
	public void setParentName(String Name)
	{
		ParentName=Name;
	}
	
	public String getParentName()
	{
		return ParentName;
	}
	
	public void setRegisterToClassID(String ClassID)
	{
		RegisterToClassID=ClassID;
	}
	
	public String getRegisterToClassID()
	{
		return RegisterToClassID;
	}
}
