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
import game.Globals;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;


// for start
public class runController implements Initializable {
    //static Stage primaryStage = null;
    @FXML
    public ScrollPane scrolly;

    public runController () {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Text t = new Text();
        scrolly.setContent(t);

        Consumer<servGame> addToScrollable = f -> {
            t.setText(t.getText() + "P1: " + f.getP1().getInetAddress() + ":" + f.getP1().getLocalPort());
            t.setText(t.getText() + "P2: " + f.getP2().getInetAddress() + ":" + f.getP2().getLocalPort());
        };

        servThread.getInstance().init(Globals.temp.port, addToScrollable);
        servThread.getInstance().start();

    }
    //method so that the controller created for second view can update the text
    //field with the text from the first vie

}
