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


	public void runScene() {


		try {
			Parent root = FXMLLoader.load(gameStart.class.getResource("/FXML/gameScene.fxml"));

			Scene s2 = new Scene(root, 500, 500);
			s2.getStylesheets().add("/styles/style2.css");

			logic.Globals.temp.primaryStage.setScene(s2);
			logic.Globals.temp.primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}



	}


	public void attemptConnect(ActionEvent e) throws IOException {

		if (validatePort()) {

			Globals.temp.port = Integer.parseInt(port.getText());

			b2.setDisable(true);
			port.setDisable(true);

			b2.setVisible(false);
			port.setDisable(false);

			centerText.setText(" LOADING ");

			Consumer<CFourInfo> walmart = f -> {
				Platform.runLater(() -> {

					try {

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameScene.fxml"));
						Parent pane = loader.load(); //load view into parent
						gameScene myctr = loader.getController();//get controller created by FXMLLoader

						//myctr.setText();//use MyLoader class method for setText()
						root.getScene().setRoot(pane);

						//logic.Globals.temp.primaryStage.setScene(s2);
						//logic.Globals.temp.primaryStage.show();

						if (f.getStatus() == 4) {
							clientGame.getInstance().turnNum = 1;
						}
					} catch (Exception g) {
						g.printStackTrace();
					}
				});
			};

			try {

				clientComThread.getInstance().init(walmart);
				clientComThread.getInstance().start();

			} catch (Exception f) {
				f.printStackTrace();

				b2.setDisable(false);
				port.setDisable(false);

				b2.setVisible(true);
				port.setDisable(true);

				centerText.setText("try again");
			}

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
