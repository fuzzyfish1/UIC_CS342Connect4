package serverGUI;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import javafx.application.Platform;
import serverLogic.Globals;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import serverLogic.servThread;

public class runController implements Initializable {

    @FXML
    public ListView scrolly;

    public runController () {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Consumer<String> addToScrollable = f -> {

            Platform.runLater(() -> {
                Text t = new Text();

                t.setText(f);
                scrolly.getItems().add(t);
            });
        };

        Globals.temp.addString = addToScrollable;

        servThread.getInstance().init(Globals.temp.port);

    }

}
