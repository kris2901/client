package entities;

public class Course {
	
	private String CourseID;
	private String CourseName;
	private int WeeklyStudyHours;
	private String StudyUnit;
	private String PreConditions;
	
	public Course(String ID, String Name, int weeklyStudyHours, String studyUnit, String preConditions)
	{
		CourseID=ID;
		CourseName=Name;
		WeeklyStudyHours=weeklyStudyHours;
		StudyUnit=studyUnit;
		PreConditions=preConditions;
	}
	
	public void setCourseID(String ID)
	{
		CourseID=ID;
	}
	
	public String getCourseID()
	{
		return CourseID;
	}
	
	public void setCourseName(String Name)
	{
		CourseName=Name;
	}
	
	public String getCourseName()
	{
		return CourseName;
	}
	
	public void setWeeklyStudyHours(int weeklyStudyHours)
	{
		WeeklyStudyHours=weeklyStudyHours;
	}
	
	public int getWeeklyStudyHours()
	{
		return WeeklyStudyHours;
	}
	
	public void setStudyUnit(String studyUnit)
	{
		StudyUnit=studyUnit;
	}
	
	public String getStudyUnit()
	{
		return StudyUnit;
	}
	
	public void setPreConditions(String preConditions)
	{
		PreConditions=preConditions;
	}
	
	public String getPreConditions()
	{
		return PreConditions;
	}

}
