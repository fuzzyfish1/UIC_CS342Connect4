package coms;

import java.io.Serializable;

public class CFourInfo implements Serializable {

	int col;
	int player;
	int status;

	public CFourInfo(int col, int player, int status) {
		this.col = col;
		this.player = player;
		this.status = status;
	}

	public int getCol() {
		return col;
	}

	public int getPlayer() {
		return player;
	}

	public int getStatus() {
		return status;
	}
}
