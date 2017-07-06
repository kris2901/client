package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import application.UserController;
import interfaces.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ui.UserWindow;


/**
 * The Class PupilShowEvaluationFormController - this class show evaluation form of pupil.
 */
public class PupilShowEvaluationFormController implements IController {

    /** The resources. */
    @FXML
    private ResourceBundle resources;

    /** The location. */
    @FXML
    private URL location;

    /** The Final grade text field. */
    @FXML
    private TextField FinalGradeTextField;

    /** The Comments label. */
    @FXML
    private Label CommentsLabel;

    /** The Form menu label. */
    @FXML
    private Label FormMenuLabel;

    /** The Comments text area. */
    @FXML
    private TextArea CommentsTextArea;

    /** The Back button. */
    @FXML
    private Button BackButton;

    /** The Final grade label. */
    @FXML
    private Label FinalGradeLabel;
    
    /** The course id. */
    private String courseId;
    
    /** The grade. */
    private String grade;
    
    /** The comments. */
    private String comments;
    
    /** The flag evaluation. */
    private boolean flagEvaluation;
    
    /** The User ID. */
    private String  UserID;
    
    /** The Pupil file controller. */
    private PupilFileController PupilFileController = new PupilFileController();

    /**
     * Pupil final grade.
     *
     * @param event - Pupil final grade 
     */
    @FXML
    void PupilFinalGrade(ActionEvent event) {

    }

    /**
     * Back to menu.
     *
     * @param event - enter back to menu.
     */
    @FXML
    void BackToMenu(ActionEvent event) {
        UserWindow.closeUserWindow(getClass(), (Stage)BackButton.getScene().getWindow());
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert FinalGradeTextField != null : "fx:id=\"FinalGradeTextField\" was not injected: check your FXML file 'PupilEvaluationForm.fxml'.";
        assert CommentsLabel != null : "fx:id=\"CommentsLabel\" was not injected: check your FXML file 'PupilEvaluationForm.fxml'.";
        assert FormMenuLabel != null : "fx:id=\"FormMenuLabel\" was not injected: check your FXML file 'PupilEvaluationForm.fxml'.";
        assert CommentsTextArea != null : "fx:id=\"CommentsTextArea\" was not injected: check your FXML file 'PupilEvaluationForm.fxml'.";
        assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'PupilEvaluationForm.fxml'.";
        assert FinalGradeLabel != null : "fx:id=\"FinalGradeLabel\" was not injected: check your FXML file 'PupilEvaluationForm.fxml'.";

        UserID=UserController.CurrentUserID;
        Main.stack.push("PupilEvaluationForm");
        Main.client.controller=this;
    }
    
    /**
     * Load evaluation form.
     *
     * @param course - enter course id
     * @param id - enter pupil id
     */
    void loadEvaluationForm(String course,String ID)
    {
    	ArrayList<String> data = new ArrayList<String>();
    	data.add("load evaluation form");
    	data.add("select");
    	data.add("evaluation_form");
    	data.add("courseID");
    	data.add(course);
    	data.add("pupilID");
    	data.add(ID);
    	
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
	 	if (type.equals("load evaluation form"))
	 	{
	 		if(arr.size()==0){
	 			flagEvaluation=false;
	 	    	Alert a = new Alert(AlertType.ERROR, "No Evaluation Form!", ButtonType.OK);
	 	    	Optional<ButtonType> res = a.showAndWait();
	 	    	if (res.get() == ButtonType.OK){
	 	           UserWindow.closeUserWindow(getClass(), (Stage)BackButton.getScene().getWindow());
	 	    	} 	    	
		   		return;
	 		}
	 		for (String row : arr)
	 		{	
	 			String[] cols = row.split(";");
	 			HashMap<String, String> map = new HashMap<>();
	 			for (String col : cols)
	 			{
	 				String[] field = col.split("=");
	 				map.put(field[0], field[1]);
	 			}
	 			grade = map.get("finalGrade");
	 			comments = map.get("generalComments");
	 		}
	 		FinalGradeTextField.setText(grade);
	 		CommentsTextArea.setText(comments);
	 		flagEvaluation=true;
	 	}	 	
	}
}
