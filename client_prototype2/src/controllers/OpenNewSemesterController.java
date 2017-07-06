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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class OpenNewSemesterController - this class is open new semester.
 */
public class OpenNewSemesterController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Open semester label. */
	@FXML
	private Label OpenSemesterLable;

	/** The Status label. */
	@FXML
	private Label StatusLable;

	/** The Check ID button. */
	@FXML
	private Button CheckIDButton;

	/** The Academic activity text field. */
	@FXML
	private TextField AcademicAcTextField;

	/** The Semester ID label. */
	@FXML
	private Label SemesterIDLable;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Current semester menu. */
	@FXML
	private MenuItem CurrentSEMMenu;

	/** The Academic activity ID label. */
	@FXML
	private Label AcademicAcIDLable;

	/** The Semester no.B. */
	@FXML
	private MenuItem SemesterB;

	/** The Status menu. */
	@FXML
	private MenuButton StatusMenu;

	/** The Check activities button. */
	@FXML
	private Button CheckActivitiesButton;

	/** The Semester no.A. */
	@FXML
	private MenuItem SemesterA;

	/** The Fill form label. */
	@FXML
	private Label FillFormLable;

	/** The Send button. */
	@FXML
	private Button SendButton;

	/** The Semester menu. */
	@FXML
	private MenuButton SemesterMenu;

	/** The Type label. */
	@FXML
	private Label TypeLable;

	/** The Semester id text field. */
	@FXML
	private TextField SemesterIdTextField;

	/** The Activity ID. */
	private ArrayList<String> ActivityID;

	/** The Semester ID. */
	private ArrayList<String> SemID;

	/** The Type. */
	private String Type;

	/** The Status. */
	private String Status;

	/** The Semester ID. */
	private String SemesterID;

	/** The Academic activity ID. */
	private String AcademicActivityID;

	/** The Id flag. */
	private int IdFlag;

	/** The Type flag. */
	private int TypeFlag;

	/** The Status flag. */
	private int StatusFlag;

	/** The Activity flag. */
	private int ActivityFlag;

	/**
	 * Enter semester ID.
	 *
	 * @param event - enter semester ID
	 */
	@FXML
	void EnterSemesterID(ActionEvent event)
	{

	}

	/**
	 * Enter academic activity ID.
	 *
	 * @param event - enter academic activity ID
	 */
	@FXML
	void EnterAcademicAcID(ActionEvent event)
	{

	}

	/**
	 * Send semester form.
	 *
	 * @param event - Send semester form.
	 */
	@FXML
	void SendSemesterForm(ActionEvent event)
	{
		if (StatusFlag == 0)
		{
			new Alert(AlertType.ERROR, "Please choose semester status.", ButtonType.OK).showAndWait();
			return;
		}
		if (StatusFlag == 1)
		{
			ArrayList<String> data = new ArrayList<String>();
			data.add("Update Status and Permission");
			data.add("update");
			data.add("semester");
			data.add("semesterStatus");
			data.add("Previous");
			data.add("semesterPermission");
			data.add("Read Only");
			data.add("conditions");
			data.add("semesterStatus");
			data.add("Current");
			data.add("semesterPermission");
			data.add("Read and Write");
			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (IdFlag == 1 && TypeFlag == 1 && StatusFlag == 1)
		{
			ArrayList<String> data = new ArrayList<String>();
			data.add("Insert To Semester");
			data.add("insert");
			data.add("semester");
			data.add("semesterId");
			data.add("semesterType");
			data.add("semesterStatus");
			data.add("semesterPermission");
			data.add("values");
			data.add(SemesterID);
			data.add(Type);
			data.add(Status);
			data.add("Read and Write");
			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (IdFlag == 0)
		{
			new Alert(AlertType.ERROR, "Please enter semester ID.", ButtonType.OK).showAndWait();
			return;
		}
		if (TypeFlag == 0)
		{
			new Alert(AlertType.ERROR, "Please choose semester type.", ButtonType.OK).showAndWait();
			return;
		}

	}

	/**
	 * Load academic activities.
	 *
	 * @param ID - enter academic activities id
	 */
	void loadAcaemicActivities(String ID)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Insert Activities");
		data.add("insert");
		data.add("activity_in_semester");
		data.add("ActivityID");
		data.add("SemesterID");
		data.add("values");
		data.add(ID);
		data.add(SemesterID);

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
	 * Back to menu.
	 *
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) BackButton.getScene().getWindow());
	}

	/**
	 * Semester no.A.
	 *
	 * @param event - enter semester A
	 */
	@FXML
	void SemA(ActionEvent event)
	{
		SemesterMenu.setText(SemesterA.getText());
		Type = "A";
		TypeFlag = 1;
		new Alert(AlertType.INFORMATION, "Type that was chosen for this semester is: A.", ButtonType.OK).showAndWait();
	}

	/**
	 * Semester no.B.
	 *
	 * @param event - enter semester A
	 */
	@FXML
	void SemB(ActionEvent event)
	{
		SemesterMenu.setText(SemesterB.getText());
		Type = "B";
		TypeFlag = 1;
		new Alert(AlertType.INFORMATION, "Type that was chosen for this semester is: B.", ButtonType.OK).showAndWait();
	}

	/**
	 * Current Semester.
	 *
	 * @param event - enter Current Semester
	 */
	@FXML
	void CurrentSEM(ActionEvent event)
	{
		StatusMenu.setText(CurrentSEMMenu.getText());
		Status = "Current";
		StatusFlag = 1;
		new Alert(AlertType.INFORMATION, "Status that was chosen for this semester is: Current.", ButtonType.OK)
				.showAndWait();
	}

	/**
	 * Check semester ID.
	 *
	 * @param event - enter semester ID
	 */
	@FXML
	void CheckSemesterID(ActionEvent event)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Semester ID");
		data.add("select");
		data.add("semester");
		data.add("semesterId");
		SemesterID = SemesterIdTextField.getText();
		data.add(SemesterID);
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
	 * Check academic activities.
	 *
	 * @param event - enter academic activities
	 */
	@FXML
	void CheckAcademicActivities(ActionEvent event)
	{

		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Activity ID");
		data.add("select field");
		data.add("academicActivityId");
		data.add("academic_activity");
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
		assert OpenSemesterLable != null : "fx:id=\"OpenSemesterLable\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert StatusLable != null : "fx:id=\"StatusLable\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert CheckIDButton != null : "fx:id=\"CheckIDButton\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert AcademicAcTextField != null : "fx:id=\"AcademicAcTextField\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert SemesterIDLable != null : "fx:id=\"SemesterIDLable\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert CurrentSEMMenu != null : "fx:id=\"CurrentSEMMenu\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert AcademicAcIDLable != null : "fx:id=\"AcademicAcIDLable\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert SemesterB != null : "fx:id=\"SemesterB\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert CheckActivitiesButton != null : "fx:id=\"CheckActivitiesButton\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert SemesterA != null : "fx:id=\"SemesterA\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert FillFormLable != null : "fx:id=\"FillFormLable\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert SendButton != null : "fx:id=\"SendButton\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert TypeLable != null : "fx:id=\"TypeLable\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";
		assert SemesterIdTextField != null : "fx:id=\"SemesterIdTextField\" was not injected: check your FXML file 'SecretaryOpenSemester.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SecretaryOpenSemester");

		ActivityID = new ArrayList<String>();
		SemID = new ArrayList<String>();

		Type = "";
		Status = "";
		SemesterID = "";
		AcademicActivityID = "";
		IdFlag = 0;
		TypeFlag = 0;
		StatusFlag = 0;
		ActivityFlag = 0;
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

		if (type.equals("Check Semester ID"))
		{
			int i;
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				SemID.add(map.get("semesterId"));
			}
			if (!SemID.contains(SemesterID))
			{
				if (SemesterID.length() == 6)
				{
					IdFlag = 1;
					for (i = 0; i < 6; i++)
					{
						if ((SemesterID.charAt(i) >= 'a' && SemesterID.charAt(i) <= 'z')
								|| (SemesterID.charAt(i) >= 'A' && SemesterID.charAt(i) <= 'Z'))
						{
							IdFlag = 0;
							new Alert(AlertType.ERROR, "Semester ID must contain digits only", ButtonType.OK)
									.showAndWait();
							break;
						}
					}
					if (IdFlag == 1)
					{
						new Alert(AlertType.INFORMATION, "Semester ID is correct and available.", ButtonType.OK)
								.showAndWait();
					}
				}
				else
				{
					new Alert(AlertType.ERROR, "Semester ID should have 6 digits.", ButtonType.OK).showAndWait();
				}
			}
			else
			{
				new Alert(AlertType.ERROR, "This semester ID already exists", ButtonType.OK).showAndWait();
			}
		}
		if (type.equals("Check Activity ID"))
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
				ActivityID.add(map.get("academicActivityId"));
			}
			int i, j;
			String str = AcademicAcTextField.getText();
			String[] activityID = str.split(" ");
			int ActivityFlag = 0;
			for (i = 0; i < activityID.length; i++)
			{
				if (!ActivityID.contains(activityID[i]))
				{
					ActivityFlag = 0;
					new Alert(AlertType.ERROR, "Academic Activity ID " + activityID[i] + " does not exist.",
							ButtonType.OK).showAndWait();
					break;
				}
				else
				{
					ActivityFlag++;
				}
			}

			if (ActivityFlag == activityID.length)
			{
				ActivityFlag = 1;
				new Alert(AlertType.INFORMATION, "All academic activities ID are correct.", ButtonType.OK)
						.showAndWait();
				ActivityID.clear();
				for (i = 0; i < activityID.length; i++)
				{
					ActivityID.add(activityID[i]);
				}
			}
		}
		if (type.equals("Insert To Semester"))
		{
			if (arr.size() > 0)
			{
				int i;
				for (i = 0; i < ActivityID.size(); i++)
				{
					loadAcaemicActivities(ActivityID.get(i));
				}
				new Alert(AlertType.INFORMATION, "Semester opened successfuly.", ButtonType.OK).showAndWait();

				Type = "";
				Status = "";
				SemesterID = "";
				AcademicActivityID = "";
				IdFlag = 0;
				TypeFlag = 0;
				StatusFlag = 0;
				ActivityFlag = 0;
			}
		}

	}
}
