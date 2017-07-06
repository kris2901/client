package controllers;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

import javax.xml.transform.OutputKeys;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class DefineAssignmentController - this class is for Teacher that define assignment to all pupil that learning her course.
 */
public class DefineAssignmentController implements IController
{

	/** The Enter ass name Text Field. */
	@FXML
	private TextField EnterAssNameTF;

	/** The Due date picker. */
	@FXML
	private DatePicker DueDatePicker;

	/** The Enter due date label. */
	@FXML
	private Label EnterDueDateLabel;

	/** The Define assignment label. */
	@FXML
	private Label DefineAssignmentLabel;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Enter classID text field. */
	@FXML
	private TextField EnterClassIDTextField;

	/** The Upload file hyper link. */
	@FXML
	private Hyperlink UploadFileHyperLink;

	/** The Define assignment button. */
	@FXML
	private Button DefineAssignmentButton;

	/** The Define assignment button. */
	@FXML
	private ProgressIndicator pbProgress;

	/** The Enter classId label. */
	@FXML
	private Label EnterClassIdLabel;

	/** The Assignment file. */
	private File AssignmentFile = null;

	/** The flag that check if the assignment is already exist. */
	private volatile boolean isExistingAssignment = false;

	/** The flag that check if the course is already exist. */
	private volatile boolean isExistingCourse = false;

	/** The UesrID. */
	private String UserID;

	/** The semaphore is to resolve the mutual exclusion. */
	private Semaphore semaphore = new Semaphore(0);

	/**
	 * Enter class ID.
	 *
	 * @param event - enter ClassID
	 */
	@FXML
	void EnterClassID(ActionEvent event)
	{

	}

	/**
	 * Enter due date.
	 *
	 * @param event - enter Due Date
	 */
	@FXML
	void EnterDueDate(ActionEvent event)
	{
		if (DueDatePicker.getValue().isBefore(LocalDate.now()))
		{
			// TODO display error
		}
	}

	/**
	 * Enter assignment name.
	 *
	 * @param event - enter assignment name
	 */
	@FXML
	void EnterAssName(ActionEvent event)
	{

	}

	/**
	 * Check existing ass.
	 *
	 * @param assName - enter the assignment name
	 * @param courseId - enter the courseId
	 */
	void checkExistingAss(String assName, String courseId)
	{

		ArrayList<String> data = new ArrayList<String>();
		data.add("check assignment name");
		data.add("select");
		data.add("assignment_in_course");
		data.add("assignmentName");
		data.add(assName);
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
	 * Search course id.
	 *
	 * @param courseId - enter the course id
	 */
	void searchCourseId(String courseId)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("search courseId");
		data.add("select");
		data.add("course_in_class");
		data.add("courseId");
		data.add(courseId);
		data.add("teacherId");
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
	 * @throws  
	 * Define assignment.
	 *
	 * @param event - enter Define Assignment
	 * @throws  
	 */
	@FXML
	void DefineAssignment(ActionEvent event)
	{
		String filename = EnterAssNameTF.getText();
		String courseId = EnterClassIDTextField.getText();
		String date = DueDatePicker.getValue().getYear() + "-" + DueDatePicker.getValue().getMonthValue() + "-" + DueDatePicker.getValue().getDayOfMonth() + " 23:59:59";
		
		String str = AssignmentFile.getName();
		String title = EnterAssNameTF.getText();

		if (AssignmentFile == null)
		{
			new Alert(AlertType.ERROR, "Missing assignment file!", ButtonType.OK).showAndWait();
			return;
		}
		else if (filename.equals(""))
		{
			new Alert(AlertType.ERROR, "Missing assignment name!", ButtonType.OK).showAndWait();
			return;
		}
		else if (courseId.equals(""))
		{
			new Alert(AlertType.ERROR, "Missing course Id!", ButtonType.OK).showAndWait();
			return;
		}
		else if (DueDatePicker.getValue() == null)
		{
			new Alert(AlertType.ERROR, "Missing date!", ButtonType.OK).showAndWait();
			return;
		
		}
		
		pbProgress.setVisible(true);
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				new FileUpload().SendFile(new FilePart("self", date, filename, null, Main.userId, courseId, null, AssignmentFile.getAbsolutePath(), null, null, null, null, null, null), new Callable<Void>()
				{
					@Override
					public Void call() throws Exception
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								new Alert(AlertType.INFORMATION, "Assignment was defined successfully!", ButtonType.OK).showAndWait();

								isExistingAssignment = false;
								isExistingCourse = false;

								UserWindow.closeUserWindow(getClass(), (Stage) EnterClassIdLabel.getScene().getWindow());
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
				}, pbProgress);
			}
		}).start();
	}

	/**
	 * Back to menu.
	 *
	 * @param event - enter back to menu 
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) EnterClassIdLabel.getScene().getWindow());
	}

	/**
	 * Choose file to upload.
	 *
	 * @param event 
	 */
	@FXML
	void ChooseFileToUpload(ActionEvent event)
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose assignment file");
		AssignmentFile = chooser.showOpenDialog(DefineAssignmentButton.getScene().getWindow());

	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert DueDatePicker != null : "fx:id=\"DueDatePicker\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert EnterDueDateLabel != null : "fx:id=\"EnterDueDateLabel\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert DefineAssignmentLabel != null : "fx:id=\"DefineAssignmentLabel\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert EnterClassIDTextField != null : "fx:id=\"EnterClassIDTextField\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert UploadFileHyperLink != null : "fx:id=\"UploadFileHyperLink\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert DefineAssignmentButton != null : "fx:id=\"DefineAssignmentButton\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert EnterClassIdLabel != null : "fx:id=\"EnterClassIdLabel\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";
		assert EnterAssNameTF != null : "fx:id=\"EnterAssNameTF\" was not injected: check your FXML file 'TeacherDefineAssignment.fxml'.";

		Main.client.controller = this;
		UserID = UserController.CurrentUserID;
		Main.stack.push("TeacherDefineAssignment");
		pbProgress.setVisible(false);
	}

	/**
	 * Handles the answer from the server according to the type of answer.
	 */
	@Override
	public void handleAnswer(Object result)
	{
		String ans = (String) result;
		System.out.println("got answer");
		if (result == null)
		{
			// error
			System.out.println("error");
			return;
		}

		if (ans.equals("success"))
		{
			new Alert(AlertType.ERROR, "The assignment was uploaded successfully!", ButtonType.OK).showAndWait();
			UserWindow.closeUserWindow(getClass(), (Stage) BackButton.getScene().getWindow());
		}

		/*ArrayList<String> arr = (ArrayList<String>) result;
		System.out.println("SIZE " + arr.size() + " CONTENTS " + arr.toString());
		
		String type = arr.remove(0);
		
		if (type.equals("search courseId")) {
			isExistingCourse = !arr.isEmpty();
			System.out.println("FINISHED WITH " + isExistingCourse);
		} else if (type.equals("check assignment name")) {
			isExistingAssignment = !arr.isEmpty();
		}*/

		//semaphore.release();
	}
}

