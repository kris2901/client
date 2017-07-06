package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import application.Main;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class ReportBetweenClassesController - this class present grade report between the classes.
 */
public class ReportBetweenClassesController implements IController
{
	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Display button. */
	@FXML
	private Button DisplayButton;

	/** The Report bar chart. */
	@FXML
	private BarChart<String, Double> ReportBarChart;

	/** The Semester label. */
	@FXML
	private Label SemesterLabel;

	/** The Specific teacher category axis. */
	@FXML
	private CategoryAxis SpecificTeacherAxis;

	/** The Check semester button. */
	@FXML
	private Button CheckSemesterButton;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Choose combo box. */
	@FXML
	private ComboBox<String> ChooseComboBox;

	/** The Report label no.1. */
	@FXML
	private Label ReportLabel1;

	/** The Choose label. */
	@FXML
	private Label ChooseLabel;

	/** The Different classes number axis. */
	@FXML
	private NumberAxis DifferentClassesAxis;

	/** The Semester text field. */
	@FXML
	private TextField SemesterTextField;

	/** The Semester ID. */
	private ArrayList<String> SemesterID;

	/** The Teacher. */
	private String Teacher;

	/** The Selected teacher. */
	private String SelectedTeacher;

	/** The Semester FLAG. */
	private int SemesterFLAG;

	/** The Teacher FLAG. */
	private int TeacherFLAG;

	/**
	 * Back to menu.
	 *
	 * @param event - enter back to menu
	 */
	@FXML
	void BackToMenu(ActionEvent event)
	{
		UserWindow.closeUserWindow(getClass(), (Stage) ReportLabel1.getScene().getWindow());
	}

	/**
	 * Choose teacher.
	 *
	 * @param event - Choose teacher
	 */
	@FXML
	void ChooseTeacher(ActionEvent event)
	{
		Teacher = ChooseComboBox.getSelectionModel().getSelectedItem();
		if (Teacher == null)
		{
			new Alert(AlertType.ERROR, "Item Not Found.", ButtonType.OK).showAndWait();
			return;
		}
	}

