

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyController implements Initializable {
	private static MyController instance = null;
	static Stage primaryStage = null;
	@FXML
	private VBox root;

	@FXML
	private BorderPane root2;

	@FXML
	private TextField port;

	@FXML
	private TextField putText;

	//static so each instance of controller can access to update
	private static String textEntered = "";

	Scene serverScene;
	Scene gamerScene;

	public static MyController getInstance() {
		if (instance == null) {
			instance = new MyController();
		}

		return instance;
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void start() throws IOException {
		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/Myfxml.fxml"));

		primaryStage.setTitle("connect 4 Start");
		Scene s1 = new Scene(root, 500, 500);
		s1.getStylesheets().add("/styles/style1.css");

		primaryStage.setScene(s1);

		primaryStage.show();
	}

	public void initGamerScene() throws IOException {

		Parent root = FXMLLoader.load(getClass()
				.getResource("/FXML/Myfxml2.fxml"));

		//primaryStage.setTitle("gamer");
		Scene gamerScene = new Scene(root, 500, 500);

		gamerScene.getStylesheets().add("/styles/style2.css");
		primaryStage.setTitle("gamer");
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
    //method so that the controller created for second view can update the text
    //field with the text from the first vie

	public boolean validatePort() {
		return Integer.parseInt(port.getText()) > 1024;
	}

	public void serverStart(ActionEvent e) throws IOException {

		if (validatePort()) {
			initGamerScene();
			primaryStage.setScene(gamerScene);
			primaryStage.show();
		}
	}

	public void gamerStart(ActionEvent e) throws IOException {

		if (validatePort()) {
			primaryStage.setScene(gamerScene);
			primaryStage.show();
		}
	}
	
	

}
