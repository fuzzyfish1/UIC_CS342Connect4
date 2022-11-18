package GUI.server;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import coms.*;
import game.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import game.clientGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// for start
public class startController implements Initializable {
    static Stage primaryStage = Globals.temp.primaryStage;
    @FXML
    public TextField port;

    @FXML
    public Button b2;

    public startController () {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

	public void serverThreadStart (ActionEvent e) throws IOException {

        if (validatePort()) {
            try {

                Globals.temp.port = Integer.parseInt(port.getText());

                Parent root = FXMLLoader.load(getClass()
                        .getResource("/FXML/server/serverRun.fxml"));

                Scene s1 = new Scene(root, 500, 500);
                s1.getStylesheets().add("/styles/style2.css");

                primaryStage.setTitle("Server Run");
                primaryStage.setScene(s1);

            } catch (Exception g) {
                g.printStackTrace();
            }

        } else {
            port.setText("invalid port: ");
        }
	}

	public boolean validatePort() {
		return Integer.parseInt(port.getText()) > 1024;
	}


}
