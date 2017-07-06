package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.Main;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The Class BlockParentBySchoolManagerController.
 */
public class BlockParentBySchoolManagerController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Block parent button. */
	@FXML
	private Button BlockParentButton;

	/** The Block user label. */
	@FXML
	private Label BlockUserLabel;

	/** The Pupil ID text field. */
	@FXML
	private TextField PupilIDTextField;

	/** The Enter pupil ID label. */
	@FXML
	private Label EnterPupilIDLabel;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Free parent button. */
	@FXML
	private Button FreeParentButton;

	/** The Parent Id. */
	private String ParentId;

	/** The flag block. */
	private boolean flagBlock;

	/** The access. */
	private String access;

	private String PupilID;

	/**
	 * Enter pupil ID.
	 *
	 * @param event of enter pupil ID
	 */
	@FXML
	void EnterPupilID(ActionEvent event)
	{

	}

	/**
	 * Block Parent.
	 *
	 * @param event - checking if the flag block is true and after that find the specific pupil
	 */
	@FXML
	void BlockParent(ActionEvent event)
	{
		try
		{
			Integer.parseInt(PupilIDTextField.getText());
		}
		catch (NumberFormatException e)
		{
			new Alert(AlertType.ERROR, "Pupil ID must contain digits only", ButtonType.OK).showAndWait();
			return;
		}
		flagBlock = true;
		findPupil();
	}

	/**
	 * Back to menu.
	 *
	 * @param event - the event back to menu of School Manager
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) BackButton.getScene().getWindow());
	}

	/**
	 * Free parent.
	 *
	 * @param event - checking if the flag block is false and after that find the specific pupil
	 */
	@FXML
	void FreeParent(ActionEvent event)
	{
		try
		{
			Integer.parseInt(PupilIDTextField.getText());
		}
		catch (NumberFormatException e)
		{
			new Alert(AlertType.ERROR, "Pupil ID must contain digits only", ButtonType.OK).showAndWait();
			return;
		}
		flagBlock = false;
		findPupil();
	}

	/**
	 * Finding all the pupils.
	 */
	void findPupil()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("find Pupil");
		data.add("select");
		data.add("pupil");
		data.add("userID");
		PupilID = PupilIDTextField.getText();
		data.add(PupilID);
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
	 * Update parent permission.
	 *
	 * @param ParentId - the parent id
	 * @param acc - the access of parent
	 */
	void updateParentPermission(String ParentId, String acc)
	{
		ArrayList<String> data = new ArrayList<String>();
		if (acc.equals("block"))
		{
			if (access.equals("1"))
			{
				new Alert(AlertType.ERROR, "Parent is already Block!", ButtonType.OK).showAndWait();
				return;
			}
			data.add("Block Parent");
			data.add("update");
			data.add("pupil");
			data.add("ParentAccess");
			data.add("1");
			data.add("conditions");
			data.add("parentID");
			data.add(ParentId);
			
			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else if (acc.equals("free"))
		{
			if (access.equals("0"))
			{
				new Alert(AlertType.ERROR, "Parent is already free!", ButtonType.OK).showAndWait();
				return;
			}
			data.add("Free Parent");
			data.add("update");
			data.add("pupil");
			data.add("ParentAccess");
			data.add("0");
			data.add("conditions");
			data.add("parentID");
			data.add(ParentId);
			
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
		assert BlockParentButton != null : "fx:id=\"BlockParentButton\" was not injected: check your FXML file 'SchoolManagerBlockUser.fxml'.";
		assert BlockUserLabel != null : "fx:id=\"BlockUserLabel\" was not injected: check your FXML file 'SchoolManagerBlockUser.fxml'.";
		assert PupilIDTextField != null : "fx:id=\"PupilIDTextField\" was not injected: check your FXML file 'SchoolManagerBlockUser.fxml'.";
		assert EnterPupilIDLabel != null : "fx:id=\"EnterPupilIDLabel\" was not injected: check your FXML file 'SchoolManagerBlockUser.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SchoolManagerBlockUser.fxml'.";
		assert FreeParentButton != null : "fx:id=\"FreeParentButton\" was not injected: check your FXML file 'SchoolManagerBlockUser.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SchoolManagerBlockUser");

		PupilID = "";
		ParentId = "";
		access = "";
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
			new Alert(AlertType.ERROR, "Item has not found.", ButtonType.OK).showAndWait();
			return;
		}

		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);
		if (type.equals("find Pupil"))
		{
			if (arr.size() == 0)
			{
				if (PupilID.length() < 9 || PupilID.length() > 9)
				{
					new Alert(AlertType.ERROR, "Pupil ID must contain 9 digits", ButtonType.OK).showAndWait();
					return;
				}
				else
				{
					new Alert(AlertType.ERROR, "Pupil has not found.", ButtonType.OK).showAndWait();
					return;
				}
			}
			else
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
					ParentId = map.get("parentID");
					access = map.get("ParentAccess");
				}
				if (flagBlock == true)
					updateParentPermission(ParentId, "block");
				else
					updateParentPermission(ParentId, "free");
			}
		}
		else if (type.equals("Free Parent"))
			new Alert(AlertType.INFORMATION, "Parent Free Successfully!", ButtonType.OK).showAndWait();
		else if (type.equals("Block Parent"))
			new Alert(AlertType.INFORMATION, "Parent Blocked Successfully!", ButtonType.OK).showAndWait();
	}
}
