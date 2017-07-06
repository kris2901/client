package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class UserController - User Controller
 */
public class UserController implements IController
{

	/** The resources . */
	@FXML
	private ResourceBundle resources;

	/** The location . */
	@FXML
	private URL location;

	/** The password text filed . */
	@FXML
	private PasswordField passwordTextField;

	/** The login button . */
	@FXML
	private Button loginBtn;
	
    @FXML
    private Button setBTN;

	/** The user ID text field . */
	@FXML
	private TextField userIdTextField;

	/** The AISFHS label. */
	@FXML
	private Label AISFHSlable;

	/** The ASH label. */
	@FXML
	private Label ASHlable;

	/** The permission. */
	private String permission;

	/** The current user id. */
	public static String CurrentUserID;

	private String Access;
	private String isLooged;

	/**
	 * login.
	 *
	 * @param event - enter Login
	 */
	@FXML
	void login(ActionEvent event)
	{
		try
		{
			Main.client = new SchoolClient(Main.ip, Integer.parseInt(Main.port));
			authorizeUser(userIdTextField.getText(), passwordTextField.getText());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			new Alert(AlertType.ERROR, "Server is not available", ButtonType.OK).showAndWait();
		}
	}
	
    @FXML
    void openSet(ActionEvent event) {

    	UserWindow.createUserWindow((Stage) userIdTextField.getScene().getWindow(), "DefineSettings", getClass());
    }

	/**
	 * Adding course to semester.
	 *
	 * @param name - user ID
	 * @param pass -  user password
	 */
	public void authorizeUser(String name, String pass)
	{
		Main.client.controller = this;
		ArrayList<String> data = new ArrayList<String>();
		data.add("login");
		data.add("select");
		data.add("user");
		data.add("userId");
		data.add(name);
		data.add("userPassword");
		data.add(pass);
		try
		{
			Main.client.sendToServer(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * open menu.
	 */
	void OpenMenu()
	{
		if(isLooged.equals("1"))
		{
			new Alert(AlertType.ERROR, "User Already Connected", ButtonType.OK).showAndWait();
		}
		else if (permission.equals("1"))
		{
			updateLogin();
			UserWindow.createUserWindow((Stage) loginBtn.getScene().getWindow(), "SystemManagerMainWindow", getClass());
		}
		else if (permission.equals("2"))
		{
			updateLogin();
			UserWindow.createUserWindow((Stage) loginBtn.getScene().getWindow(), "SchoolManagerMainWindow", getClass());
		}
		else if (permission.equals("3"))
		{
			updateLogin();
			UserWindow.createUserWindow((Stage) loginBtn.getScene().getWindow(), "SecretaryMainWindow", getClass());
		}
		else if (permission.equals("4"))
		{
			updateLogin();
			UserWindow.createUserWindow((Stage) loginBtn.getScene().getWindow(), "TeacherMainWindow", getClass());
		}
		else if (permission.equals("5"))
		{
			CheckPermisssion();
			
		}
		else if (permission.equals("6"))
		{
			updateLogin();
			UserWindow.createUserWindow((Stage) loginBtn.getScene().getWindow(), "PupilMainWindow", getClass());
		}

	}
	
	void updateLogin()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("update connection");
		data.add("update");
		data.add("user");
		data.add("isLogged");
		data.add("1");
		data.add("conditions");
		data.add("userId");
		data.add(CurrentUserID);
		
 		try
 		{
 			Main.client.sendToServer(data);
 		}
 		catch (IOException e)
 		{
 			e.printStackTrace();
 		}
	}

	void CheckPermisssion()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("check parent permission");
		data.add("select");
		data.add("pupil");
		data.add("parentID");
		data.add(CurrentUserID);
		try
		{
			Main.client.sendToServer(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	void cheackIfParentBlock()
	{
		if (Access.equals("0"))
		{
			updateLogin();
			UserWindow.createUserWindow((Stage) loginBtn.getScene().getWindow(), "Parent", getClass());
		}
		else
		{
			new Alert(AlertType.ERROR, "User Is Blocked", ButtonType.OK).showAndWait();
		}
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'loginFrame.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginFrame.fxml'.";
        assert userIdTextField != null : "fx:id=\"userIdTextField\" was not injected: check your FXML file 'loginFrame.fxml'.";
        assert AISFHSlable != null : "fx:id=\"AISFHSlable\" was not injected: check your FXML file 'loginFrame.fxml'.";
        assert setBTN != null : "fx:id=\"setBTN\" was not injected: check your FXML file 'loginFrame.fxml'.";
        assert ASHlable != null : "fx:id=\"ASHlable\" was not injected: check your FXML file 'loginFrame.fxml'.";

		//Main.client.controller = this;

		CurrentUserID = "";
		permission = "";
		Access = "";
		isLooged="";
	}

	/**
	 * Handles the answer from the server according to the type of answer.
	 */
	@Override
	public void handleAnswer(Object result)
	{
		if (result == null)
		{
			// error
			return;
		}
		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);

		if (type.equals("login"))
		{
			if (arr.size() == 0)
			{
				new Alert(AlertType.ERROR, "Wrong Password OR ID", ButtonType.OK).showAndWait();
			}
			else
			{

				for (String row : arr)
				{
					String[] cols = row.split(";");
					HashMap<String, String> map = new HashMap<>();
					for (String col : cols)
					{
						String[] field = col.split("=");
						map.put(field[0], field[1]);
					}
					permission = map.get("permission");
					CurrentUserID = map.get("userId");
					Main.userId = CurrentUserID;
					isLooged=map.get("isLogged");
				}
				OpenMenu();
			}
		}

		if (type.equals("check parent permission"))
		{
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				Access = map.get("ParentAccess");
			}
			
			cheackIfParentBlock();
		}
	}
}
