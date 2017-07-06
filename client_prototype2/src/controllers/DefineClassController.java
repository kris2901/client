package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.Main;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class DefineClassController - this class is .
 */
public class DefineClassController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Maximum pupil in class text field. */
	@FXML
	private TextField MaximumPupilInClassTextField;

	/** The Enter class name label. */
	@FXML
	private Label EnterClassNameLabel;

	/** The Class name text field. */
	@FXML
	private TextField ClassNameTextField;

	/** The Enter Maximum pupil label. */
	@FXML
	private Label EnterMAximumPupilLabel;

	/** The Class id text field. */
	@FXML
	private TextField ClassIdTextField;

	/** The Enter classID1 label . */
	@FXML
	private Label EnterClassIDLabel1;

	/** The Check availability2 button . */
	@FXML
	private Button CheckAvailabilityButton2;

	/** The Check availability1 button . */
	@FXML
	private Button CheckAvailabilityButton1;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Define class label. */
	@FXML
	private Label DefineClassLabel;

	/** The Fill form label. */
	@FXML
	private Label FillFormLabel;

	/** The Define class button. */
	@FXML
	private Button DefineClassButton;

	/** The array of class ID. */
	private ArrayList<String> classID;

	/** The array of class name. */
	private ArrayList<String> className;

	/** The ClassID. */
	private String ClassID;

	/** The Class NAME. */
	private String ClassNAME;

	/** The Capacity. */
	private String Capacity;

	/** The Class Name flag. */
	private int ClassNameF;

	/** The Class ID Flag. */
	private int ClassIDF;

	/**
	 * Enter maximum pupil for class.
	 *
	 * @param event - enter maximum pupil for class
	 */
	@FXML
	void EnterMaxPupilForClass(ActionEvent event)
	{

	}

	/**
	 * Enter class name.
	 *
	 * @param event - enter class name
	 */
	@FXML
	void EnterClassName(ActionEvent event)
	{

	}

	/**
	 * Define class.
	 *
	 * @param event - checking exceptions of user
	 */
	@FXML
	void DefineClass(ActionEvent event)
	{
		String capacity = MaximumPupilInClassTextField.getText();
		int charFLAG = 0;

		int i;
		for (i = 0; i < capacity.length(); i++)
		{
			if ((capacity.charAt(i) >= 'a' && capacity.charAt(i) <= 'z')
					|| (capacity.charAt(i) >= 'A' && capacity.charAt(i) <= 'Z'))
			{
				charFLAG = 1;
				new Alert(AlertType.ERROR, "The capacity must be a number!", ButtonType.OK).showAndWait();
				break;
			}
		}
		if (charFLAG == 0)
		{
			if (capacity.startsWith("-") || capacity.equals("0") || capacity.isEmpty())
			{
				new Alert(AlertType.ERROR, "Wrong class capacity was entered.", ButtonType.OK).showAndWait();
				return;
			}
			else if ((ClassIDF == 1) && (ClassNameF == 1) && ClassNAME.equals(ClassNameTextField.getText())
					&& ClassID.equals(ClassIdTextField.getText()))
			{
				InsertNewClass();
			}
			else if (ClassIDF == 0)
			{
				if (classID.isEmpty())
				{
					new Alert(AlertType.ERROR, "Class ID field is missing.", ButtonType.OK).showAndWait();
				}
				else
				{
					new Alert(AlertType.ERROR, "Please enter correct class ID again.", ButtonType.OK).showAndWait();
				}
			}
			else if (ClassNameF == 0)
			{
				if (className.isEmpty())
				{
					new Alert(AlertType.ERROR, "Class Name field is missing.", ButtonType.OK).showAndWait();
				}
				else
				{
					new Alert(AlertType.ERROR, "Please enter correct class name again.", ButtonType.OK).showAndWait();
				}
			}
		}

	}

	/**
	 * Insert new class.
	 */
	void InsertNewClass()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Insert New Class");
		data.add("insert");
		data.add("class");
		data.add("classId");
		data.add("className");
		data.add("capacity");
		data.add("AssignedPupils");
		data.add("values");
		data.add(ClassID);
		data.add(ClassNAME);
		Capacity = MaximumPupilInClassTextField.getText();
		data.add(Capacity);
		data.add("0");
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
	 * Back to menu.
	 *
	 * @param event - enter back to menu button
	 */
	@FXML
	void BackToMenu(ActionEvent event) //problem here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	{
		ClassNameF = 0;
		ClassIDF = 0;
		UserWindow.closeUserWindow(getClass(), (Stage) EnterClassNameLabel.getScene().getWindow());
	}

	/**
	 * Check availability no.2.
	 *
	 * @param event - enter class name 
	 */
	@FXML
	void CheckAvailability2(ActionEvent event)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Class Name");
		data.add("select");
		data.add("class");
		data.add("className");
		ClassNAME = ClassNameTextField.getText();
		data.add(ClassNAME);

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
	 * Enter class ID.
	 *
	 * @param event - enter class ID
	 */
	@FXML
	void EnterClassID(ActionEvent event)
	{

	}

	/**
	 * Check availability no.1.
	 *
	 * @param event - enter class ID
	 */
	@FXML
	void CheckAvailability1(ActionEvent event)
	{
		ClassID = ClassIdTextField.getText();

		ArrayList<String> data = new ArrayList<String>();
		data.add("Class ID");
		data.add("select");
		data.add("class");
		data.add("classId");
		data.add(ClassID);

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
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert MaximumPupilInClassTextField != null : "fx:id=\"MaximumPupilInClassTextField\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert EnterClassNameLabel != null : "fx:id=\"EnterClassNameLabel\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert ClassNameTextField != null : "fx:id=\"ClassNameTextField\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert EnterMAximumPupilLabel != null : "fx:id=\"EnterMAximumPupilLabel\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert ClassIdTextField != null : "fx:id=\"ClassIdTextField\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert EnterClassIDLabel1 != null : "fx:id=\"EnterClassIDLabel1\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert CheckAvailabilityButton2 != null : "fx:id=\"CheckAvailabilityButton2\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert CheckAvailabilityButton1 != null : "fx:id=\"CheckAvailabilityButton1\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert DefineClassLabel != null : "fx:id=\"DefineClassLabel\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert FillFormLabel != null : "fx:id=\"FillFormLabel\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";
		assert DefineClassButton != null : "fx:id=\"DefineClassButton\" was not injected: check your FXML file 'SecretaryDefineClass.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SecretaryDefineClass");

		classID = new ArrayList<String>();
		className = new ArrayList<String>();
		ClassNAME = "";
		ClassID = "";
		Capacity = "";
		ClassNameF = 0;
		ClassIDF = 0;

	}

	/**
	 * Handles the answer from the server according to the type of answer.
	 */
	@Override
	public void handleAnswer(Object msg)
	{
		// TODO Auto-generated method stub

		if (msg == null)
		{
			// error
			new Alert(AlertType.ERROR, "Item has not found.", ButtonType.OK).showAndWait();
			return;
		}

		ArrayList<String> arr = (ArrayList<String>) msg;
		String type = arr.remove(0);
		if (type.equals("Class ID"))
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
				classID.add(map.get("classId"));
			}
			if (!classID.contains(ClassID))
			{
				int charFLAG = 0;
				int i;
				for (i = 0; i < ClassID.length(); i++)
				{
					if ((ClassID.charAt(i) >= 'a' && ClassID.charAt(i) <= 'z')
							|| (ClassID.charAt(i) >= 'A' && ClassID.charAt(i) <= 'Z'))
					{
						charFLAG = 1;
						ClassIDF = 0;
						new Alert(AlertType.ERROR, "Class ID must contain digits only!", ButtonType.OK).showAndWait();
						break;
					}
				}
				if (charFLAG == 0)
				{
					if (ClassID.length() == 4)
					{
						ClassIDF = 1;
						new Alert(AlertType.INFORMATION, "Class ID is correct and available.", ButtonType.OK)
								.showAndWait();
					}
					else
					{
						ClassIDF = 0;
						new Alert(AlertType.ERROR, "Class ID must contain 4 digits.", ButtonType.OK).showAndWait();
					}
				}
			}
			else
			{
				ClassIDF = 0;
				new Alert(AlertType.ERROR, "This class ID already exists.", ButtonType.OK).showAndWait();
			}
		}
		if (type.equals("Class Name"))
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
				className.add(map.get("classId"));
			}
			if (!className.contains(ClassNAME))
			{
				ClassNameF = 1;
				new Alert(AlertType.INFORMATION, "Class name is correct and available.", ButtonType.OK).showAndWait();
			}
			else
			{
				ClassNameF = 0;
				new Alert(AlertType.ERROR, "This class name already exists.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Insert New Class"))
		{
			if (arr.size() != 0)
			{
				new Alert(AlertType.INFORMATION, "Class Defined Succesfully!", ButtonType.OK).showAndWait();
				UserWindow.closeUserWindow(getClass(), (Stage) FillFormLabel.getScene().getWindow()); // problem here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}
		}

	}
}
