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
import coms.game.clientComThread;
import logic.Globals;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import logic.game.clientGame;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class gameSceneController implements Initializable {

    @FXML
    GridPane pane;

    public gameSceneController() {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Consumer<CFourInfo> walmart = f -> {

            try {
                clientGame.getInstance().enemyMove(f);
                this.updateScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };

        try {
            clientComThread.getInstance().init(Globals.temp.port, walmart);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        for (int r = 0; r < Globals.constants.height; r++) {
            for (int c = 0; c < Globals.constants.width; c++) {

                GameButton button = new GameButton(Globals.constants.height - r - 1, c);

                button.setOnAction(e -> {
                    try {
                        clientGame.getInstance().makeMove(button.c);
                        this.updateScreen();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                pane.add(button, c, r);
            }
        }

        clientComThread.getInstance().start();

        updateScreen();
    }


    public void updateScreen() {
        ObservableList<Node> childrens = pane.getChildren();
        int[][] board = clientGame.getInstance().board;

        for (Node child : childrens) {

            GameButton i = (GameButton) child;
            i.setValue(board[i.c][i.r]);

        }
    }
}