package entities;

public class EvaluationForm {
	
	private String GeneralComments;
	private int FinalGrade;
	private String Format;
	private String FileNumber;
	
	public EvaluationForm(String Comments, int Grade, String format, String Number)
	{
		GeneralComments=Comments;
		FinalGrade=Grade;
		Format=format;
		FileNumber=Number;
	}
	
	public void setGeneralComments(String Comments)
	{
		GeneralComments=Comments;
	}
	
	public String getGeneralComments()
	{
		return GeneralComments;
	}
	
	public void setFinalGrade(int Grade)
	{
		FinalGrade=Grade;
	}
	
	public int getFinalGrade()
	{
		return FinalGrade;
	}
	
	public void setFormat(String format)
	{
		Format=format;
	}
	
	public String getFormat()
	{
		return Format;
	}
	
	public void setFileNumber(String Number)
	{
		FileNumber=Number;
	}
	
	public String getFileNumber()
	{
		return FileNumber;
	}

	
}
