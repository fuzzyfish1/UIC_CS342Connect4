/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  The communication thread for clients to server
 * */
package coms;

import lib.CFourInfo;
import lib.comThread;
import lib.status;
import logic.Globals;
import logic.clientGame;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class clientComThread extends comThread {

	private int port;
	private static clientComThread instance = null;

	private clientComThread() {}

	public static clientComThread getInstance() {

		if (instance == null) {
			instance = new clientComThread();
		}

		return instance;
	}

	public void init(Consumer<CFourInfo> callback) throws Exception {

		clientGame.getInstance().currStatus = status.RUNNING;
		this.setCallback(callback);
		this.port = Globals.temp.port;

		if (!this.isAlive()) {
			this.setSocket(new Socket("localhost", this.port));
		}

		super.init();
		this.start();

	}
}