package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
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
 * The Class SecretaryMainWindowController - this class display the main window of secretary.
 */
public class SecretaryMainWindowController implements IController{

    /** The resources. */
    @FXML
    private ResourceBundle resources;

    /** The location. */
    @FXML
    private URL location;

    /** The Choose menu button. */
    @FXML
    private MenuButton ChooseMenuButton;

    /** The Assign pupil. */
    @FXML
    private MenuItem AssignPupil;

    /** The Open course. */
    @FXML
    private MenuItem OpenCourse;

    /** The Assign pupil to class. */
    @FXML
    private MenuItem AssignPupilToClass;

    /** The Secretary menu label. */
    @FXML
    private Label SecretaryMenuLable;

    /** The Open exceptional request menu. */
    @FXML
    private MenuItem OpenExeptionalRequstMenu;

    /** The Choose operational menu. */
    @FXML
    private Label ChooseOperationalMenu;

    /** The Log out button. */
    @FXML
    private Button LogOutButton;

    /** The Re assign. */
    @FXML
    private MenuItem ReAssign;

    /** The Delete pupil. */
    @FXML
    private MenuItem DeletePupil;

    /** The Open semester. */
    @FXML
    private MenuItem OpenSemester;

    /** The Define class. */
    @FXML
    private MenuItem DefineClass;

    /**
     * Choose operation.
     *
     * @param event - Choose operation
     */
    @FXML
    void ChooseOperation(ActionEvent event) {

    }

    /**
     * Open exceptional request window.
     *
     * @param event - Open exceptional request window
     */
    @FXML
    void OpenExeptionalRequstWindow(ActionEvent event) {

    	UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(),
				"SecretaryOpenExeptionalRequest", getClass());
    }

    /**
     * Assign pupil to class window.
     *
     * @param event - Assign pupil to class window
     */
    @FXML
    void AssignPupilToClassWindow(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(), "SecretaryAssignPupilToClass",getClass());
    }

    /**
     * Open course window.
     *
     * @param event - Open course window
     */
    @FXML
    void OpenCourseWindow(ActionEvent event) {    	
    		UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(), "SecretaryOpenCourse",getClass());
    }

    /**
     * Open semester window.
     *
     * @param event - Open semester window
     */
    @FXML
    void OpenSemesterWindow(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(), "SecretaryOpenSemester",getClass());
    }

    /**
     * Define class window.
     *
     * @param event - Define class window
     */
    @FXML
    void DefineClassWindow(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(), "SecretaryDefineClass",getClass());
    }

    /**
     * Assign pupil to course.
     *
     * @param event - Assign pupil to course
     */
    @FXML
    void AssignPupilToCourse(ActionEvent event) {
		UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(),"SecretaryAssignPupilToCourse", getClass());
    }

    /**
     * Delete pupil from course.
     *
     * @param event - Delete pupil from course
     */
    @FXML
    void DeletePupilFromCourse(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(),"SecretaryDeletePupilFromCourse", getClass());
    }

    /**
     * Request assignment teacher.
     *
     * @param event - Request assignment teacher
     */
    @FXML
    void ReAssignTeacher(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseOperationalMenu.getScene().getWindow(), "SecretaryReAssignTeacher",getClass());
    }

    /**
     * Secretary log out.
     *
     * @param event - Secretary log out
     */
    @FXML
    void SecretaryLogOut(ActionEvent event) {
    	
		ArrayList<String> data = new ArrayList<String>();
		data.add("update connection");
		data.add("update");
		data.add("user");
		data.add("isLogged");
		data.add("0");
		data.add("conditions");
		data.add("userId");
		data.add("404040404");
		
 		try
 		{
 			Main.client.sendToServer(data);
 		}
 		catch (IOException e)
 		{
 			e.printStackTrace();
 		}
		UserWindow.closeUserWindow(getClass(), (Stage) SecretaryMenuLable.getScene().getWindow());
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert ChooseMenuButton != null : "fx:id=\"ChooseMenuButton\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert AssignPupil != null : "fx:id=\"AssignPupil\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert OpenCourse != null : "fx:id=\"OpenCourse\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert AssignPupilToClass != null : "fx:id=\"AssignPupilToClass\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert SecretaryMenuLable != null : "fx:id=\"SecretaryMenuLable\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert OpenExeptionalRequstMenu != null : "fx:id=\"OpenExeptionalRequstMenu\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert ChooseOperationalMenu != null : "fx:id=\"ChooseOperationalMenu\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert LogOutButton != null : "fx:id=\"LogOutButton\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert ReAssign != null : "fx:id=\"ReAssign\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert DeletePupil != null : "fx:id=\"DeletePupil\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert OpenSemester != null : "fx:id=\"OpenSemester\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";
        assert DefineClass != null : "fx:id=\"DefineClass\" was not injected: check your FXML file 'SecretaryMainWindow.fxml'.";

        Main.client.controller=this;
        Main.stack.push("SecretaryMainWindow");    
    }

    /**
     * Handles the answer from the server according to the type of answer.
     */   
	@Override
	public void handleAnswer(Object msg) {
		// TODO Auto-generated method stub	
	}
}
