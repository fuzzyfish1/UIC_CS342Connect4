/* Project 3: Connect 4
*  CS342 11am T, TH Lec
*  This project is connect 4
*  uses sockets to connect to server
*  this is multiplayer
*  Main starts the program by giving the
*  GUI controller access to the primary stage
* */

import GUI.startSceneController;
import logic.Globals;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			Globals.temp.primaryStage = primaryStage;

			startSceneController x = new startSceneController();
			x.start();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
