package entities;

public class Report {
	
	private String ReportID;
	private String ReportName;
	
	public Report(String ID, String Name)
	{
		ReportID=ID;
		ReportName=Name;
	}
	
	public void setReportID(String ID)
	{
		ReportID=ID;
	}
	
	public String getReportID()
	{
		return ReportID;
	}
	
	public void setReportName(String Name)
	{
		ReportName=Name;
	}
	
	public String getReportName()
	{
		return ReportName;
	}

}
