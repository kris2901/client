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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;


/**
 * The Class ViewAllDataController this class display all data.
 */
public class ViewAllDataController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Display exceptional requests. */
	@FXML
	private MenuItem DisplayExeptionalRequests;

	/** The Display academic activities. */
	@FXML
	private MenuItem DisplayAcademicActivities;

	/** The Menu button. */
	@FXML
	private MenuButton MenuButton;

	/** The Display assignments. */
	@FXML
	private MenuItem DisplayAssignments;

	/** The Display secretary. */
	@FXML
	private MenuItem DisplaySecretary;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Display teachers. */
	@FXML
	private MenuItem DisplayTeachers;

	/** The Display semesters. */
	@FXML
	private MenuItem DisplaySemesters;

	/** The Display system manager. */
	@FXML
	private MenuItem DisplaySystemManager;

	/** The Display parents. */
	@FXML
	private MenuItem DisplayParents;

	/** The Data combo box. */
	@FXML
	private ComboBox<String> DataComboBox;

	/** The Display classes. */
	@FXML
	private MenuItem DisplayClasses;

	/** The Display pupils. */
	@FXML
	private MenuItem DisplayPupils;

	/** The View label. */
	@FXML
	private Label ViewLabel;

	/** The Choose label. */
	@FXML
	private Label ChooseLabel;

	/** The data. */
	private String DATA;

	/**
	 * View all options.
	 *
	 * @param event - View all options
	 */
	@FXML
	void ViewAllOptions(ActionEvent event)
	{

	}

	/**
	 * Display all pupil.
	 *
	 * @param event - Display all pupil
	 */
	@FXML
	void DisplayAllPupil(ActionEvent event)
	{
		MenuButton.setText(DisplayPupils.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Pupil Data");
		data.add("select");
		data.add("user");
		data.add("permission");
		data.add("6");

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
	 * Display all parents.
	 *
	 * @param event - Display all parents
	 */
	@FXML
	void DisplayAllParents(ActionEvent event)
	{
		MenuButton.setText(DisplayParents.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Parent Data");
		data.add("select");
		data.add("user");
		data.add("permission");
		data.add("5");

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
	 * Display all teachers.
	 *
	 * @param event - Display all teachers
	 */
	@FXML
	void DisplayAllTeachers(ActionEvent event)
	{
		MenuButton.setText(DisplayTeachers.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Teacher Data");
		data.add("select");
		data.add("user");
		data.add("permission");
		data.add("4");

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
	 * Display all classes.
	 *
	 * @param event - Display all classes
	 */
	@FXML
	void DisplayAllClasses(ActionEvent event)
	{
		MenuButton.setText(DisplayClasses.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Class Data");
		data.add("select");
		data.add("class");
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
	 * Display all academic activities.
	 *
	 * @param event - Display all academic activities
	 */
	@FXML
	void DisplayAllAcademicActivities(ActionEvent event)
	{
		MenuButton.setText(DisplayAcademicActivities.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Academic Activities Data");
		data.add("select");
		data.add("academic_activity");
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
	 * Display all semester.
	 *
	 * @param event - Display all semester
	 */
	@FXML
	void DisplayAllSemester(ActionEvent event)
	{
		MenuButton.setText(DisplaySemesters.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Semester Data");
		data.add("select");
		data.add("semester");
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
	 * Display all exceptional requests.
	 *
	 * @param event - Display all exceptional requests
	 */
	@FXML
	void DisplayAllExeptionalRequests(ActionEvent event)
	{
		MenuButton.setText(DisplayExeptionalRequests.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Exeptional Requests Data");
		data.add("select");
		data.add("exceptional_request");
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
	 * Display all assignments.
	 *
	 * @param event - Display all assignments
	 */
	@FXML
	void DisplayAllAssignments(ActionEvent event)
	{
		MenuButton.setText(DisplayAssignments.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Assignments Data");
		data.add("select");
		data.add("assignment");
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
	 * Display secretary data.
	 *
	 * @param event - Display secretary data
	 */
	@FXML
	void DisplaySecretaryData(ActionEvent event)
	{
		MenuButton.setText(DisplaySecretary.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("Secretary Data");
		data.add("select");
		data.add("user");
		data.add("permission");
		data.add("3");

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
	 * Display system manager data.
	 *
	 * @param event - Display system manager data
	 */
	@FXML
	void DisplaySystemManagerData(ActionEvent event)
	{
		MenuButton.setText(DisplaySystemManager.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("System Manager Data");
		data.add("select");
		data.add("user");
		data.add("permission");
		data.add("1");

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
	 * @param event - enter Back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) ViewLabel.getScene().getWindow());
	}

	/**
	 * View chosen data.
	 *
	 * @param event - View chosen data
	 */
	@FXML
	void ViewChosenData(ActionEvent event)
	{

	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert DisplayExeptionalRequests != null : "fx:id=\"DisplayExeptionalRequests\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplayAcademicActivities != null : "fx:id=\"DisplayAcademicActivities\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert MenuButton != null : "fx:id=\"MenuButton\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplayAssignments != null : "fx:id=\"DisplayAssignments\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplaySecretary != null : "fx:id=\"DisplaySecretary\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplayTeachers != null : "fx:id=\"DisplayTeachers\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplaySemesters != null : "fx:id=\"DisplaySemesters\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplaySystemManager != null : "fx:id=\"DisplaySystemManager\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplayParents != null : "fx:id=\"DisplayParents\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DataComboBox != null : "fx:id=\"DataComboBox\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplayClasses != null : "fx:id=\"DisplayClasses\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert DisplayPupils != null : "fx:id=\"DisplayPupils\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert ViewLabel != null : "fx:id=\"ViewLabel\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";
		assert ChooseLabel != null : "fx:id=\"ChooseLabel\" was not injected: check your FXML file 'SchoolManagerViewAllData.fxml'.";

		Main.client.controller = this;
		
		Main.stack.push("SchoolManagerViewAllData");

		DATA = "";
	}

    /**
     * Handles the answer from the server according to the type of answer.
     */   
	@Override
	public void handleAnswer(Object msg)
	{
		if (msg == null)
		{
			// error
			new Alert(AlertType.ERROR, "Item has not found.", ButtonType.OK).showAndWait();
			return;
		}

		ArrayList<String> arr = (ArrayList<String>) msg;
		String type = arr.remove(0);
		
		if(type.equals("Pupil Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String userID = map.get("userId");
				String userFName=map.get("userFirstName");
				String userLname=map.get("userLastName");
				DataComboBox.getItems().add("ID: " + userID + ", First Name: " + userFName + ", Last Name:  " + userLname);
			}
		}
		
		if(type.equals("Parent Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String userID = map.get("userId");
				String userFName=map.get("userFirstName");
				String userLname=map.get("userLastName");
				DataComboBox.getItems().add("ID: " + userID + ", First Name: " + userFName + ", Last Name:  " + userLname);
			}
		}
		
		if(type.equals("Teacher Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String userID = map.get("userId");
				String userFName=map.get("userFirstName");
				String userLname=map.get("userLastName");
				DataComboBox.getItems().add("ID: " + userID + ", First Name: " + userFName + ", Last Name:  " + userLname);
			}
		}
		
		if(type.equals("Class Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String classId= map.get("classId");
				String className=map.get("className");
				String AssignedPupils=map.get("AssignedPupils");
				DataComboBox.getItems().add("Class ID: " + classId + ", Class Name: " + className + ", Assigned Pupils:  " + AssignedPupils);
			}		
		}
		
		if(type.equals("Academic Activities Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String academicActivityId= map.get("academicActivityId");
				String academicActivityName=map.get("academicActivityName");
				String typeOfActivity=map.get("typeOfActivity");
				DataComboBox.getItems().add("Academic Activity Id: " + academicActivityId + ", Academic Activity Name: " + academicActivityName + ", Type Of Activity: " + typeOfActivity);
			}
		}
		
		if(type.equals("Semester Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String semesterId= map.get("semesterId");
				String semesterType=map.get("semesterType");
				String semesterStatus=map.get("semesterStatus");
				DataComboBox.getItems().add("Semester ID: " + semesterId + ", Semester Type: " + semesterType + ", Semester Status: " + semesterStatus);
			}
		}
		
		if(type.equals("Exeptional Requests Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String exceptonalRequestID= map.get("exceptonalRequestID");
				String type1=map.get("type");
				String descision=map.get("descision");
				if(type1.equals("assign"))
				{
				DataComboBox.getItems().add("Exceptonal Request ID: " + exceptonalRequestID + ", Request Type: " + type1 + " pupil to course, Descision: " + descision);
				}
				if(type1.equals("delete"))
				{
				DataComboBox.getItems().add("Exceptonal Request ID: " + exceptonalRequestID + ", Request Type: " + type1 + " pupil from course, Descision: " + descision);
				}
				if(type1.equals("Reassign"))
				{
				DataComboBox.getItems().add("Exceptonal Request ID: " + exceptonalRequestID + ", Request Type: " + type1 + " teacher to course in class, Descision: " + descision);
				}
			}
		}
		
		if(type.equals("Assignments Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String assignmentName= map.get("assignmentName");
				String dueDate=map.get("dueDate");
				String courseId=map.get("courseId");
				DataComboBox.getItems().add("Assignment Name: " + assignmentName + ", Due Date: " + dueDate + ", For Course: " + courseId);
			}
		}
		
		if(type.equals("Secretary Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String userID = map.get("userId");
				String userFName=map.get("userFirstName");
				String userLname=map.get("userLastName");
				DataComboBox.getItems().add("ID: " + userID + ", First Name: " + userFName + ", Last Name:  " + userLname);
			}
		}
		
		if(type.equals("System Manager Data"))
		{
			DataComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String userID = map.get("userId");
				String userFName=map.get("userFirstName");
				String userLname=map.get("userLastName");
				DataComboBox.getItems().add("ID: " + userID + ", First Name: " + userFName + ", Last Name:  " + userLname);
			}
		}
	}
}
