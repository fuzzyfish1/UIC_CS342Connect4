package GUI;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static logic.Globals.temp.primaryStage;

// for start
public class EndScene implements Initializable {

	@FXML
	public TextField port;
	@FXML
	public Button b2;
	@FXML
	Text centerText;
	@FXML
	Parent root;

	public EndScene() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	public void reset(ActionEvent e) {
		try {
			gameStart x = new gameStart();
			x.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void quit(ActionEvent e) {
		System.exit(0);
	}

	public void start() throws IOException {


	}
}
