package GUI.game;
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
import game.Globals;
import game.clientGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

// for start
public class startController implements Initializable {
    static Stage primaryStage = Globals.temp.primaryStage;
    @FXML
    public TextField port;

    @FXML
    public Button b2;

    public startController() {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

	public void gameThreadStart (ActionEvent e) throws IOException {

        if (validatePort()) {

            Globals.temp.port = Integer.parseInt(port.getText());

            Parent root = FXMLLoader.load(getClass()
                    .getResource("/FXML/client/clientRun.fxml"));

            Scene s1 = new Scene(root, 500, 500);
            s1.getStylesheets().add("/styles/style2.css");

            primaryStage.setTitle("gamer Run");
            primaryStage.setScene(s1);

        } else {
            port.setText("invalid port: ");
        }
	}

	public boolean validatePort() {
		return Integer.parseInt(port.getText()) > 1024;
	}


}
