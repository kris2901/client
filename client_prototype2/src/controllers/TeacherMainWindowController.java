package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.UserController;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class TeacherMainWindowController - this class present the main window of teacher
 */
public class TeacherMainWindowController implements IController  {

	/** The resources . */
    @FXML
    private ResourceBundle resources;

    /** The location . */
    @FXML
    private URL location;

    /** The menu item . */
    @FXML
    private MenuItem DefineAssignment;

    /** The teacher menu button. */
    @FXML
    private MenuButton TeacherMenuButton;

    /** The evaluation form . */
    @FXML
    private MenuItem EvaluationForm;

    /** The check assignment menu item . */
    @FXML
    private MenuItem checkAssMenuItem;

    /** The teacher choose label . */
    @FXML
    private Label TeacherChooseLable;

    /** The log out button. */
    @FXML
    private Button LogOutButton;

    /** The teacher main label. */
    @FXML
    private Label TeacherMainLable;
    
    /** The user id . */
    private  String UserID;

    /**
     * Teacher Choose Operation
     *
     * @param event - Teacher Choose Operation
     */
    @FXML
    void TeacherChooseOperation(ActionEvent event) {

    }

    /**
     * Define Assignment Window
     *
     * @param event - Define Assignment Window
     */
    @FXML
    void DefineAssignmentWindow(ActionEvent event) {
    	
    	UserWindow.createUserWindow((Stage) LogOutButton.getScene().getWindow(), "TeacherDefineAssignment", getClass());
    }

    /**
     * Define
     *
     * @param event - Define
     */
    @FXML
    void Define(ActionEvent event) {

    }

    /**
     * Evaluation Form Window
     *
     * @param event - Evaluation Form Window
     */
    @FXML
    void EvaluationFormWindow(ActionEvent event) {
    	
    	UserWindow.createUserWindow((Stage) TeacherMenuButton.getScene().getWindow(), "TeacherCreateEvaluationForm", getClass());
    }


    /**
     * check Assignment
     *
     * @param event - check Assignment
     */
    @FXML
    void checkAss(ActionEvent event) {
    	
    	UserWindow.createUserWindow((Stage) TeacherMenuButton.getScene().getWindow(), "CheckAssignment", getClass());
    }
    
    /**
     * Log Out Teacher
     *
     * @param event - Log Out Teacher
     */
    @FXML
    void LogOutTeacher(ActionEvent event) {
    	
		ArrayList<String> data = new ArrayList<String>();
		data.add("update connection");
		data.add("update");
		data.add("user");
		data.add("isLogged");
		data.add("0");
		data.add("conditions");
		data.add("userId");
		data.add(UserID);
		
 		try
 		{
 			Main.client.sendToServer(data);
 		}
 		catch (IOException e)
 		{
 			e.printStackTrace();
 		}
 		
    	UserWindow.closeUserWindow(getClass(), (Stage)TeacherMainLable.getScene().getWindow());
    }

    /**
     * initialize
     */
    @FXML
    void initialize() {
        assert DefineAssignment != null : "fx:id=\"DefineAssignment\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";
        assert TeacherMenuButton != null : "fx:id=\"TeacherMenuButton\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";
        assert EvaluationForm != null : "fx:id=\"EvaluationForm\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";
        assert checkAssMenuItem != null : "fx:id=\"checkAssMenuItem\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";
        assert TeacherChooseLable != null : "fx:id=\"TeacherChooseLable\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";
        assert LogOutButton != null : "fx:id=\"LogOutButton\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";
        assert TeacherMainLable != null : "fx:id=\"TeacherMainLable\" was not injected: check your FXML file 'TeacherMainWindow.fxml'.";

        Main.client.controller=this;
        UserID=UserController.CurrentUserID;
        Main.stack.push("TeacherMainWindow");

    }

    /**
     * Handles the answer from the server according to the type of answer.
     */
	@Override
	public void handleAnswer(Object msg) {
		// TODO Auto-generated method stub
	}
}
