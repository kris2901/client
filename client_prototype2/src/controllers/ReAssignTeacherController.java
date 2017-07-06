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
import javafx.stage.Stage;
import ui.UserWindow;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The Class ReAssignTeacherController - this class treats to request assignment teacher.
 */
public class ReAssignTeacherController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Class ID text field. */
	@FXML
	private TextField ClassIDTextField;

	/** The Check request button. */
	@FXML
	private Button CheckRequestButton;

	/** The Course ID text field. */
	@FXML
	private TextField CourseIDTextField;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Teacher id label. */
	@FXML
	private Label TeacherIdLabel;

	/** The Class ID label. */
	@FXML
	private Label ClassIDLable;

	/** The Request assignment label. */
	@FXML
	private Label ReAssignLable;

	/** The Check teacher button. */
	@FXML
	private Button CheckTeacherButton;

	/** The Check course button. */
	@FXML
	private Button CheckCourseButton;

	/** The Teacher id text field. */
	@FXML
	private TextField TeacherIdTextFieald;

	/** The Request id label. */
	@FXML
	private Label RequestIdLabel;

	/** The Check class button. */
	@FXML
	private Button CheckClassButton;

	/** The Re assign teacher button. */
	@FXML
	private Button ReAssignTeacherButton;

	/** The Course ID label. */
	@FXML
	private Label CourseIDLable;

	/** The Exceptional request text field no.1. */
	@FXML
	private TextField ExReTextField1;

	/** The Exceptional flag. */
	private int ExFlag;

	/** The course ID flag. */
	private int courseIDF;

	/** The class ID flag. */
	private int classIDF;

	/** The teacher ID flag. */
	private int teacherIDF;

	/** The course hours. */
	private String courseHours;

	/** The New teacher work hours. */
	private String NewTeacherWorkHours;

	/** The Old teacher work hours. */
	private String OldTeacherWorkHours;

	/** The Old teacher ID. */
	private String OldTeacherID;

	private String RequestID;
	private String TeacherID;
	private String CourseID;
	private String ClassID;

	/**
	 * Check request ID.
	 *
	 * @param event - request ID
	 */
	@FXML
	void CheckRequestID(ActionEvent event)
	{
		RequestID = ExReTextField1.getText();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Existing Request ID");
		data.add("select");
		data.add("exceptional_request");
		data.add("exceptonalRequestID");
		data.add(RequestID);
		data.add("type");
		data.add("Reassign");

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
	 * Check course ID.
	 *
	 * @param event - course ID
	 */
	@FXML
	void CheckCourseID(ActionEvent event)
	{
		CourseID = CourseIDTextField.getText();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Course ID");
		data.add("select");
		data.add("courses");
		data.add("courseId");
		data.add(CourseID);

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
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) CheckCourseButton.getScene().getWindow());
	}

	/**
	 * Request assignment teacher.
	 *
	 * @param event - enter exceptional input of user in request assignment teacher
	 */
	@FXML
	void ReAssignTeacher(ActionEvent event)
	{

		if (ExFlag == 0)
		{
			new Alert(AlertType.ERROR, "Enter Exist Request ID And Check It.", ButtonType.OK).showAndWait();
		}
		else if (courseIDF == 0)
		{
			new Alert(AlertType.ERROR, "Enter Course ID And Check It.", ButtonType.OK).showAndWait();
		}
		else if (classIDF == 0)
		{
			new Alert(AlertType.ERROR, "Enter Class ID And Check It", ButtonType.OK).showAndWait();
		}
		else if (teacherIDF == 0)
		{
			new Alert(AlertType.ERROR, "Enter Teacher ID And Check It", ButtonType.OK).showAndWait();
		}
		else
		{
			loadDecision();
		}

	}

	/**
	 * Check class ID.
	 *
	 * @param event - class ID
	 */
	@FXML
	void CheckClassID(ActionEvent event)
	{
		ClassID = ClassIDTextField.getText();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Class ID");
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
	 * Gets the teacher ID.
	 *
	 * @param event - teacher ID
	 */
	@FXML
	void getTeacherID(ActionEvent event)
	{
		teacherIDF = 0;
	}

	/**
	 * Check teacher ID.
	 *
	 * @param event  - teacher ID
	 */
	@FXML
	void CheckTeacherID(ActionEvent event)
	{
		TeacherID = TeacherIdTextFieald.getText();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Teacher ID");
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
	 * Load decision.
	 */
	void loadDecision()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Exeptional Request Descision");
		data.add("select");
		data.add("exceptional_request");
		data.add("exceptonalRequestID");
		data.add(ExReTextField1.getText());
		data.add("type");
		data.add("Reassign");
		data.add("CourseID");
		data.add(CourseIDTextField.getText());
		data.add("userID");
		data.add(TeacherIdTextFieald.getText());
		data.add("classId");
		data.add(ClassIDTextField.getText());

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
	 * Reassign teacher.
	 */
	void reassignTeacher()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Update Teacher To Course In Class");
		data.add("update");
		data.add("course_in_class");
		data.add("teacherId");
		data.add(TeacherIdTextFieald.getText());
		data.add("conditions");
		data.add("classId");
		data.add(ClassIDTextField.getText());
		data.add("courseId");
		data.add(CourseIDTextField.getText());

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
	 * Change teacher hours.
	 */
	void ChangeTeacherHours()
	{
		int num = Integer.parseInt(NewTeacherWorkHours);
		num = num + Integer.parseInt(courseHours);
		NewTeacherWorkHours = Integer.toString(num);

		ArrayList<String> data = new ArrayList<String>();
		data.add("Update New Teacher Hours");
		data.add("update");
		data.add("teacher");
		data.add("workHours");
		data.add(NewTeacherWorkHours);
		data.add("conditions");
		data.add("userId");
		data.add(TeacherIdTextFieald.getText());

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
	 * Load old teacher.
	 */
	void loadOldTeacher()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Old Teacher");
		data.add("select");
		data.add("course_in_class");
		data.add("classId");
		data.add(ClassIDTextField.getText());
		data.add("courseId");
		data.add(CourseIDTextField.getText());

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
	 * Load old teacher hours.
	 */
	void loadOldTeacherHours()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Old Teacher Hours");
		data.add("select");
		data.add("teacher");
		data.add("userID");
		data.add(OldTeacherID);

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
	 * Update old teacher hours.
	 */
	void updateOldTeacherHours()
	{
		int num = Integer.parseInt(OldTeacherWorkHours);
		int num1 = Integer.parseInt(courseHours);
		num = num - num1;
		OldTeacherWorkHours = Integer.toString(num);

		ArrayList<String> data = new ArrayList<String>();
		data.add("Update Old Teacher Hours");
		data.add("update");
		data.add("teacher");
		data.add("workHours");
		data.add(OldTeacherWorkHours);
		data.add("conditions");
		data.add("userID");
		data.add(OldTeacherID);

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
		assert ClassIDTextField != null : "fx:id=\"ClassIDTextField\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert CheckRequestButton != null : "fx:id=\"CheckRequestButton\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert CourseIDTextField != null : "fx:id=\"CourseIDTextField\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert TeacherIdLabel != null : "fx:id=\"TeacherIdLabel\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert ClassIDLable != null : "fx:id=\"ClassIDLable\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert ReAssignLable != null : "fx:id=\"ReAssignLable\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert CheckTeacherButton != null : "fx:id=\"CheckTeacherButton\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert CheckCourseButton != null : "fx:id=\"CheckCourseButton\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert TeacherIdTextFieald != null : "fx:id=\"TeacherIdTextFieald\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert RequestIdLabel != null : "fx:id=\"RequestIdLabel\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert CheckClassButton != null : "fx:id=\"CheckClassButton\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert ReAssignTeacherButton != null : "fx:id=\"ReAssignTeacherButton\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert CourseIDLable != null : "fx:id=\"CourseIDLable\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";
		assert ExReTextField1 != null : "fx:id=\"ExReTextField1\" was not injected: check your FXML file 'SecretaryReAssignTeacher.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SecretaryReAssignTeacher");

		ExFlag = 0;
		courseIDF = 0;
		classIDF = 0;
		teacherIDF = 0;
		courseHours = "";
		NewTeacherWorkHours = "";
		OldTeacherWorkHours = "";
		OldTeacherID = "";
		RequestID = "";
		TeacherID = "";
		CourseID = "";
		ClassID = "";
	}

	/**
	 * Handles the answer from the server according to the type of answer.
	 */
	@Override
	public void handleAnswer(Object result)
	{
		// TODO Auto-generated method stub

		if (result == null)
		{
			// error
			new Alert(AlertType.ERROR, "Item has not found.", ButtonType.OK).showAndWait();
			return;
		}

		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);

		if (type.equals("Check Existing Request ID"))
		{
			int rIdFlag = 1;
			if (arr.size() == 0)
			{
				ExFlag = 0;
				int i;
				for (i = 0; i < RequestID.length(); i++)
				{
					if ((RequestID.charAt(i) >= 'a' && RequestID.charAt(i) <= 'z')
							|| (RequestID.charAt(i) >= 'A' && RequestID.charAt(i) <= 'Z'))
					{
						rIdFlag = 0;
						new Alert(AlertType.ERROR, "Exceptional Request ID must contain digits only", ButtonType.OK)
								.showAndWait();
						break;
					}
				}
				if (((RequestID.length() > 4) || (RequestID.length() < 4)) && (rIdFlag == 1))
				{
					new Alert(AlertType.ERROR, "Exceptional Request ID must contain 4 digits.", ButtonType.OK)
							.showAndWait();
				}
				else
				{
					if (rIdFlag == 1)
					{
						new Alert(AlertType.ERROR, "Exceptional Request ID has not found.", ButtonType.OK)
								.showAndWait();
					}
				}
			}
			else
			{
				ExFlag = 1;
				new Alert(AlertType.INFORMATION, "Exceptional Request ID has found.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Check Class ID"))
		{
			int clIdFlag = 1;
			if (arr.size() == 0)
			{
				classIDF = 0;
				int i;
				for (i = 0; i < ClassID.length(); i++)
				{
					if ((ClassID.charAt(i) >= 'a' && ClassID.charAt(i) <= 'z')
							|| (ClassID.charAt(i) >= 'A' && ClassID.charAt(i) <= 'Z'))
					{
						clIdFlag = 0;
						new Alert(AlertType.ERROR, "Class ID must contain digits only", ButtonType.OK).showAndWait();
						break;
					}
				}
				if (((ClassID.length() > 9) || (ClassID.length() < 9)) && (clIdFlag == 1))
				{
					new Alert(AlertType.ERROR, "Class ID must contain 9 digits.", ButtonType.OK).showAndWait();
				}
				else
				{
					if (clIdFlag == 1)
					{
						new Alert(AlertType.ERROR, "Class ID has not found.", ButtonType.OK).showAndWait();
					}
				}
			}
			else
			{
				classIDF = 1;
				new Alert(AlertType.INFORMATION, "Class ID has found.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Check Course ID"))
		{
			int cIdFlag = 1;
			if (arr.size() == 0)
			{
				courseIDF = 0;
				int i;
				for (i = 0; i < CourseID.length(); i++)
				{
					if ((CourseID.charAt(i) >= 'a' && CourseID.charAt(i) <= 'z')
							|| (CourseID.charAt(i) >= 'A' && CourseID.charAt(i) <= 'Z'))
					{
						cIdFlag = 0;
						new Alert(AlertType.ERROR, "Course ID must contain digits only", ButtonType.OK).showAndWait();
						break;
					}
				}
				if (((CourseID.length() > 5) || (CourseID.length() < 5)) && (cIdFlag == 1))
				{
					new Alert(AlertType.ERROR, "Course ID must contain 5 digits.", ButtonType.OK).showAndWait();
				}
				else
				{

					if (cIdFlag == 1)
					{
						new Alert(AlertType.ERROR, "Course ID has not found.", ButtonType.OK).showAndWait();
					}
				}
			}
			else
			{
				courseIDF = 1;
				new Alert(AlertType.INFORMATION, "Course ID has found.", ButtonType.OK).showAndWait();
				for (String row : arr)
				{
					String[] cols = row.split(";");
					HashMap<String, String> map = new HashMap<>();
					for (String col : cols)
					{
						String[] field = col.split("=");
						map.put(field[0], field[1]);
					}
					courseHours = map.get("weeklyStudyHours");
				}
			}
		}

		if (type.equals("Check Teacher ID"))
		{
			int pIdFlag = 1;
			if (arr.size() == 0)
			{
				teacherIDF = 0;
				int i;
				for (i = 0; i < TeacherID.length(); i++)
				{
					if ((TeacherID.charAt(i) >= 'a' && TeacherID.charAt(i) <= 'z')
							|| (TeacherID.charAt(i) >= 'A' && TeacherID.charAt(i) <= 'Z'))
					{
						pIdFlag = 0;
						new Alert(AlertType.ERROR, "Teacher ID must contain digits only", ButtonType.OK).showAndWait();
						break;
					}
				}
				if (((TeacherID.length() > 9) || (TeacherID.length() < 9)) && (pIdFlag == 1))
				{
					new Alert(AlertType.ERROR, "Teacher ID must contain 9 digits.", ButtonType.OK).showAndWait();
				}
				else
				{
					if (pIdFlag == 1)
					{
						new Alert(AlertType.ERROR, "Teacher ID has not found.", ButtonType.OK).showAndWait();
					}
				}
			}
			else
			{
				teacherIDF = 1;
				new Alert(AlertType.INFORMATION, "Teacher ID has found.", ButtonType.OK).showAndWait();
				for (String row : arr)
				{
					String[] cols = row.split(";");
					HashMap<String, String> map = new HashMap<>();
					for (String col : cols)
					{
						String[] field = col.split("=");
						map.put(field[0], field[1]);
					}

					NewTeacherWorkHours = map.get("workHours");
				}
			}
		}

		if (type.equals("Check Exeptional Request Descision"))
		{
			String DES = "";
			if (arr.size() == 0)
			{
				new Alert(AlertType.ERROR, "Not Found Exceptional Request For This Details", ButtonType.OK)
						.showAndWait();
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

					DES = map.get("descision");
				}

				if (DES.equals("confirm"))
				{
					loadOldTeacher();
					reassignTeacher();
				}
				else if (DES.equals("deny"))
				{
					new Alert(AlertType.INFORMATION, "Exceptional Request For This Details Deny", ButtonType.OK)
							.showAndWait();
				}
				else if (DES.equals("panding"))
				{
					new Alert(AlertType.ERROR, "Exceptional Request For This Details Not Responsed Yet", ButtonType.OK)
							.showAndWait();
				}
			}
		}

		if (type.equals("Update Teacher To Course In Class"))
		{
			new Alert(AlertType.INFORMATION, "Re-Assiggn Of Teacher To Course In Clsss Succes.", ButtonType.OK)
					.showAndWait();
			//UserWindow.closeUserWindow(getClass(), (Stage) CheckCourseButton.getScene().getWindow());
			ChangeTeacherHours();
		}

		if (type.equals("Load Old Teacher"))
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

				OldTeacherID = map.get("teacherId");
				loadOldTeacherHours();
			}
		}

		if (type.equals("Load Old Teacher Hours"))
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

				OldTeacherWorkHours = map.get("workHours");
				updateOldTeacherHours();
			}
		}

		if (type.equals("Update Old Teacher Hours"))
		{
			//new Alert(AlertType.INFORMATION, "New And Old Teachers Work Hours Updated.", ButtonType.OK).showAndWait();
			UserWindow.closeUserWindow(getClass(), (Stage) CheckCourseButton.getScene().getWindow());
		}
	}
}
