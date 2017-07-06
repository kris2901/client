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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class OpenCourseController - this class is open new course.
 */
public class OpenCourseController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Choose course combo box. */
	@FXML
	private ComboBox<String> ChooseCourseComboBox;

	/** The Choose class combo box. */
	@FXML
	private ComboBox<String> ChooseClassComboBox;

	/** The Open course label. */
	@FXML
	private Label OpenCourseLable;

	/** The Open button. */
	@FXML
	private Button OpenButton;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Choose teacher combo box. */
	@FXML
	private ComboBox<String> ChooseTeacherComboBox;

	/** The Study unit. */
	private String StudyUnit;

	/** The cb course name. */
	private String cbCourseName;

	/** The combo box class name. */
	private String cbClassName;

	/** The combo box teacher details. */
	private String cbTeacherDetails;

	/** The Course ID. */
	private String CourseID;

	/** The Teacher ID. */
	private String TeacherID;

	/** The Course weekly hour. */
	private String CourseWeeklyHour;

	/** The Teacher available hours. */
	private int TeacherAvailableHours;

	/** The class ID. */
	private String classID;

	/** The Teaching hours. */
	private String TeachingHours;

	/** The choose course flag. */
	private int chooseCourseFlag;

	/** The choose class flag. */
	private int chooseClassFlag;

	/** The choose teacher flag. */
	private int chooseTeacherFlag;

	/**
	 * Back to menu.
	 *
	 * @param event enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{

		UserWindow.closeUserWindow(getClass(), (Stage) OpenCourseLable.getScene().getWindow());
	}

	/**
	 * Course list.
	 *
	 * @param event - enter course list
	 */
	@FXML
	void CourseList(ActionEvent event)
	{

		cbCourseName = ChooseCourseComboBox.getSelectionModel().getSelectedItem();
		String[] temp = cbCourseName.split(":");
		cbCourseName = temp[0];
		chooseCourseFlag = 1;
		loadStudyUnit();

	}

	/**
	 * Open course in class.
	 *
	 * @param event - exceptional events
	 */
	@FXML
	void OpenCourseInClass(ActionEvent event)
	{

		if (chooseCourseFlag == 0)
		{
			new Alert(AlertType.ERROR, "Choose Course From List First.", ButtonType.OK).showAndWait();
		}
		else if (chooseClassFlag == 0)
		{
			new Alert(AlertType.ERROR, "Choose Class From List First.", ButtonType.OK).showAndWait();
		}
		else if (chooseTeacherFlag == 0)
		{
			new Alert(AlertType.ERROR, "Choose Teacher From List First.", ButtonType.OK).showAndWait();
		}
		else
		{
			checkIFcourseAlreadyOpen();
		}

	}

	/**
	 * Choose teacher.
	 *
	 * @param event - enter teacher 
	 */
	@FXML
	void ChooseTeacher(ActionEvent event)
	{

		cbTeacherDetails = ChooseTeacherComboBox.getSelectionModel().getSelectedItem();
		chooseTeacherFlag = 1;

	}

	/**
	 * Choose class.
	 *
	 * @param event - enter class
	 */
	@FXML
	void ChooseClass(ActionEvent event)
	{

		cbClassName = ChooseClassComboBox.getSelectionModel().getSelectedItem();
		loadClassID();
		chooseClassFlag = 1;

	}

	/**
	 * Load all courses.
	 */
	void loadCourses()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Course List");
		data.add("select");
		data.add("courses");

		try
		{
			Main.client.sendToServer(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		//loadStudyUnit();
	}

	/**
	 * Load all class.
	 */
	void loadAllClass()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Class List");
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
	 * Load all class ID.
	 */
	void loadClassID()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load ClassID");
		data.add("select");
		data.add("class");
		data.add("className");
		data.add(cbClassName);

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
	 * Load all teacher.
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
	 * Load study unit.
	 */
	void loadStudyUnit()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Study Unit");
		data.add("select");
		data.add("courses");
		data.add("courseName");
		data.add(cbCourseName);

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
	 * Check if course is already open.
	 */
	void checkIFcourseAlreadyOpen()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Course In Class");
		data.add("select");
		data.add("course_in_class");
		data.add("courseId");
		data.add(CourseID);
		data.add("classId");
		data.add(classID);

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
	 * Check maximum hours.
	 */
	void checkMaximumHours()
	{
		String[] Temp = ChooseTeacherComboBox.getSelectionModel().getSelectedItem().split(":");
		TeacherID = Temp[1];
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Available Hours");
		data.add("select");
		data.add("teacher");
		data.add("userID");
		data.add(TeacherID);

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
	 * Insert course to class.
	 */
	void InsertCourseToClass()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Insert to course_in_class");
		data.add("insert");
		data.add("course_in_class");
		data.add("courseId");
		data.add("classId");
		data.add("teacherId");
		data.add("values");
		data.add(CourseID);
		data.add(classID);
		data.add(TeacherID);

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
	 * Update work hours teacher.
	 *
	 * @param S - enter new work hours
	 */
	void UpdateWorkHoursTeacher(String S)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Update Hours");
		data.add("update");
		data.add("teacher");
		data.add("workHours");
		data.add(S);
		data.add("conditions");
		data.add("userID");
		data.add(TeacherID);

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
		assert ChooseCourseComboBox != null : "fx:id=\"ChooseCourseComboBox\" was not injected: check your FXML file 'SecretaryOpenCourse.fxml'.";
		assert ChooseClassComboBox != null : "fx:id=\"ChooseClassComboBox\" was not injected: check your FXML file 'SecretaryOpenCourse.fxml'.";
		assert OpenCourseLable != null : "fx:id=\"OpenCourseLable\" was not injected: check your FXML file 'SecretaryOpenCourse.fxml'.";
		assert OpenButton != null : "fx:id=\"OpenButton\" was not injected: check your FXML file 'SecretaryOpenCourse.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SecretaryOpenCourse.fxml'.";
		assert ChooseTeacherComboBox != null : "fx:id=\"ChooseTeacherComboBox\" was not injected: check your FXML file 'SecretaryOpenCourse.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SecretaryOpenCourse");

		StudyUnit = "";
		cbCourseName = "";
		cbClassName = "";
		cbTeacherDetails = "";
		CourseID = "";
		TeacherID = "";
		CourseWeeklyHour = "";
		TeacherAvailableHours = -1;
		classID = "";
		TeachingHours = "";
		chooseCourseFlag = 0;
		chooseClassFlag = 0;
		chooseTeacherFlag = 0;

		loadCourses();
		loadAllClass();

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

		if (type.equals("Course List"))
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

				String coursename = map.get("courseName");
				String coursID = map.get("courseId");
				ChooseCourseComboBox.getItems().add(coursename + ": " + coursID);
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
				CourseID = map.get("courseId");
				CourseWeeklyHour = map.get("weeklyStudyHours");
			}
			//loadAllClass();
			loadteacher();
		}

		if (type.equals("Class List"))
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
				ChooseClassComboBox.getItems().add(classname);
			}
			//	loadCourses();
		}

		if (type.equals("Load ClassID"))
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

				classID = map.get("classId");
			}
		}

		if (type.equals("Teacher List"))
		{
			ChooseTeacherComboBox.getItems().clear();
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
				ChooseTeacherComboBox.getItems().add(username + " :" + userID);
			}
			//loadClassID();
		}

		if (type.equals("Load Course In Class"))
		{
			if (arr.size() != 0)
			{
				new Alert(AlertType.ERROR, "Course is already opened in this class", ButtonType.OK).showAndWait();
			}
			else
				checkMaximumHours();
		}

		if (type.equals("Load Available Hours"))
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

				TeachingHours = map.get("workHours");
				String MaxHours = map.get("MaxHoursForWeek");
				int num = Integer.parseInt(TeachingHours);
				int num1 = Integer.parseInt(MaxHours);
				TeacherAvailableHours = num1 - num;
			}
			int CourseHours = Integer.parseInt(CourseWeeklyHour);
			if (CourseHours > TeacherAvailableHours)
			{
				new Alert(AlertType.ERROR, "Teacher's Availabele hours not enough for this course", ButtonType.OK)
						.showAndWait();
			}
			else
			{
				int num = Integer.parseInt(TeachingHours);
				num = num + CourseHours;
				TeachingHours = Integer.toString(num);
				InsertCourseToClass();
				new Alert(AlertType.INFORMATION, "Course Opened Succesfully", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Insert to course_in_class"))
		{
			if (arr.size() > 0)
			{
				UpdateWorkHoursTeacher(TeachingHours);
			}
			else
				new Alert(AlertType.ERROR, "Error", ButtonType.OK).showAndWait();
		}

		if (type.equals("Update Hours"))
		{
			//new Alert(AlertType.INFORMATION, "Updated Working Hours Of Teacher", ButtonType.OK).showAndWait();
			UserWindow.closeUserWindow(getClass(), (Stage) BackButton.getScene().getWindow());
		}
	}
}