	/**
	 * Load teachers.
	 */
	void loadTeachers()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Teacher List");
		data.add("select");
		data.add("user");
		data.add("permission");
		data.add("4");
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
	 * Check semester ID.
	 *
	 * @param event - Check semester ID.
	 */
	@FXML
	void CheckSemesterID(ActionEvent event)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("Check Semester ID");
		data.add("select field");
		data.add("semesterId");
		data.add("semester");
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
	 * Display report.
	 *
	 * @param event - Display report
	 */
	@FXML
	void DisplayReport(ActionEvent event)
	{
		if (TeacherFLAG == 1 && SemesterFLAG == 1)
		{
			SelectedTeacher = Teacher.substring(0, 9);
			if (SelectedTeacher == null)
				return;

			ReportBarChart.getData().clear();
			ArrayList<String> data = new ArrayList<String>();
			data.add("histogram 1"); // for answer
			data.add("histogram 1"); // for sql type
			data.add(SelectedTeacher);
			data.add(SemesterID.get(0));
			data.add(SemesterID.get(1));
			data.add(SemesterID.get(2));
			data.add(SemesterID.get(3));

			try
			{
				Main.client.sendToServer(data);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			if (TeacherFLAG == 0 && SemesterFLAG == 0)
			{
				new Alert(AlertType.ERROR, "You must choose teacher and 4 semesters ID first!!", ButtonType.OK)
						.showAndWait();
			}
			if (TeacherFLAG == 0)
			{
				new Alert(AlertType.ERROR, "You must choose teacher first!!", ButtonType.OK).showAndWait();
			}
			if (SemesterFLAG == 0)
			{
				new Alert(AlertType.ERROR, "You must enter 4 semesters ID first!!", ButtonType.OK).showAndWait();
			}
		}
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert DisplayButton != null : "fx:id=\"DisplayButton\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert ReportBarChart != null : "fx:id=\"ReportBarChart\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert SemesterLabel != null : "fx:id=\"SemesterLabel\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert SpecificTeacherAxis != null : "fx:id=\"SpecificTeacherAxis\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert CheckSemesterButton != null : "fx:id=\"CheckSemesterButton\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert ChooseComboBox != null : "fx:id=\"ChooseComboBox\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert ReportLabel1 != null : "fx:id=\"ReportLabel1\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert ChooseLabel != null : "fx:id=\"ChooseLabel\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert DifferentClassesAxis != null : "fx:id=\"DifferentClassesAxis\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";
		assert SemesterTextField != null : "fx:id=\"SemesterTextField\" was not injected: check your FXML file 'ReportBetweenClassesOfSpecificTeacher.fxml'.";

		Main.client.controller = this;
		Main.stack.push("ReportBetweenClassesOfSpecificTeacher");

		SemesterID = new ArrayList<String>();
		Teacher = "";
		SelectedTeacher = "";
		TeacherFLAG = 0;
		SemesterFLAG = 0;

		ReportBarChart.getXAxis().setLabel("Different Classes");
		ReportBarChart.getXAxis().setMaxWidth(0.3);
		ReportBarChart.getYAxis().setLabel("Average Grade");
		ReportBarChart.setTitle("");
		ReportBarChart.setCategoryGap(22.0);

		loadTeachers();

	}

	/**
	 * Handles the answer from the server according to the type of answer.
	 */
	@Override
	public void handleAnswer(Object result)
	{
		if (result == null)
		{
			new Alert(AlertType.ERROR, "Item Not Found.", ButtonType.OK).showAndWait();

			return;
		}

		ArrayList<String> arr = (ArrayList<String>) result;
		String type = arr.remove(0);

		if (type.equals("histogram 1"))
		{
			XYChart.Series<String, Double> series = new XYChart.Series<>();

			for (String row : arr)
			{
				ArrayList<String> values = new ArrayList<>();
				String[] cols = row.split(";");

				for (String col : cols)
				{
					String[] field = col.split("=");
					values.add(field[1]);
				}
				series.getData().add(new XYChart.Data<>(values.get(0), Double.parseDouble(values.get(1))));
			}

			ReportBarChart.getData().add(series);

		}
		if (type.equals("Teacher List"))
		{
			for (String row : arr)
			{
				String[] cols = row.split(";");
				HashMap<String, String> map = new HashMap<>();
				ArrayList<String> values = new ArrayList<>();
				for (String col : cols)
				{
					String[] field = col.split("=");
					map.put(field[0], field[1]);
				}
				String TeacherID = map.get("userId");
				String TeacherNAME = map.get("userFirstName");
				ChooseComboBox.getItems().add(TeacherID + " : " + TeacherNAME);
			}
			TeacherFLAG = 1;
		}

		if (type.equals("Check Semester ID"))
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
				SemesterID.add(map.get("semesterId"));
			}

			int i, j;
			String str = SemesterTextField.getText();
			String[] semesterID = str.split(" ");
			int SemesterFlag = 0;
			int charFLAG = 0;
			for (i = 0; i < semesterID.length; i++)
			{
				String semID = semesterID[i];
				if ((semID.charAt(i) >= 'a' && semID.charAt(i) <= 'z')
						|| (semID.charAt(i) >= 'A' && semID.charAt(i) <= 'Z'))
				{
					new Alert(AlertType.ERROR, "You can only enter digits for semester ID.", ButtonType.OK)
							.showAndWait();
					charFLAG = 1;
					break;

				}
			}
			if (charFLAG == 0)
			{
				if (semesterID.length > 4 || semesterID.length < 4)
				{
					new Alert(AlertType.ERROR, "You have to enter 4 semesters ID for this report", ButtonType.OK)
							.showAndWait();
				}

				else
				{
					for (i = 0; i < semesterID.length; i++)
					{
						if (!SemesterID.contains(semesterID[i]))
						{
							SemesterFlag = 0;
							new Alert(AlertType.ERROR, "Semester ID " + semesterID[i] + " does not exist.",
									ButtonType.OK).showAndWait();
							break;
						}
						else
						{
							SemesterFlag++;
						}
					}
				}
			}
			if (SemesterFlag == semesterID.length)
			{
				SemesterFlag = 1;
				new Alert(AlertType.INFORMATION, "All semester ID are correct.", ButtonType.OK).showAndWait();
				SemesterID.clear();
				for (i = 0; i < semesterID.length; i++)
				{
					SemesterID.add(semesterID[i]);
				}
				SemesterFLAG = 1;
			}
		}
	}
}
