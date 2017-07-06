package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.UserWindow;

public class DefineSettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PortTextField;

    @FXML
    private TextField IPtxtField;

    @FXML
    private Button backToLogBTN;

    @FXML
    private Label IPlabel;

    @FXML
    private Label portLabel;
    
    @FXML
    private Label connectionLabel;
    

    @FXML
    void EnterIP(ActionEvent event) {

    	
    }

    @FXML
    void EnterPort(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
    	Main.ip=IPtxtField.getText();
    	Main.port=PortTextField.getText();
    	UserWindow.closeUserWindow(getClass(), (Stage) PortTextField.getScene().getWindow());
    }

    @FXML
    void initialize() {
        assert PortTextField != null : "fx:id=\"PortTextField\" was not injected: check your FXML file 'DefineSettings.fxml'.";
        assert IPtxtField != null : "fx:id=\"IPtxtField\" was not injected: check your FXML file 'DefineSettings.fxml'.";
        assert backToLogBTN != null : "fx:id=\"backToLogBTN\" was not injected: check your FXML file 'DefineSettings.fxml'.";
        assert IPlabel != null : "fx:id=\"IPlabel\" was not injected: check your FXML file 'DefineSettings.fxml'.";
        assert portLabel != null : "fx:id=\"portLabel\" was not injected: check your FXML file 'DefineSettings.fxml'.";
        assert connectionLabel != null : "fx:id=\"connectionLabel\" was not injected: check your FXML file 'DefineSettings.fxml'.";
        
        Main.stack.push("DefineSettings");
    	IPtxtField.setText(Main.ip);
    	PortTextField.setText(Main.port);
    }
}
