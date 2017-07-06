package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
 * The Class PupilMainWindow - this class display the pupil main window.
 */
public class PupilMainWindow implements IController{

    /** The resources. */
    @FXML
    private ResourceBundle resources;

    /** The location. */
    @FXML
    private URL location;

    /** The Choose menu button. */
    @FXML
    private MenuButton ChooseMenuButton;
    
    /** The upload ass. */
    @FXML
    private MenuItem uploadAss;
    
    @FXML
    private MenuItem DownLoadAssMenu;

    /** The Pupil file. */
    @FXML
    private MenuItem PupilFile;

    /** The Pupil menu label. */
    @FXML
    private Label PupilMenuLable;

    /** The Log out button. */
    @FXML
    private Button LogOutButton;

    /** The Choose operation label. */
    @FXML
    private Label ChooseOperationLable;
    
    /** The User ID. */
    private String UserID;
   
    /**
     * Pupil choose operation.
     *
     * @param event - choose pupil operation
     */
    @FXML
    void PupilChooseOperation(ActionEvent event) {
    }

    /**
     * Pupil log out.
     *
     * @param event - pupil log out
     */
    @FXML
    void PupilLogOut(ActionEvent event) {
    	
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
    	UserWindow.closeUserWindow(getClass(), (Stage)PupilMenuLable.getScene().getWindow());
    }
    
    /**
     * Upload ass.
     *
     * @param event - upload assignment
     */
    @FXML
    void uploadAss(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseMenuButton.getScene().getWindow(), "PupilAssignment", getClass());  
    }

    /**
     * Open pupil file.
     *
     * @param event - open pupil file
     */
    @FXML
    void openPupilFile(ActionEvent event) {
    	UserWindow.createUserWindow((Stage) ChooseMenuButton.getScene().getWindow(), "PupilFile", getClass());
    }
    

    @FXML
    void DownLoadAss(ActionEvent event) {

    	UserWindow.createUserWindow((Stage) ChooseMenuButton.getScene().getWindow(), "PupilDownloadAssignment", getClass());
    }
    
    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert ChooseMenuButton != null : "fx:id=\"ChooseMenuButton\" was not injected: check your FXML file 'PupilMainWindow.fxml'.";
        assert PupilMenuLable != null : "fx:id=\"PupilMenuLable\" was not injected: check your FXML file 'PupilMainWindow.fxml'.";
        assert LogOutButton != null : "fx:id=\"LogOutButton\" was not injected: check your FXML file 'PupilMainWindow.fxml'.";
        assert uploadAss != null : "fx:id=\"uploadAss\" was not injected: check your FXML file 'PupilMainWindow.fxml'.";
        assert PupilFile != null : "fx:id=\"PupilFile\" was not injected: check your FXML file 'PupilMainWindow.fxml'.";
        assert ChooseOperationLable != null : "fx:id=\"ChooseOperationLable\" was not injected: check your FXML file 'PupilMainWindow.fxml'.";

        UserID=UserController.CurrentUserID;
        Main.client.controller=this; 
        Main.stack.push("PupilMainWindow");
        
        loadPupil();
    }

    /**
     * Load pupil.
     */
    void loadPupil()
    {
    	ArrayList<String> data = new ArrayList<String>();
    	data.add("Client Pupil");
    	data.add("select");
    	data.add("pupil");
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
     * Handles the answer from the server according to the type of answer.
     */   
    @Override
    public void handleAnswer(Object result) {
    	if (result == null)
    	{
    		// error
    		return;
    	}
    	
    	ArrayList<String> arr = (ArrayList<String>) result;
    	String type = arr.remove(0);
    	if (type.equals("Client Pupil"))
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
    		}
    	}
    }
}