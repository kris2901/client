package entities;

public class PupilFile {
	
	private String FileNumber;
	private String UserID;
	
	public PupilFile(String Number, String ID)
	{
		FileNumber=Number;
		UserID=ID;
	}

	public void setFileNumber(String Number)
	{
		FileNumber=Number;
	}
	
	public String getFileNumber()
	{
		return FileNumber;
	}
	
	public void setUserID(String ID)
	{
		UserID=ID;
	}
	
	public String getUserID()
	{
		return UserID;
	}
}
