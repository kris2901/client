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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class PupilExeptionalRequestController this class send pupil exceptional request.
 */
public class PupilExeptionalRequestController implements IController
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

	/** The Assign pupil. */
	@FXML
	private MenuItem AssignPupil;

	/** The Request id text field. */
	@FXML
	private TextField RequestIdTextField;

	/** The Check id button. */
	@FXML
	private Button CheckIdButton;

	/** The Choose pupil combo box. */
	@FXML
	private ComboBox<String> ChoosePupilComboBox;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Pupil exceptional label. */
	@FXML
	private Label PupilExeptionalLable;

	/** The Request id label. */
	@FXML
	private Label RequestIdLable;

	/** The Choose operation menu button. */
	@FXML
	private MenuButton ChooseOperationMenuButton;

	/** The Operation label. */
	@FXML
	private Label OperationLable;

	/** The Delete pupil. */
	@FXML
	private MenuItem DeletePupil;

	/** The Send button. */
	@FXML
	private Button SendButton;

	/** The Pupil id label. */
	@FXML
	private Label PupilIdLable;

	/** The Course list label. */
	@FXML
	private Label CourseListLable;

	/** The Choose class label. */
	@FXML
	private Label ChooseClassLabel;

	/** The Choose class Combo Box. */
	@FXML
	private ComboBox<String> ChooseClassCB;

	/** The Request Id flag. */
	private int ReqIDflag;

	/** The combo box course ID name. */
	private String cbCourseIDName;

	/** The combo box pupil ID name. */
	private String cbPupilIDName;

	/** The Operation. */
	private String Operation;

	/** The Pupil ID. */
	private String PupilID;

	/** The Course ID. */
	private String CourseID;

	/** The combo box class ID name. */
	private String cbClassIDName;

	private String ID;

	/**
	 * Check id availability.
	 *
	 * @param event - enter id availability
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
		data.add("Check RequestID Availbility");
		data.add("select");
		data.add("exceptional_request");
		data.add("exceptonalRequestID");
		data.add(ID);

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
	 * @param event - enter course
	 */
	@FXML
	void ChooseCourse(ActionEvent event)
	{

		cbCourseIDName = ChooseCourseComboBox.getSelectionModel().getSelectedItem();
		String[] temp = cbCourseIDName.split(":");
		CourseID = temp[0];

		loadClasses();
	}

	/**
	 * Choose pupil.
	 *
	 * @param event - enter pupil id
	 */
	@FXML
	void ChoosePupil(ActionEvent event)
	{

		cbPupilIDName = ChoosePupilComboBox.getSelectionModel().getSelectedItem();
	}

	/**
	 * Choose operation.
	 *
	 * @param event operation
	 */
	@FXML
	void ChooseOperation(ActionEvent event)
	{

	}

	/**
	 * Assign pupil to course.
	 *
	 * @param event - enter assignment
	 */
	@FXML
	void AssignPupilToCourse(ActionEvent event)
	{

		Operation = "assign";
		ChooseOperationMenuButton.setText(AssignPupil.getText());
	}

	/**
	 * Assign pupil menu.
	 *
	 * @param event - pupil menu
	 */
	@FXML
	void AssignPupilMenu(ActionEvent event)
	{

	}

	/**
	 * Delete pupil from course.
	 *
	 * @param event delete pupil from course id
	 */
	@FXML
	void DeletePupilFromCourse(ActionEvent event)
	{

		Operation = "delete";
		ChooseOperationMenuButton.setText(DeletePupil.getText());

	}

	/**
	 * Delete pupil menu.
	 *
	 * @param event - delete pupil menu
	 */
	@FXML
	void DeletePupilMenu(ActionEvent event)
	{

	}

	/**
	 * Send to school manager.
	 *
	 * @param event - check the request exceptional enters of user in pupil exceptional request 
	 */
	@FXML
	void SendToSchoolManager(ActionEvent event)
	{
		String req = RequestIdTextField.getText();

		if (ReqIDflag == 0 || req.equals(""))
		{
			new Alert(AlertType.ERROR, "Enter Availabile Request ID.", ButtonType.OK).showAndWait();

		}
		else if (cbPupilIDName.equals(""))
		{
			new Alert(AlertType.ERROR, "Please Choose Pupil For The Request", ButtonType.OK).showAndWait();
		}
		else if (cbCourseIDName.equals(""))
		{
			new Alert(AlertType.ERROR, "Please Choose Course For The Request", ButtonType.OK).showAndWait();
		}
		else if (cbClassIDName.equals(""))
		{
			new Alert(AlertType.ERROR, "Please Choose Class For The Request", ButtonType.OK).showAndWait();
		}
		else if (Operation.equals(""))
		{
			new Alert(AlertType.ERROR, "Please Choose Operation For Request", ButtonType.OK).showAndWait();
		}
		else
		{
			String[] temp = cbPupilIDName.split(":");
			PupilID = temp[0];
			temp = cbCourseIDName.split(":");
			CourseID = temp[0];
			loadPupilInCourse();
		}

	}

	/**
	* Load all classes.
	*/
	void loadClasses()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load class in Course");
		data.add("select");
		data.add("course_in_class");
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
	 * Choose class.
	 *
	 * @param event - enter class
	 */
	@FXML
	void ChooseClass(ActionEvent event)
	{

		cbClassIDName = ChooseClassCB.getSelectionModel().getSelectedItem();
		String[] Temp = cbClassIDName.split(":");
		cbClassIDName = Temp[0];
	}

	/**
	 * Back to menu.
	 *
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{

		UserWindow.closeUserWindow(getClass(), (Stage) OperationLable.getScene().getWindow());

	}

	/**
	 * Load all pupils.
	 */
	void loadPupils()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Pupils");
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
	 * Load all courses.
	 */
	void loadCourses()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Courses");
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
	}

	/**
	 * Load all pupil in course.
	 */
	void loadPupilInCourse()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load pupil in Course");
		data.add("select");
		data.add("pupil_in_course");
		data.add("userID");
		data.add(PupilID);
		data.add("courseID");
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
	 * Insert exceptional request.
	 *
	 * @param str - enter comment in exceptional request
	 */
	void InsertExceptionalRequst(String str)
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
		data.add(str);
		data.add("panding");
		data.add(CourseID);
		data.add(PupilID);
		data.add(cbClassIDName);

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
		assert ChooseCourseComboBox != null : "fx:id=\"ChooseCourseComboBox\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert AssignPupil != null : "fx:id=\"AssignPupil\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert RequestIdTextField != null : "fx:id=\"RequestIdTextField\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert ChooseClassCB != null : "fx:id=\"ChooseClassCB\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert CheckIdButton != null : "fx:id=\"CheckIdButton\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert ChoosePupilComboBox != null : "fx:id=\"ChoosePupilComboBox\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert PupilExeptionalLable != null : "fx:id=\"PupilExeptionalLable\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert RequestIdLable != null : "fx:id=\"RequestIdLable\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert ChooseClassLabel != null : "fx:id=\"ChooseClassLabel\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert ChooseOperationMenuButton != null : "fx:id=\"ChooseOperationMenuButton\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert OperationLable != null : "fx:id=\"OperationLable\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert DeletePupil != null : "fx:id=\"DeletePupil\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert SendButton != null : "fx:id=\"SendButton\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert PupilIdLable != null : "fx:id=\"PupilIdLable\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";
		assert CourseListLable != null : "fx:id=\"CourseListLable\" was not injected: check your FXML file 'ExeptionalRequestForPupil.fxml'.";

		Main.client.controller = this;
		Main.stack.push("ExeptionalRequestForPupil");

		ReqIDflag = 0;
		cbCourseIDName = "";
		cbPupilIDName = "";
		Operation = "";
		PupilID = "";
		CourseID = "";
		cbClassIDName = "";
		ID = "";

		loadPupils();
		loadCourses();
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

		if (type.equals("Check RequestID Availbility"))
		{
			if (arr.size() == 0)
			{

				if (ID.length() < 4 || ID.length() > 4)
				{
					new Alert(AlertType.ERROR, "Request ID must contain 4 digits.", ButtonType.OK).showAndWait();
				}
				else
				{
					ReqIDflag = 1;
					new Alert(AlertType.INFORMATION, "Exceptional Request ID is Available.", ButtonType.OK)
							.showAndWait();
				}
			}
			else
			{
				new Alert(AlertType.ERROR, "This Exceptional Request ID already exists.", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("Load Pupils"))
		{
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

					String userId = map.get("userId");
					String PupilFName = map.get("userFirstName");
					String PupilLName = map.get("userLastName");
					ChoosePupilComboBox.getItems().add(userId + ": " + PupilFName + " " + PupilLName);
				}
			}
		}

		if (type.equals("Load Courses"))
		{
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

					String CourseId = map.get("courseId");
					String CourseName = map.get("courseName");
					ChooseCourseComboBox.getItems().add(CourseId + ": " + CourseName);
				}
			}
		}

		if (type.equals("Load pupil in Course"))
		{
			if (arr.size() == 0)
			{
				if (Operation.equals("assign"))
				{
					InsertExceptionalRequst("assign");
				}
				else
				{
					new Alert(AlertType.ERROR, "Pupil not assigned to the course.", ButtonType.OK).showAndWait();
				}
			}
			if (arr.size() != 0)
			{
				if (Operation.equals("assign"))
				{
					new Alert(AlertType.ERROR, "Pupil Already Assigned To This Course", ButtonType.OK).showAndWait();
				}
				else
				{
					InsertExceptionalRequst("delete");
				}
			}
		}
		if (type.equals("Insert new Exeptional Request"))
		{
			if (arr.size() > 0)
			{
				new Alert(AlertType.INFORMATION, "Exceptional Request Opened Susccesfully", ButtonType.OK)
						.showAndWait();
				UserWindow.exitToMenu(getClass(), (Stage) OperationLable.getScene().getWindow());
			}
		}

		if (type.equals("Load class in Course"))
		{
			ChooseClassCB.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}

				String ClassId = map.get("classId");
				ChooseClassCB.getItems().add(ClassId);
			}
		}
	}
}
