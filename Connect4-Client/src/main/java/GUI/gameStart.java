package GUI;
/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import commonCode.status;
import coms.clientComThread;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
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

		if (clientGame.getInstance().numGames > 0 ) {
			clientGame.getInstance().reset();
			clientGame.getInstance().turnNum++;

			centerText.setText("Waiting for enemy to join server");

			clientComThread.getInstance().setCallback(info -> {

				if (info.getStatus() == status.START) {
					changeScene();
				}
			});

			b2.setDisable(true);
		}
	}

	public void attemptConnect(ActionEvent e) {


		if (validatePort()) {

			Globals.temp.port = Integer.parseInt(port.getText());

			try {
				b2.setVisible(false);
				clientComThread.getInstance().init(info -> {

					if (info.getStatus() == status.WAITING) {
						// show some waiting
						clientGame.getInstance().turnNum++;
						centerText.setText("Waiting for enemy to join server");
					} else if (info.getStatus() == status.START) {

						changeScene();
					}
				});

			} catch (Exception f) {
				f.printStackTrace();
			}


		} else {
			b2.setVisible(true);
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

	public void changeScene() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				try {
					GridPane root2 = FXMLLoader.load(getClass()
							.getResource("/FXML/gameScene.fxml"));

					root2.getStylesheets().add("/styles/style2.css");//set style

					Scene s1 = new Scene(root2, 500, 500);

					s1.getStylesheets().add("/styles/style2.css");

					primaryStage.setTitle("Connect 4");
					primaryStage.setScene(s1);
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});


//			System.out.println("asdklfa;sdlkfj;asdjkf");
//			Parent pane = FXMLLoader.load(getClass()
//					.getResource("/FXML/gameScene.fxml"));
//
//			System.out.println("asd;fklja;sdklfja;sdfjkl");
//
//			Scene s1 = new Scene(pane, 500, 500);
//
//			s1.getStylesheets().add("/styles/style2.css");
//
//			primaryStage.setTitle("Connect 4");
//			primaryStage.setScene(s1);

	}
}
