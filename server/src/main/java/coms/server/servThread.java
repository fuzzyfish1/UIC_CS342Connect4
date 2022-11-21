/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  This is the Server Code that accepts new players
 *  and starts a game for players
 * */
package coms.server;

import logic.server.servGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class servThread extends Thread {

	private static servThread instance = null;
	int port;
	ServerSocket serverSocket;

	Consumer<String> a; // callback

	public static servThread getInstance() {

		if (instance == null) {
			instance = new servThread();
		}

		return instance;
	}

	public void init(int port, Consumer<String> a) {
		this.port = port;
		this.a = a;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {

			Socket p1 = serverSocket.accept();
			a.accept("P1: " + p1.getInetAddress() + ":" + p1.getLocalPort());

			Socket p2 = serverSocket.accept();
			a.accept("P2: " + p1.getInetAddress() + ":" + p1.getLocalPort());

			servGame g = new servGame();

			g.init(p1, p2, a);

			g.start();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}