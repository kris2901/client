package application;

import java.util.Stack;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.UserWindow;

/**
 * The Class Main extends Application - this class is the main of client 
 */
public class Main extends Application {
	public static Stack<String> stack;
	public static SchoolClient client;
	public static Object paramater;
	public static String userId = null;
	public static String ip = "127.0.0.1";
	public static String port = "5555";

    /**
     * start the school client and login to the system
     */   
	@Override
	public void start(Stage primaryStage) throws Exception {
		stack = new Stack<>();
		stack.push("loginFrame");

		UserWindow.createUserWindow(primaryStage, "loginFrame", getClass());
			
	}


    /**
     * main
     * @param args - argument
     */   
	public static void main(String[] args)
	{
		launch(args);
	}
}
