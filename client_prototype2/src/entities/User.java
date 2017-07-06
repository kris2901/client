package entities;

public class User
{

	private String UserID;
	private String UserFirstName;
	private String UserLastName;
	private String UserPassword;
	private boolean IsLogged;
	private int UserPermission;

	public User()
	{
		UserID = null;
		UserFirstName = null;
		UserLastName = null;
		UserPassword = null;
		IsLogged = (Boolean) null;
		UserPermission = (Integer) null;
	}

	public User(String ID, String FirstName, String LastName, String Password, boolean isLogged, int permission)
	{
		UserID = ID;
		UserFirstName = FirstName;
		UserLastName = LastName;
		UserPassword = Password;
		IsLogged = isLogged;
		UserPermission = permission;
	}

	public void setUserID(String ID)
	{
		UserID = ID;
	}

	public String getUserID()
	{
		return UserID;
	}

	public void setUserFirstName(String FirstName)
	{
		UserFirstName = FirstName;
	}

	public String getUserFirstName()
	{
		return UserFirstName;
	}

	public void setUserLastName(String LastName)
	{
		UserLastName = LastName;
	}

	public String getUserLastName()
	{
		return UserLastName;
	}

	public void setUserPassword(String Password)
	{
		UserPassword = Password;
	}

	public String getUserPassword()
	{
		return UserPassword;
	}

	public void setIsLogged(boolean isLogged)
	{
		IsLogged = isLogged;
	}

	public boolean getIsLogged()
	{
		return IsLogged;
	}

	public void setUserPermission(int Permission)
	{
		UserPermission = Permission;
	}

	public int getUserPermission()
	{
		return UserPermission;
	}

}
