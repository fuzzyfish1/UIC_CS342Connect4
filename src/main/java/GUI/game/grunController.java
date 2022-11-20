package GUI.game;
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

public class grunController implements Initializable {
    @FXML
    public ScrollPane scrolly;

    public grunController () {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
