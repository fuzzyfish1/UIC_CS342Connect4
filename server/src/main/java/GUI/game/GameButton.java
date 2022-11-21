package GUI.game;

import javafx.scene.control.Button;

public class GameButton extends Button {

	int r,c, value;

	public GameButton(int r, int c) {

		super();

		this.r = r;
		this.c = c;

	}

	public void setValue(int value) {
		this.value = value;

		if (value == 0) {
			setStyle("-fx-background-color: #fcaf38");
		} else if (value == 1) {
			setStyle("-fx-background-color: #15fd05");
		} else if (value == 2) {
			setStyle("-fx-background-color: #ff0000");
		}
	}
}
