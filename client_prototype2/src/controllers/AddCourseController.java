package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
 * The Class AddCourseController - this class adding a course to DB from the System manager
 */
public class AddCourseController implements IController
{

	/** The resources . */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Open label. */
	@FXML
	private Label OpenLabel;

	/** The id text. */
	@FXML
	private TextField idTxt;

	/** The cancel button. */
	@FXML
	private Button cancelBtn;

	/** The name text. */
	@FXML
	private TextField nameTxt;

	/** The unit text. */
	@FXML
	private TextField unitTxt;

	/** The check hours Button. */
	@FXML
	private Button checkHoursBTN;

	/** The course name label. */
	@FXML
	private Label courseNameLbl;

	/** The id label. */
	@FXML
	private Label idLbl;

	/** The add Button. */
	@FXML
	private Button addBtn;

	/** The check course name Button. */
	@FXML
	private Button checkCourseNameBTN;

	/** The hours text. */
	@FXML
	private TextField hoursTxt;

	/** The s unit label. */
	@FXML
	private Label sUnitLbl;

	/** The hours label. */
	@FXML
	private Label hoursLbl;

	/** The check courseID Button. */
	@FXML
	private Button checkCourseIDBTN;

	/** The Check study unit Button. */
	@FXML
	private Button CheckStudyUnitBTN;

	/** The Course ID flag. */
	private int CourseIDf;

	/** The Course name flag. */
	private int CourseNamef;

	/** The Study unit. */
	private int StuduUnit;

	/** The Weekly hours. */
	private int WeeklyHours;

