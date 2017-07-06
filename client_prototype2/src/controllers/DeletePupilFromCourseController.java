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
 * The Class DeletePupilFromCourseController - this class delete pupil that they are already exist in the course.
 */
public class DeletePupilFromCourseController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Check pupil button. */
	@FXML
	private Button CheckPupilButton;

	/** The Check course button. */
	@FXML
	private Button CheckCourseButton;

	/** The Request id text field. */
	@FXML
	private TextField RequestIdTextField;

	/** The Check request button. */
	@FXML
	private Button CheckRequestButton;

	/** The Pupil ID label. */
	@FXML
	private Label PupilIDLable;

	/** The Pupil ID text field. */
	@FXML
	private TextField PupilIDTextField;

	/** The Delete button. */
	@FXML
	private Button DeleteButton;

	/** The Course ID text field. */
	@FXML
	private TextField CourseIDTextField;

	/** The Request Id label. */
	@FXML
	private Label RequestIdLabel;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Delete label. */
	@FXML
	private Label DeleteLable;

	/** The Course ID label. */
	@FXML
	private Label CourseIDLable;

	/** The Pupil ID. */
	private String PupilID;

	/** The Course ID. */
	private String CourseID;

	/** The check course ID. */
	private int checkCourseID;

	/** The check pupil ID. */
	private int checkPupilID;

	/** The Exception request flag. */
	private int ExeReFLAG;

	private String RequestID;

	/**
	 * Check if pupil ID is already exist in the DB.
	 *
	 * @param event - enter pupil ID
	 */
	@FXML
	void CheckPupilID(ActionEvent event)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Pupil");
		data.add("select");
		data.add("pupil");
		data.add("userID");
		PupilID = PupilIDTextField.getText();
		data.add(PupilID);
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
	 * Check if the course ID is already exist in the DB.
	 *
	 * @param event - enter Course ID
	 */
	@FXML
	void CheckCourseID(ActionEvent event)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Course");
		data.add("select");
		data.add("courses");
		data.add("courseId");
		CourseID = CourseIDTextField.getText();
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
	 * Delete pupil course.
	 *
	 * @param event - enter pupil
	 */
	@FXML
	void DeletePupilCourse(ActionEvent event)
	{
		if (ExeReFLAG == 0)
		{
			new Alert(AlertType.ERROR, "Enter Availabele Request ID.", ButtonType.OK).showAndWait();
		}
		else if (checkPupilID == 0)
		{
			new Alert(AlertType.ERROR, "Enter Availabele Pupil ID.", ButtonType.OK).showAndWait();
		}
		else if (checkCourseID == 0)
		{
			new Alert(AlertType.ERROR, "Enter Availabele Course ID.", ButtonType.OK).showAndWait();
		}
		else
		{
			loadDecision();
		}
	}

	/**
	 * Load decision.
	 */
	void loadDecision()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Exeptional Request Descision");
		data.add("select field");
		data.add("descision");
		data.add("exceptional_request");
		data.add("CourseID");
		data.add(CourseIDTextField.getText());
		data.add("userID");
		data.add(PupilIDTextField.getText());
		data.add("type");
		data.add("delete");
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
	 * Check request ID.
	 *
	 * @param event - enter exceptional request ID
	 */
	@FXML
	void CheckRequestID(ActionEvent event)
	{
		RequestID = RequestIdTextField.getText();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check RequestID");
		data.add("select");
		data.add("exceptional_request");
		data.add("exceptonalRequestID");
		data.add(RequestID);
		data.add("type");
		data.add("delete");

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
		UserWindow.closeUserWindow(getClass(), (Stage) DeleteButton.getScene().getWindow());
	}

	/**
	 * Delete pupil from course.
	 */
	void DeletePupilFromCourse()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Delete Pupil From Course");
		data.add("delete");
		data.add("pupil_in_course");
		data.add("userID");
		data.add(PupilIDTextField.getText());
		data.add("courseID");
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
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert CheckPupilButton != null : "fx:id=\"CheckPupilButton\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert CheckCourseButton != null : "fx:id=\"CheckCourseButton\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert RequestIdTextField != null : "fx:id=\"RequestIdTextField\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert CheckRequestButton != null : "fx:id=\"CheckRequestButton\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert PupilIDLable != null : "fx:id=\"PupilIDLable\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert PupilIDTextField != null : "fx:id=\"PupilIDTextField\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert DeleteButton != null : "fx:id=\"DeleteButton\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert CourseIDTextField != null : "fx:id=\"CourseIDTextField\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert RequestIdLabel != null : "fx:id=\"RequestIdLabel\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert DeleteLable != null : "fx:id=\"DeleteLable\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";
		assert CourseIDLable != null : "fx:id=\"CourseIDLable\" was not injected: check your FXML file 'SecretaryDeletePupilFromCourse.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SecretaryDeletePupilFromCourse");

		PupilID = "";
		CourseID = "";
		checkCourseID = 0;
		checkPupilID = 0;
		ExeReFLAG = 0;
		RequestID = "";
		PupilID = "";
		CourseID = "";
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

		if (type.equals("Check RequestID"))
		{
			int rIdFlag = 1;
			if (arr.size() == 0)
			{
				ExeReFLAG = 0;
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
				ExeReFLAG = 1;
				new Alert(AlertType.INFORMATION, "Exceptional Request ID has found.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Check Pupil"))
		{
			int pIdFlag = 1;
			if (arr.size() == 0)
			{
				checkPupilID = 0;
				int i;
				for (i = 0; i < PupilID.length(); i++)
				{
					if ((PupilID.charAt(i) >= 'a' && PupilID.charAt(i) <= 'z')
							|| (PupilID.charAt(i) >= 'A' && PupilID.charAt(i) <= 'Z'))
					{
						pIdFlag = 0;
						new Alert(AlertType.ERROR, "Pupil ID must contain digits only", ButtonType.OK).showAndWait();
						break;
					}
				}
				if (((PupilID.length() > 9) || (PupilID.length() < 9)) && (pIdFlag == 1))
				{
					new Alert(AlertType.ERROR, "Pupil ID must contain 9 digits.", ButtonType.OK).showAndWait();
				}
				else
				{
					if (pIdFlag == 1)
					{
						new Alert(AlertType.ERROR, "Pupil ID has not found.", ButtonType.OK).showAndWait();
					}
				}
			}
			else
			{
				checkPupilID = 1;
				new Alert(AlertType.INFORMATION, "Pupil ID has found.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Check Course"))
		{
			int cIdFlag = 1;
			if (arr.size() == 0)
			{
				checkCourseID = 0;
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
				checkCourseID = 1;
				new Alert(AlertType.INFORMATION, "Course ID has found.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Check Exeptional Request Descision"))

		{
			String des = "";
			if (arr.isEmpty() == true)
			{
				new Alert(AlertType.ERROR,
						"Exceptional Request for delete pupil to course by details you entered not exist.",
						ButtonType.OK).showAndWait();
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
					des = map.get("descision");
				}
				if (des.equals("deny"))
				{
					new Alert(AlertType.ERROR, "The request to delete pupil to this course not confirmed",
							ButtonType.OK).showAndWait();
				}
				else if (des.equals("confirm"))
				{
					DeletePupilFromCourse();
				}
				else if (des.equals("panding"))
				{
					new Alert(AlertType.ERROR, "No Response To This Request Yet", ButtonType.OK).showAndWait();
				}
			}
		}

		if (type.equals("Delete Pupil From Course"))
		{
			if (arr.size() == 0)

			{
				new Alert(AlertType.ERROR, "Delete failed", ButtonType.OK).showAndWait();
			}
			else
			{
				new Alert(AlertType.INFORMATION, "Pupil Succesfully Deleted From Course", ButtonType.OK).showAndWait();
				UserWindow.closeUserWindow(getClass(), (Stage) DeleteButton.getScene().getWindow());
			}
		}
	}
}
