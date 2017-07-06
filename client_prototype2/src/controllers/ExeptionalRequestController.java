package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class ExeptionalRequestController - this class for all the exceptional request.
 */
public class ExeptionalRequestController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;
	
    /** The Teacher button. */
    @FXML
    private Button TeacherButton;

	/** The Choose exceptional request label. */
	@FXML
	private Label ChooseExeptionalRequestLabel;
	
    /** The Exceptional request label. */
    @FXML
    private Label ExeptionalRequestLabel;

	/** The Back button. */
	@FXML
	private Button BackButton;

    /** The Pupil button. */
    @FXML
    private Button PupilButton;

	/**
	 * Delete or assign pupil.
	 *
	 * @param event 
	 */
	@FXML
	void DeleteOrAssignPupil(ActionEvent event)
	{

		UserWindow.createUserWindow((Stage) BackButton.getScene().getWindow(), "ExeptionalRequestForPupil", getClass());

	}

	/**
	 * Request assignment teacher.
	 *
	 * @param event - enter request assignment teacher
	 */
	@FXML
	void ReAssignTeacher(ActionEvent event)
	{

		UserWindow.createUserWindow((Stage) BackButton.getScene().getWindow(), "ExeptionalRequestForTeacher",
				getClass());
	}

	/**
	 * Back to menu.
	 *
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{

		UserWindow.closeUserWindow(getClass(), (Stage) ExeptionalRequestLabel.getScene().getWindow());
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert TeacherButton != null : "fx:id=\"TeacherButton\" was not injected: check your FXML file 'SecretaryOpenExeptionalRequest.fxml'.";
		assert ChooseExeptionalRequestLabel != null : "fx:id=\"ChooseExeptionalRequestLabel\" was not injected: check your FXML file 'SecretaryOpenExeptionalRequest.fxml'.";
		assert ExeptionalRequestLabel != null : "fx:id=\"ExeptionalRequestLabel\" was not injected: check your FXML file 'SecretaryOpenExeptionalRequest.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SecretaryOpenExeptionalRequest.fxml'.";
		assert PupilButton != null : "fx:id=\"PupilButton\" was not injected: check your FXML file 'SecretaryOpenExeptionalRequest.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SecretaryOpenExeptionalRequest");
	}

    /**
     * Handles the answer from the server according to the type of answer.
     */   
	@Override
	public void handleAnswer(Object msg)
	{
		// TODO Auto-generated method stub

	}
}
