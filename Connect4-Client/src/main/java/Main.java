/* Project 3: Connect 4
*  CS342 11am T, TH Lec
*  This project is connect 4
*  uses sockets to connect to server
*  this is multiplayer
*  Main starts the program by giving the
*  GUI controller access to the primary stage
* */

import GUI.gameStart;
import coms.clientComThread;
import logic.Globals;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) { // removed a throws, we have a try catch it is unclean
		try {
			clientComThread.getInstance().setCallback(c-> {
				System.out.println("SENDING STUFF");
				System.out.println(c.getStatus());
			});

			Globals.temp.primaryStage = primaryStage;

			gameStart x = new gameStart();
			x.start();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
