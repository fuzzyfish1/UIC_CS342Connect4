/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  My Controller controls GUI and
 *  Initializes the server and any communications
 * */

import coms.*;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MyController implements Initializable {
	static Stage primaryStage = null;
	@FXML
	public ScrollPane veiw;

	//static so each instance of controller can access to update
	Scene serverScene;
	Scene gamerScene;
	@FXML
	private TextField port;


	// TODO: get rid of vv

	public MyController () {
		super();
	}

	public static void setStage(Stage primaryStage) {
		MyController.primaryStage = primaryStage;
	}

	public void start() throws IOException {
		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/start.fxml"));

		primaryStage.setTitle("connect 4 Start");
		Scene s1 = new Scene(root, 500, 500);
		s1.getStylesheets().add("/styles/style1.css");

		primaryStage.setScene(s1);

		primaryStage.show();
	}

	public void initGamerScene() throws IOException {

		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/client/gamerStart.fxml"));

		gamerScene = new Scene(root, 500, 500);

		gamerScene.getStylesheets().add("/styles/style2.css");
		primaryStage.setTitle("gamer");
	}

	public void initServerScene() throws IOException {
		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/server/serverStart.fxml"));

		serverScene = new Scene(root, 500, 500);
		serverScene.getStylesheets().add("/styles/style2.css");

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	//method so that the controller created for second view can update the text
	//field with the text from the first vie

	public void serverThreadStart(ActionEvent e) throws IOException {
		if (validatePort()) {

			Parent root = FXMLLoader.load(getClass()
					.getResource("/FXML/server/serverRun.fxml"));

			serverScene = new Scene(root, 500, 500);
			serverScene.getStylesheets().add("/styles/style2.css");


			primaryStage.setTitle("Server Run");
			primaryStage.setScene(serverScene);
			primaryStage.show();

			servThread.getInstance().init(Integer.parseInt(port.getText()));
			servThread.getInstance().start();

		} else {
			port.setText("invalid port: ");
		}
	}

	public boolean validatePort() {
		return Integer.parseInt(port.getText()) > 1024;
	}

	public void serverStart(ActionEvent e) throws IOException {

		initServerScene();

		primaryStage.setTitle("Server Application");
		primaryStage.setScene(serverScene);
		primaryStage.show();

	}

	public void gamerStart(ActionEvent e) throws IOException {

		initGamerScene();

		Consumer<CFourInfo> walmart = f -> {

			System.out.println("recieved:  ");
			System.out.println(f.getCol());
			System.out.println(f.getPlayer());
			System.out.println(f.getStatus());

			try {
				clientGame.getInstance().enemyMove(f);
				clientGame.getInstance().makeMove();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		};

		clientComThread.getInstance().init(5555, walmart);
		clientComThread.getInstance().start();

		primaryStage.setTitle("Gamy boi");
		primaryStage.setScene(gamerScene);
		primaryStage.show();
	}

	public void echo(ActionEvent e) throws IOException {
		clientComThread.getInstance().send(
				new CFourInfo(0, 10, 2)
		);
	}
}