	/**
	 * Adding course to semester.
	 *
	 * @param event - enter Course
	 */
	@FXML
	void addCourse(ActionEvent event)
	{

		if (CourseIDf == 0)
		{
			new Alert(AlertType.ERROR, "Enter Correct Course ID and Check It.", ButtonType.OK).showAndWait();

		}
		else if (CourseNamef == 0)
		{
			new Alert(AlertType.ERROR, "Enter Correct Course Name and Check It.", ButtonType.OK).showAndWait();
		}
		else if (StuduUnit == 0)
		{
			new Alert(AlertType.ERROR, "Enter Correct Study Unit and Check It.", ButtonType.OK).showAndWait();
		}
		else if (WeeklyHours == 0)
		{
			new Alert(AlertType.ERROR, "Enter Correct Weekly Study Hours and Check It.", ButtonType.OK).showAndWait();
		}
		else if (!(unitTxt.getText().equals(idTxt.getText().substring(0, 2))))
		{
			new Alert(AlertType.ERROR, "Course ID Not Match For Study Unit.", ButtonType.OK).showAndWait();
		}
		else
		{
			ArrayList<String> data = new ArrayList<>();
			data.add("add course");
			data.add("insert");
			data.add("courses");
			data.add("courseId");
			data.add("courseName");
			data.add("weeklyStudyHours");
			data.add("studyUnit");

			data.add("values");
			data.add(idTxt.getText());
			data.add(nameTxt.getText());
			data.add(hoursTxt.getText());
			data.add(unitTxt.getText());

			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * Back to menu.
	 *
	 * @param event - back to Secretary Menu
	 */
	@FXML
	void backToMenu(ActionEvent event)
	{

		UserWindow.closeUserWindow(getClass(), (Stage) cancelBtn.getScene().getWindow());
	}

	/**
	 * Checking the validation of study unit.
	 *
	 * @param event - enter Study Unit
	 */
	@FXML
	void CheckStudyUnit(ActionEvent event)
	{

		if (unitTxt.getText().length() != 2)
		{
			new Alert(AlertType.ERROR, "Styudy Unit must Contain 2 digits", ButtonType.OK).showAndWait();
		}
		else
		{
			ArrayList<String> data = new ArrayList<String>();
			data.add("check if ST exist");
			data.add("select");
			data.add("study_unit");
			data.add("studyUnitID");
			data.add(unitTxt.getText());

			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checking the validation of weekly hours.
	 *
	 * @param event - enter weekly hours
	 */
	@FXML
	void checkHours(ActionEvent event)
	{

		int flag = 0, num = 0, i = 0;
		String hours = hoursTxt.getText();
		if (hours.isEmpty())
		{
			new Alert(AlertType.ERROR, "Please enter weekly study hourse", ButtonType.OK).showAndWait();
		}
		else
		{
			for (i = 0; i < hours.length(); i++)
			{
				for (int j = 0; j < 9; j++)
				{
					String JS = Integer.toString(j);
					if (hours.substring(i, i + 1).equals(JS))
					{
						flag = 1;
						break;
					}
				}
				if (flag == 0)
				{
					new Alert(AlertType.ERROR, "Weekly study hours must be number", ButtonType.OK).showAndWait();
					break;
				}
				else
				{
					flag = 0;
				}
			}
		}

		if (i == hours.length())
		{
			num = Integer.parseInt(hours);
			if (num <= 0)
			{
				new Alert(AlertType.ERROR, "Wrong Weekly Study Hours", ButtonType.OK).showAndWait();
			}
			else
			{
				WeeklyHours = 1;
				new Alert(AlertType.INFORMATION, "Weekly Study Is OK", ButtonType.OK).showAndWait();
			}
		}
	}

	/**
	 * Checking the course name.
	 *
	 * @param event - enter Course Name
	 */
	@FXML
	void checkCourseName(ActionEvent event)
	{

		ArrayList<String> data = new ArrayList<String>();
		data.add("check if name availeble");
		data.add("select");
		data.add("courses");
		data.add("courseName");
		data.add(nameTxt.getText());

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
	 * Checking the validation of course ID.
	 *
	 * @param event - enter Course ID
	 */
	@FXML
	void checkCourseID(ActionEvent event)
	{

		if (idTxt.getText().length() != 5)
		{
			new Alert(AlertType.ERROR, "Course ID must Contain 5 digits", ButtonType.OK).showAndWait();
		}

		else
		{
			for (int i = 0; i < idTxt.getText().length(); i++)
			{
				if (!(idTxt.getText().charAt(i) <= '9' && idTxt.getText().charAt(i) >= '0'))
				{
					new Alert(AlertType.ERROR, "Course ID must contain digits only", ButtonType.OK).showAndWait();
					return;
				}
			}

			ArrayList<String> data = new ArrayList<String>();
			data.add("check if course exist");
			data.add("select");
			data.add("courses");
			data.add("courseId");
			data.add(idTxt.getText());

			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert OpenLabel != null : "fx:id=\"OpenLabel\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert idTxt != null : "fx:id=\"idTxt\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert nameTxt != null : "fx:id=\"nameTxt\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert unitTxt != null : "fx:id=\"unitTxt\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert checkHoursBTN != null : "fx:id=\"checkHoursBTN\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert courseNameLbl != null : "fx:id=\"courseNameLbl\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert idLbl != null : "fx:id=\"idLbl\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert checkCourseNameBTN != null : "fx:id=\"checkCourseNameBTN\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert hoursTxt != null : "fx:id=\"hoursTxt\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert sUnitLbl != null : "fx:id=\"sUnitLbl\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert hoursLbl != null : "fx:id=\"hoursLbl\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert checkCourseIDBTN != null : "fx:id=\"checkCourseIDBTN\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";
		assert CheckStudyUnitBTN != null : "fx:id=\"CheckStudyUnitBTN\" was not injected: check your FXML file 'SystemManagerAddCourse.fxml'.";

		Main.client.controller = this;
		CourseIDf = 0;
		CourseNamef = 0;
		StuduUnit = 0;
		WeeklyHours = 0;
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
			new Alert(AlertType.ERROR, "Item Was Not Found.", ButtonType.OK).showAndWait();

			return;
		}
		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);

		if (type.equals("add course"))
		{
			new Alert(AlertType.INFORMATION, "Course Add Succesfully To System", ButtonType.OK).showAndWait();
			UserWindow.closeUserWindow(getClass(), (Stage) checkCourseNameBTN.getScene().getWindow());
		}

		if (type.equals("check if course exist"))
		{
			if (arr.size() != 0)
			{
				new Alert(AlertType.ERROR, "Course Already Exist In System", ButtonType.OK).showAndWait();
			}
			else
			{
				CourseIDf = 1;
				new Alert(AlertType.INFORMATION, "Course ID Available", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("check if name availeble"))
		{
			if (arr.size() != 0)
			{
				new Alert(AlertType.ERROR, "Course Name Not Available", ButtonType.OK).showAndWait();
			}
			else
			{
				CourseNamef = 1;
				new Alert(AlertType.INFORMATION, "Course Name Available", ButtonType.OK).showAndWait();
			}
		}

		if (type.equals("check if ST exist"))
		{
			if (arr.size() != 0)
			{
				StuduUnit = 1;
				new Alert(AlertType.INFORMATION, "Study Unit Exist", ButtonType.OK).showAndWait();
			}
			else
			{
				new Alert(AlertType.ERROR, "Study Unit Not Exist", ButtonType.OK).showAndWait();
			}
		}
	}
}
