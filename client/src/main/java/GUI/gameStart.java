package GUI;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import coms.CFourInfo;
import coms.clientComThread;
import javafx.application.Platform;
import javafx.scene.text.Text;
import logic.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.clientGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static logic.Globals.temp.primaryStage;

// for start
public class gameStart implements Initializable {

	@FXML
	public TextField port;
	@FXML
	public Button b2;
	@FXML
	Text centerText;
	Parent root;

	public gameStart() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void attemptConnect(ActionEvent e) throws IOException {

		if (validatePort()) {

			Globals.temp.port = Integer.parseInt(port.getText());

			root = FXMLLoader.load(getClass()
					.getResource("/FXML/gameScene.fxml"));

			Scene s1 = new Scene(root, 500, 500);

			s1.getStylesheets().add("/styles/style2.css");

			primaryStage.setTitle("Connect 4");
			primaryStage.setScene(s1);
			primaryStage.show();

		} else {
			port.setText("invalid port: ");
		}
	}

	public boolean validatePort() {
		return Integer.parseInt(port.getText()) > 1024;
	}

	public void start() throws IOException {

		root = FXMLLoader.load(getClass()
				.getResource("/FXML/gameStart.fxml"));

		Scene s1 = new Scene(root, 500, 500);

		s1.getStylesheets().add("/styles/style2.css");

		primaryStage.setTitle("Connect 4");
		primaryStage.setScene(s1);
		primaryStage.show();

	}
}
