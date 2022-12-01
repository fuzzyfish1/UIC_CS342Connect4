package GUI;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import lib.status;
import coms.clientComThread;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import logic.Globals;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import logic.clientGame;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static logic.Globals.temp.primaryStage;

public class gameScene implements Initializable {

	@FXML
	GridPane pane;

	@FXML
	Text moveIndicator;

	@FXML
	Text errorBox;

	public gameScene() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		pane.setPadding(new Insets(20));
		pane.setHgap(10);
		pane.setVgap(10);

		try {
			clientComThread.getInstance().setCallback(e -> {
				clientGame.getInstance().enemyMove(e);
				updateScreen();
			});
		} catch (Exception e){
			e.printStackTrace();
		}

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


		updateScreen();
	}


	public void updateScreen() {
		ObservableList<Node> childrens = pane.getChildren();
		int[][] board = clientGame.getInstance().board;

		if (clientGame.getInstance().currStatus != status.RUNNING) {
			for (Node child : childrens) {
				if (child instanceof GameButton) {
					((GameButton) child).setOnAction( e -> {
						System.out.println(" button pushy");
					});
				}
			}


			delay( 3000, () -> {
				try {
					BorderPane root = FXMLLoader.load(getClass()
							.getResource("/FXML/endScene.fxml"));

					Scene s1 = new Scene(root, 500, 500);

					s1.getStylesheets().add("/styles/style2.css");

					primaryStage.setTitle(clientGame.getInstance().errorMsg);
					primaryStage.setScene(s1);
					primaryStage.show();

				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}

		for (Node child : childrens) {

			if (child instanceof GameButton) {
				GameButton i = (GameButton) child;
				i.setValue(board[i.c][i.r]);
			}
		}

		moveIndicator.setText(
				clientGame.getInstance().turnNum % 2 == 0 ?
						"Waiting for Enemy Move" : "It is now your Turn"
		);

		errorBox.setText(clientGame.getInstance().errorMsg);

	}

	public static void delay(long millis, Runnable continuation) {
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() {
				try { Thread.sleep(millis); }
				catch (InterruptedException e) { }
				return null;
			}
		};
		sleeper.setOnSucceeded(event -> continuation.run());
		new Thread(sleeper).start();
	}
}