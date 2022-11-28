package serverLogic;

import javafx.stage.Stage;

import java.util.function.Consumer;

public class Globals {

	public static class temp {
		public static int port = -1;
		public static Stage primaryStage = null;

		public static Consumer<String> addString = null; // callback for setting strings
	}

	public static class constants {
		public static final int width = 7;
		public static final int height = 6;
	}

}
