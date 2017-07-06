package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import application.FilePart;
import application.FileUpload;
import application.Main;
import application.UserController;
import interfaces.IController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class PupilAssignmentController - this class receive pupil assignment from teacher.
 */
public class PupilAssignmentController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Upload text field. */
	@FXML
	private Label UploadTextField;

	/** The choose course ComboBox. */
	@FXML
	private ComboBox<String> chooseCourseCB;

	/** The Assignment text field. */
	@FXML
	private Label AssignmentTextField;

	/** The Upload button. */
	@FXML
	private Button UploadButton;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The File hyper link. */
	@FXML
	private Hyperlink FileHyperLink;

	/** The choose ass ComboBox. */
	@FXML
	private ComboBox<String> chooseAssCB;

	/** The Progress upload. */
	@FXML
	private ProgressIndicator ProgressUpload;

	/** The Course text field. */
	@FXML
	private Label CourseTextField;

	/** The all courses. */
	private HashMap<String, HashMap<String, String>> allCourses;

	/** The all assignment. */
	private HashMap<String, HashMap<String, String>> allAss;

	/** The Assignment file. */
	private File assignmentFile;

	/** The User ID. */
	private String UserID;

	/**
	 * Upload solution file.
	 *
	 * @param event - enter solution
	 */
	@FXML
	void UploadSolutionFile(ActionEvent event)
	{
		String selectedCourse = chooseCourseCB.getSelectionModel().getSelectedItem();
		String selectedAss = chooseAssCB.getSelectionModel().getSelectedItem();

		if (selectedAss == null)
		{
			new Alert(AlertType.ERROR, "Assignment was not selected!", ButtonType.OK).showAndWait();
			return;
		}

		if (assignmentFile == null)
		{
			new Alert(AlertType.ERROR, "Missing assignment file!", ButtonType.OK).showAndWait();
			return;
		}
		

		String fileName = assignmentFile.getName();
		String expectedExtension = selectedAss.substring(selectedAss.lastIndexOf('.') + 1);
		String uploadedExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

		if (!expectedExtension.equals(uploadedExtension))
		{
			new Alert(AlertType.ERROR, "The Format Of The File Must Be " + expectedExtension, ButtonType.OK).showAndWait();
			return;
		}
		if (selectedCourse == null)
		{
			new Alert(AlertType.ERROR, "Course was not selected!", ButtonType.OK).showAndWait();
			return;
		}

		String courseId = selectedCourse.split(":")[0];
		ProgressUpload.setVisible(true);
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				new FileUpload().SendFile(new FilePart("pupil", null, null, null, Main.userId, courseId, selectedAss, assignmentFile.getAbsolutePath(), null, null, null, null, null, null), new Callable<Void>()
				{
					@Override
					public Void call() throws Exception
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								new Alert(AlertType.INFORMATION, "Assignment was uploaded successfully!", ButtonType.OK).showAndWait();
							}
						});
						return null;
					}
				}, new Callable<Void>()
				{
					@Override
					public Void call() throws Exception
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
							}
						});
						return null;
					}
				}, ProgressUpload);
			}
		}).start();
	}

	/**
	 * Choose solution file.
	 *
	 * @param event - enter the choose solution file
	 */
	@FXML
	void ChooseSolutionFile(ActionEvent event)
	{
		FileChooser chooser = new FileChooser();

		// TODO add extension filter from DB
		chooser.setTitle("Choose assignment solution file");
		assignmentFile = chooser.showOpenDialog(UploadButton.getScene().getWindow());

	}

	/**
	 * Back to pupil menu.
	 *
	 * @param event - enter back to pupil menu
	 */
	@FXML
	void BackToPupilMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) BackButton.getScene().getWindow());
	}

	/**
	 * Choose course.
	 *
	 * @param event - enter choose course
	 */
	@FXML
	void chooseCourse(ActionEvent event)
	{
		String selectedCourse = chooseCourseCB.getSelectionModel().getSelectedItem();
		if (selectedCourse == null)
			return;
		String ans[] = selectedCourse.split(":");
		loadAssOfCourse(ans[0]);
	}

	/**
	 * Choose ass.
	 *
	 * @param event - enter choose assignment
	 */
	@FXML
	void chooseAss(ActionEvent event)
	{
		String selectedAss = chooseAssCB.getSelectionModel().getSelectedItem();
		if (selectedAss == null)
			return;
	}

	/**
	 * Load all courses.
	 */
	void loadAllCourses()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("load Courses");
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
	 * Load courses of pupil.
	 */
	void loadCoursesOfPupil()
	{
		System.out.println("Current user id is " + UserID);
		ArrayList<String> data = new ArrayList<String>();
		data.add("load courses of pupil");
		data.add("select");
		data.add("pupil_in_course");
		data.add("userID");
		data.add(UserID);

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
	 * Load all assignment.
	 */
	void loadAllAss()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("load all assignments");
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
	 * Load ass of course.
	 *
	 * @param courseId - enter course id
	 */
	void loadAssOfCourse(String courseId)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("load assignment of course");
		data.add("select");
		data.add("assignment_in_course");
		data.add("courseID");
		data.add(courseId);

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
		assert UploadTextField != null : "fx:id=\"UploadTextField\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert chooseCourseCB != null : "fx:id=\"chooseCourseCB\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert AssignmentTextField != null : "fx:id=\"AssignmentTextField\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert UploadButton != null : "fx:id=\"UploadButton\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert FileHyperLink != null : "fx:id=\"FileHyperLink\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert chooseAssCB != null : "fx:id=\"chooseAssCB\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert ProgressUpload != null : "fx:id=\"ProgressUpload\" was not injected: check your FXML file 'PupilAssignment.fxml'.";
		assert CourseTextField != null : "fx:id=\"CourseTextField\" was not injected: check your FXML file 'PupilAssignment.fxml'.";

		UserID = UserController.CurrentUserID;

		Main.client.controller = this;
		Main.stack.push("PupilAssignment");

		allCourses = new HashMap<>();
		allAss = new HashMap<>();
		loadAllCourses();
	}

	/**
	 * Handles the answer from the server according to the type of answer.
	 */
	@Override
	public void handleAnswer(Object result)
	{
		System.out.println("got result " + result);
		if (result == null)
		{
			// error

			return;
		}
		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);
		if (type.equals("load Courses"))
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
				allCourses.put(map.get("courseId"), map);
			}
			loadCoursesOfPupil();
		}

		else if (type.equals("load courses of pupil"))
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

				String courseId = map.get("courseID");
				chooseCourseCB.getItems().add(courseId + ": " + allCourses.get(courseId).get("courseName"));
			}
			loadAllAss();
		}
		else if (type.equals("load all assignmens"))
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
				allAss.put(map.get("assignmentName"), map);
			}
		}

		else if (type.equals("load assignment of course"))
		{
			while (chooseAssCB.getItems().size() > 0)
				chooseAssCB.getItems().remove(0);

			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String AssName = map.get("assignmentName");
				chooseAssCB.getItems().add(AssName);

			}
		}

	}

}