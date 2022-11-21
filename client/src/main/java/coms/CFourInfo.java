package coms;

import java.io.Serializable;

/*
* // status is information passed from server to client, about the
current game

0 = client loses
1 = client wins
2 = tie
3 = start game or ready to receive next move (both clients connected)

* */


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
