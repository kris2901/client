package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.Main;
import application.UserController;
import entities.*;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class ParentController - this class is for parent user.
 */
public class ParentController implements IController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

	/** The Parent label. */
	@FXML
	private Label ParentLabel;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Choose child combo box. */
	@FXML
	private ComboBox<String> ChooseChildComboBox;
	
    @FXML
    private Label ChooseLabel;

	/** The all pupils. */
	private HashMap<String, HashMap<String, String>> allPupils;

	/** The User ID. */
	private String UserID;
	
	private PupilFileController PupilFileController;
	
	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert ParentLabel != null : "fx:id=\"ParentLabel\" was not injected: check your FXML file 'Parent.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'Parent.fxml'.";
		assert ChooseChildComboBox != null : "fx:id=\"ChooseChildComboBox\" was not injected: check your FXML file 'Parent.fxml'.";
	    assert ChooseLabel != null : "fx:id=\"ChooseLabel\" was not injected: check your FXML file 'Parent.fxml'.";

		UserID=UserController.CurrentUserID;
		Main.client.controller = this;
		Main.stack.push("Parent");
		
		allPupils = new HashMap<>();
		loadAllPupils();
	}

	/**
	 * Load all pupils.
	 */
	void loadAllPupils()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Client User");
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
	 * Load pupils parent.
	 */
	void loadPupilsParent()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Client Pupil");
		data.add("select");
		data.add("pupil");
		data.add("parentID");
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
	public void handleAnswer(Object result)
	{
		if (result == null)
		{
			// error
			return;
		}

		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);
		if (type.equals("Client User"))
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
				allPupils.put(map.get("userId"), map);
			}
			loadPupilsParent();
		}
		else if (type.equals("Client Pupil"))
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

				String userId = map.get("userID");
				ChooseChildComboBox.getItems().add(userId + ": " + allPupils.get(userId).get("userFirstName") + " "
						+ allPupils.get(userId).get("userLastName"));
			}
		}
	}

	/**
	 * Back to menu.
	 *
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event) // change to logout
	{
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
		UserWindow.closeUserWindow(getClass(), (Stage) BackButton.getScene().getWindow());
	}

	/**
	 * Choose child.
	 *
	 * @param event - enter child 
	 */
	@FXML
	void ChooseChild(ActionEvent event)
	{
		String selectedChild = ChooseChildComboBox.getSelectionModel().getSelectedItem();
		if (selectedChild == null)
			return;
		String ans[] = selectedChild.split(":");
		UserController.CurrentUserID = ans[0];
		PupilFileController.ParentID = UserID;
		UserWindow.createUserWindow((Stage) ChooseChildComboBox.getScene().getWindow(), "PupilFile", getClass());
	}

}
