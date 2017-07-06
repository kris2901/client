package entities;

public class ExeptionalRequest {
	
	private String ExeptionalRequestID;
	private String Type;
	private String OpenRequestDate;
	private String Decision;
	private String Comments;
	
	public ExeptionalRequest(String ID, String type, String Date, String decision, String comments)
	{
		ExeptionalRequestID=ID;
		Type=type;
		OpenRequestDate=Date;
		Decision=decision;
		Comments=comments;
	}
	
	public void setExeptionalRequestID(String ID)
	{
		ExeptionalRequestID=ID;
	}
	
	public String getExeptionalRequestID()
	{
		return ExeptionalRequestID;
	}
	
	public void setType(String type)
	{
		Type=type;
	}
	
	public String getType()
	{
		return Type;
	}
	
	public void setOpenRequestDate(String Date)
	{
		OpenRequestDate=Date;
	}
	
	public String getOpenRequestDate()
	{
		return OpenRequestDate;
	}
	
	public void setDecision(String decision)
	{
		Decision=decision;
	}
	
	public String getDecision()
	{
		return Decision;
	}
	
	public void setComments(String comments)
	{
		Comments=comments;
	}
	
	public String getComments()
	{
		return Comments;
	}

}
