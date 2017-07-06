package entities;

public class Class {
	
	private String ClassID;
	private String ClassName;
	private int ClassCapacity;
	
	public Class(String ID, String Name, int Capacity)
	{
		ClassID=ID;
		ClassName=Name;
		ClassCapacity=Capacity;
	}
	
	public void setClassID(String ID)
	{
		ClassID=ID;
	}
	
	public String getClassID()
	{
		return ClassID;
	}
	
	public void setClassName(String Name)
	{
		ClassName=Name;
	}
	
	public String getClassName()
	{
		return ClassName;
	}
	
	public void setClassCapacity(int Capacity)
	{
		ClassCapacity=Capacity;
	}
	
	public int getClassCapacity()
	{
		return ClassCapacity;
	}

}
