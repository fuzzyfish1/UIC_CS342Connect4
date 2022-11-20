package GUI;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import coms.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import game.Globals;
import game.clientGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// for start
public class MyController implements Initializable {
	Stage primaryStage = Globals.temp.primaryStage;

	@FXML
	public Button serverButton;
	@FXML
	public Button clientButton;

	public MyController () {
		super();
	}

	public void start() throws IOException {
		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/start.fxml"));

		primaryStage.setTitle("connect 4 Start");

		Scene s1 = new Scene(root, 500, 500);
		s1.getStylesheets().add("/styles/style1.css");

		primaryStage.setScene(s1);
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	//method so that the controller created for second view can update the text
	//field with the text from the first vie
	public void serverStart(ActionEvent e) throws IOException {

		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/server/serverStart.fxml"));

		Scene serverScene = new Scene(root, 500, 500);
		serverScene.getStylesheets().add("/styles/style2.css");

		primaryStage.setTitle("Server Application");
		primaryStage.setScene(serverScene);
		primaryStage.show();

	}

	public void gamerStart(ActionEvent e) throws IOException {

		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/client/gamerStart.fxml"));

		Scene gamerScene = new Scene(root, 500, 500);

		gamerScene.getStylesheets().add("/styles/style2.css");
		primaryStage.setTitle("gamer");

		primaryStage.setTitle("Gamy boi");
		primaryStage.setScene(gamerScene);
		primaryStage.show();
	}
}
