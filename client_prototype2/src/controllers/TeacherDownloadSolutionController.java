package controllers;

import interfaces.IController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import application.FilePart;
import application.FileUpload;
import application.Main;
import application.UserController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.UserWindow;
import javafx.scene.control.Hyperlink;

/**
 * The Class TeacherDownloadSolutionController - this class download solution to teacher
 */
public class TeacherDownloadSolutionController implements IController
{

	/** The resources . */
	@FXML
	private ResourceBundle resources;

	/** The location . */
	@FXML
	private URL location;

	/** The Back To Menu Button . */
	@FXML
	private Button BackToMenuButton;

	/** The check assignment . */
	@FXML
	private Label CheckPupilAssignment;

	/** The Choose Assignment Combo Box . */
	@FXML
	private ComboBox<String> ChooseAssignmentCB;

	@FXML
	private ProgressIndicator ProgressUpload;
	
	@FXML
	private ProgressIndicator ProgressDownload;

	@FXML
	private Label PupilLabel;

	@FXML
	private Hyperlink UploadHyperLink;

	/** The Choose Pupil Combo Box . */
	@FXML
	private ComboBox<String> ChoosePupilCB;

	/** The Choose course Combo Box . */
	@FXML
	private ComboBox<String> ChooseCourseCB;

	/** The download button . */
	@FXML
	private Button DownloadButton;

	@FXML
	private Label CourseLabel;

	@FXML
	private Label AssignmentLabel;

	/** The combo box choice . */
	private String CBchoice;

	/** The assignment name . */
	private String AssName;

	/** The pupil choice . */
	private String pupilChoice;

	private String UserID;

	/**
	 * choose course
	 *
	 * @param event - choose course
	 */
	@FXML
	void ChooseCourse(ActionEvent event)
	{
		CBchoice = ChooseCourseCB.getSelectionModel().getSelectedItem();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Assignment in course");
		data.add("select");
		data.add("assignment");
		data.add("courseId");
		data.add(CBchoice);

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
	 * choose assignment
	 *
	 * @param event - choose assignment
	 */
	@FXML
	void ChooseAssignment(ActionEvent event)
	{
		AssName = ChooseAssignmentCB.getSelectionModel().getSelectedItem();
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Pupil");
		data.add("select");
		data.add("pupil_in_course");
		data.add("courseID");
		data.add(CBchoice);
		//data.add("gradeInCourse");
		//data.add("0");

		try
		{
			Main.client.sendToServer(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadCourses()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Load Courses for Teacher");
		data.add("select");
		data.add("course_in_class");
		data.add("teacherId");
		data.add(UserController.CurrentUserID);

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
	 * download solution
	 *
	 * @param event - download solution
	 */
	@FXML
	void DownloadSolution(ActionEvent event)
	{
		if (CBchoice.equals(""))
		{
			new Alert(AlertType.ERROR, "Please choose course from list first.", ButtonType.OK).showAndWait();
			return;
		}
		else if (AssName.equals(""))
		{
			new Alert(AlertType.ERROR, "Please choose assignment from list first.", ButtonType.OK).showAndWait();
			return;
		}
		else if (pupilChoice.equals(""))
		{
			new Alert(AlertType.ERROR, "Please choose pupil from list first.", ButtonType.OK).showAndWait();
			return;
		}
		
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Choose directory");
		File dir = dirChooser.showDialog((Stage) ProgressDownload.getScene().getWindow());
		if (dir == null)
			return;

		ProgressDownload.setVisible(true);
		ProgressDownload.setProgress(0);
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				new FileUpload().RecieveFile(dir.getAbsolutePath(), new FilePart("self"/* task */, null, null, null, pupilChoice, CBchoice, AssName, null, null, null, null, null, null, null), new Callable<Void>()
				{
					@Override
					public Void call() throws Exception
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								//ProgressDownload.setVisible(false);
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
								//ProgressDownload.setVisible(false);
							}
						});
						return null;
					}
				}, ProgressDownload);
			}
		}).start();
	}

	/**
	 * back to menu
	 *
	 * @param event - back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) BackToMenuButton.getScene().getWindow());
	}

	/**
	 * choose pupil
	 *
	 * @param event - choose pupil
	 */
	@FXML
	void ChoosePupil(ActionEvent event)
	{
		pupilChoice = ChoosePupilCB.getSelectionModel().getSelectedItem();
	}

	@FXML
	void UploadSolutionWithComments(ActionEvent event)
	{
		FileChooser chooser = new FileChooser();

		// TODO add extension filter from DB
		chooser.setTitle("Choose assignment checked file");
		File assignmentFile = chooser.showOpenDialog(DownloadButton.getScene().getWindow());
		
		String selectedCourse = ChooseCourseCB.getSelectionModel().getSelectedItem();
		String selectedAss = ChooseAssignmentCB.getSelectionModel().getSelectedItem();
		String selectedPupil = ChoosePupilCB.getSelectionModel().getSelectedItem();

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
				new FileUpload().SendFile(new FilePart("pupil check", null, null, null, selectedPupil, courseId, selectedAss, assignmentFile.getAbsolutePath(), null, null, null, null, null, null), new Callable<Void>()
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
	 * initialize
	 */
	@FXML
	void initialize()
	{
		assert BackToMenuButton != null : "fx:id=\"BackToMenuButton\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert CheckPupilAssignment != null : "fx:id=\"CheckPupilAssignment\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert ChooseAssignmentCB != null : "fx:id=\"ChooseAssignmentCB\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert PupilLabel != null : "fx:id=\"PupilLabel\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert UploadHyperLink != null : "fx:id=\"UploadHyperLink\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert ChoosePupilCB != null : "fx:id=\"ChoosePupilCB\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert ChooseCourseCB != null : "fx:id=\"ChooseCourseCB\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert DownloadButton != null : "fx:id=\"DownloadButton\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert CourseLabel != null : "fx:id=\"CourseLabel\" was not injected: check your FXML file 'CheckAssignment.fxml'.";
		assert AssignmentLabel != null : "fx:id=\"AssignmentLabel\" was not injected: check your FXML file 'CheckAssignment.fxml'.";

		Main.client.controller = this;
		UserID = UserController.CurrentUserID;
		Main.stack.push("CheckAssignment");
		loadCourses();
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

		if (type.equals("Load Courses for Teacher"))
		{
			ChooseCourseCB.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String CourseID = map.get("courseId");
				ChooseCourseCB.getItems().add(CourseID);
			}
		}

		if (type.equals("Load Pupil"))
		{
			ChoosePupilCB.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String userID = map.get("userID");
				//String UserName = map.get("userFirstName");
				//String UserLName = map.get("userLastName");
				ChoosePupilCB.getItems().add(userID/* + ": " + UserName + " " + UserLName*/);
			}
		}

		if (type.equals("Load Assignment in course"))
		{
			ChooseAssignmentCB.getItems().clear();
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String assignmentName = map.get("assignmentName");
				ChooseAssignmentCB.getItems().add(assignmentName);
			}
		}
	}

}