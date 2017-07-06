package controllers;

import java.io.IOException;
import java.util.ArrayList;

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
 * The Class DeleteCourseController - this class is delete existing courses.
 */
public class DeleteCourseController implements IController
{

	/** The id text. */
	@FXML
	private TextField idTxt;

	/** The cancel button. */
	@FXML
	private Button cancelBtn;

	/** The enter label. */
	@FXML
	private Label enterLab;

	/** The delete button. */
	@FXML
	private Button deleteBtn;

	/**
	 * Delete course.
	 *
	 * @param event - enter course Id that the user want to delete
	 */
	@FXML
	void DeleteCourse(ActionEvent event)
	{

		ArrayList<String> data = new ArrayList<>();
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

	/**
	 * Delete.
	 */
	void delete()
	{
		ArrayList<String> data = new ArrayList<>();
		data.add("delete course");
		data.add("delete");
		data.add("courses");
		data.add("courseId");
		data.add(idTxt.getText());

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

	/**
	 * Back to menu.
	 *
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) cancelBtn.getScene().getWindow());
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{

		assert idTxt != null : "fx:id=\"idTxt\" was not injected: check your FXML file 'SystemManagerDeleteCourse.fxml'.";
		assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'SystemManagerDeleteCourse.fxml'.";
		assert enterLab != null : "fx:id=\"enterLab\" was not injected: check your FXML file 'SystemManagerDeleteCourse.fxml'.";
		assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'SystemManagerDeleteCourse.fxml'.";

		Main.client.controller = this;
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

		if (type.equals("check if course exist"))
		{
			if (arr.size() == 0)
			{
				new Alert(AlertType.ERROR, "Course Not Exist", ButtonType.OK).showAndWait();
			}
			else
			{
				delete();
			}
		}

		if (type.equals("delete course"))
		{
			new Alert(AlertType.INFORMATION, "Course Deleted Succesfully From DB", ButtonType.OK).showAndWait();
			UserWindow.closeUserWindow(getClass(), (Stage) cancelBtn.getScene().getWindow());
		}
	}

}
