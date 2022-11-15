import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// Read file fxml and draw interface.

			MyController.getInstance().setStage(primaryStage);
			MyController.getInstance().start();

			System.out.println("start in main");
			System.out.println(primaryStage);

		} catch (Exception e) {
			System.out.println("start in main err");
			System.out.println(primaryStage);

			e.printStackTrace();

			System.out.println("start in main err");
			System.out.println(primaryStage);

			System.exit(1);
		}

		System.out.println("start in main2");
		System.out.println(primaryStage);
	}

}
