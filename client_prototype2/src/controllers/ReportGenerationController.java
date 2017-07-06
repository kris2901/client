package controllers;

import java.net.URL;
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
 * The Class ReportGenerationController - this class produce report generation.
 */
public class ReportGenerationController implements IController
{

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The Report menu button. */
	@FXML
	private MenuButton ReportMenuButton;

	/** The Back button. */
	@FXML
	private Button BackButton;

	/** The Between courses. */
	@FXML
	private MenuItem BetweenCourses;

	/** The Between teachers. */
	@FXML
	private MenuItem BetweenTeachers;

	/** The Report window label. */
	@FXML
	private Label ReportWindowLabel;

	/** The Between classes. */
	@FXML
	private MenuItem BetweenClasses;

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
	 * Choose report.
	 *
	 * @param event - Choose report
	 */
	@FXML
	void ChooseReport(ActionEvent event)
	{

	}

	/**
	 * Classes of teacher.
	 *
	 * @param event - Classes of teacher
	 */
	@FXML
	void ClassesOfTeacher(ActionEvent event)
	{
		//Main.stack.push("SchoolManagerReportGeneration");
		UserWindow.createUserWindow((Stage) ReportWindowLabel.getScene().getWindow(),
				"ReportBetweenClassesOfSpecificTeacher", getClass());
	}

	/**
	 * Courses in class.
	 *
	 * @param event - Courses in class
	 */
	@FXML
	void CoursesInClass(ActionEvent event)
	{
		//Main.stack.push("SchoolManagerReportGeneration");
		UserWindow.createUserWindow((Stage) ReportWindowLabel.getScene().getWindow(), "ReportBetweenDiferentCoursesOfSpecificClass", getClass());
	}

	/**
	 * Teachers in class.
	 *
	 * @param event - Teachers in class
	 */
	@FXML
	void TeachersInClass(ActionEvent event)
	{
		//Main.stack.push("SchoolManagerReportGeneration");
		UserWindow.createUserWindow((Stage) ReportWindowLabel.getScene().getWindow(),
				"ReportBetweenTeachersOfSpecificClass", getClass());
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize()
	{
		assert ReportMenuButton != null : "fx:id=\"ReportMenuButton\" was not injected: check your FXML file 'SchoolManagerReportGeneration.fxml'.";
		assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'SchoolManagerReportGeneration.fxml'.";
		assert BetweenCourses != null : "fx:id=\"BetweenCourses\" was not injected: check your FXML file 'SchoolManagerReportGeneration.fxml'.";
		assert BetweenTeachers != null : "fx:id=\"BetweenTeachers\" was not injected: check your FXML file 'SchoolManagerReportGeneration.fxml'.";
		assert ReportWindowLabel != null : "fx:id=\"ReportWindowLabel\" was not injected: check your FXML file 'SchoolManagerReportGeneration.fxml'.";
		assert BetweenClasses != null : "fx:id=\"BetweenClasses\" was not injected: check your FXML file 'SchoolManagerReportGeneration.fxml'.";

		Main.client.controller = this;
		Main.stack.push("SchoolManagerReportGeneration");
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
