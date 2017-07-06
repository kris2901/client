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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class TeacherExeptionalRequestController - this class sending exceptional request for teacher.
 */
public class TeacherExeptionalRequestController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The List teacher combo box. */
	@FXML
	private ComboBox<String> ListTeacherComboBox;

	/** The Request id text field. */
	@FXML
	private TextField RequestIdTextField;

	/** The Class label. */
	@FXML
	private Label ClassLabel;

	/** The Teacher exceptional label. */
	@FXML
	private Label TeacherExeptionalLabel;

	/** The Teacher label. */
	@FXML
	private Label TeacherLabel;

	/** The Check id button. */
	@FXML
	private Button CheckIdButton;

	/** The Request id label. */
	@FXML
	private Label RequestIdLabel;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Course label no.1. */
	@FXML
	private Label CourseLabel1;

	/** The Send button. */
	@FXML
	private Button SendButton;

	/** The List curse combo box. */
	@FXML
	private ComboBox<String> ListCurseComboBox;

	/** The List C lass combo box. */
	@FXML
	private ComboBox<String> ListCLassComboBox;

	/** The Exceptional flag. */
	private int ExFlag;

	/** The combo box class. */
	private String cbClass;

	/** The Class ID. */
	private String ClassID;

	/** The course id. */
	private String courseid;

	/** The Study unit. */
	private String StudyUnit;

	/** The Course weekly hour. */
	private String CourseWeeklyHour;

	/** The Combo Box teacher id. */
	private String CBTeacherId;

	private String ID;

	/**
	 * Choose class.
	 *
	 * @param event - Choose class
	 */
	@FXML
	void ChooseClass(ActionEvent event)
	{
		cbClass = ListCLassComboBox.getSelectionModel().getSelectedItem();
		String[] Temp = cbClass.split(":");
		ClassID = Temp[0];
		loadCoursesInClass(ClassID);
	}

	/**
	 * Choose teacher.
	 *
	 * @param event - Choose teacher
	 */
	@FXML
	void ChooseTeacher(ActionEvent event)
	{
		CBTeacherId = ListTeacherComboBox.getSelectionModel().getSelectedItem();
		String[] Temp = CBTeacherId.split(":");
		CBTeacherId = Temp[0];
	}

	/**
	 * Send to school manager.
	 *
	 * @param event - Send to school manager
	 */
	@FXML
	void SendToSchoolManager(ActionEvent event)
	{
		String req = RequestIdTextField.getText();

		if (ExFlag == 0 || req.equals(""))
		{
			new Alert(AlertType.ERROR, "Enter Availabile Request ID.", ButtonType.OK).showAndWait();

		}
		else if (ClassID.equals(""))
		{
			new Alert(AlertType.ERROR, "Please Choose Class For The Request", ButtonType.OK).showAndWait();
		}
		else if (CBTeacherId.equals(""))
		{
			new Alert(AlertType.ERROR, "Please Choose Course And Teacher For The Request", ButtonType.OK).showAndWait();
		}
		else
		{
			CheckItISnOSameTeacher();
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
		UserWindow.closeUserWindow(getClass(), (Stage) ListTeacherComboBox.getScene().getWindow());
	}

	/**
	 * Check id availability.
	 *
	 * @param event - Check id availability
	 */
	@FXML
	void CeckIdAvailability(ActionEvent event)
	{
		ID = RequestIdTextField.getText();
		for (int i = 0; i < ID.length(); i++)
		{
			if (!(ID.charAt(i) <= '9' && ID.charAt(i) >= '0'))
			{
				new Alert(AlertType.ERROR, "Request ID must contain digits only!", ButtonType.OK).showAndWait();
				return;
			}
		}

		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Availbility Request ID");
		data.add("select");
		data.add("exceptional_request");
		data.add("exceptonalRequestID");
		data.add(RequestIdTextField.getText());

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
	 * Choose course.
	 *
	 * @param event - Choose course
	 */
	@FXML
	void ChooseCourse(ActionEvent event)
	{
		courseid = ListCurseComboBox.getSelectionModel().getSelectedItem();
		loadStudyUnit();
	}

	/**
	 * Load class ID and name.
	 */
	void loadClassIDAndName()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Class ID and Name");
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
	 * Load courses in class.
	 *
	 * @param ClassiD - Load courses in class
	 */
	void loadCoursesInClass(String ClassiD)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Course in class");
		data.add("select");
		data.add("course_in_class");
		data.add("classId");
		data.add(ClassiD);

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
	 * Load study unit.
	 */
	void loadStudyUnit()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Study Unit");
		data.add("select");
		data.add("courses");
		data.add("courseId");
		data.add(courseid);

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
	* Load teacher.
	*/
	void loadteacher()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Teacher List");
		data.add("select");
		data.add("teacher_in_studyunit");
		data.add("studyUnitID");
		data.add(StudyUnit);

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
	 * Check if is not the same teacher.
	 */
	void CheckItISnOSameTeacher()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Teacher In Course In Class");
		data.add("select");
		data.add("course_in_class");
		data.add("classId");
		data.add(ClassID);
		data.add("courseId");
		data.add(courseid);
		data.add("teacherId");
		data.add(CBTeacherId);

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
	* Open exceptional request.
	*/
	void OpenExeptionalRequest()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Insert new Exeptional Request");
		data.add("insert");
		data.add("exceptional_request");
		data.add("exceptonalRequestID");
		data.add("type");
		data.add("descision");
		data.add("CourseID");
		data.add("userID");
		data.add("classId");
		data.add("values");
		data.add(RequestIdTextField.getText());
		data.add("Reassign");
		data.add("panding");
		data.add(courseid);
		data.add(CBTeacherId);
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
		assert ListTeacherComboBox != null : "fx:id=\"ListTeacherComboBox\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert RequestIdTextField != null : "fx:id=\"RequestIdTextField\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert ClassLabel != null : "fx:id=\"ClassLabel\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert TeacherExeptionalLabel != null : "fx:id=\"TeacherExeptionalLabel\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert TeacherLabel != null : "fx:id=\"TeacherLabel\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert CheckIdButton != null : "fx:id=\"CheckIdButton\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert RequestIdLabel != null : "fx:id=\"RequestIdLabel\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert CourseLabel1 != null : "fx:id=\"CourseLabel1\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert SendButton != null : "fx:id=\"SendButton\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert ListCurseComboBox != null : "fx:id=\"ListCurseComboBox\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";
		assert ListCLassComboBox != null : "fx:id=\"ListCLassComboBox\" was not injected: check your FXML file 'ExeptionalRequestForTeacher.fxml'.";

		Main.client.controller = this;
		Main.stack.push("ExeptionalRequestForTeacher");

		ExFlag = 0;
		cbClass = "";
		ClassID = "";
		courseid = "";
		StudyUnit = "";
		CourseWeeklyHour = "";
		CBTeacherId = "";
		ID = "";
		loadClassIDAndName();
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
			new Alert(AlertType.ERROR, "Item has not found.", ButtonType.OK).showAndWait();
			return;
		}

		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);

		if (type.equals("Check Availbility Request ID"))
		{
			if (arr.size() == 0)
			{	
				if (ID.length() < 4 || ID.length() > 4)
				{
					new Alert(AlertType.ERROR, "Request ID must contain 4 digits.", ButtonType.OK).showAndWait();
				}
				else
				{
					ExFlag = 1;
					new Alert(AlertType.INFORMATION, "Exceptional Request ID is Available.", ButtonType.OK)
							.showAndWait();
				}
			}
			else
			{
				new Alert(AlertType.ERROR, "This Exceptional Request ID already exists.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Load Class ID and Name"))
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
				String classname = map.get("className");
				String classID = map.get("classId");
				ListCLassComboBox.getItems().add(classID + ": " + classname);
			}
		}

		if (type.equals("Load Course in class"))
		{
			ListCurseComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				courseid = map.get("courseId");
				ListCurseComboBox.getItems().add(courseid);
			}
		}

		if (type.equals("Load Study Unit"))
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
				StudyUnit = map.get("studyUnit");
				CourseWeeklyHour = map.get("weeklyStudyHours");
			}
			loadteacher();
		}

		if (type.equals("Teacher List"))
		{
			ListTeacherComboBox.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String username = map.get("userName");
				String userID = map.get("userId");
				ListTeacherComboBox.getItems().add(userID + ": " + username);
			}
		}

		if (type.equals("Load Teacher In Course In Class"))
		{
			if (arr.size() != 0)
			{
				new Alert(AlertType.ERROR, "Teacher Already Assigned To This Course In This Class.", ButtonType.OK)
						.showAndWait();
			}
			else
			{
				OpenExeptionalRequest();
			}
		}

		if (type.equals("Insert new Exeptional Request"))
		{
			if (arr.size() > 0)
			{
				new Alert(AlertType.INFORMATION, "Exceptional Request Opened Susccesfully", ButtonType.OK)
						.showAndWait();
				UserWindow.exitToMenu(getClass(), (Stage) CheckIdButton.getScene().getWindow());
			}
		}
	}
}
