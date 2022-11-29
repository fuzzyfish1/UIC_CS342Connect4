/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  This thread listens for new players starts their games
 *  and matches players to Games
 * */
package serverLogic;

import commonCode.CFourInfo;
import commonCode.status;
import serverComs.serverComThread;
import serverLogic.Globals;
import serverLogic.servGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class servThread extends Thread {

	private static servThread instance = null;
	int port;
	ServerSocket serverSocket;

	//Queue<Socket> playerQueue;

	public static servThread getInstance() {

		if (instance == null) {
			instance = new servThread();
		}

		return instance;
	}

	public void init(int port) {
		this.port = port;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			while (true) {
				Socket temp = serverSocket.accept();
				serverComThread p1 = new serverComThread(temp, c -> {});

				p1.send(new CFourInfo(
						-1, 0, status.WAITING
				));

				temp = serverSocket.accept();
				serverComThread p2 = new serverComThread(temp, c -> {});

				servGame g = new servGame();
				g.init(p1, p2);
				g.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


//		try {
//
//			Socket p1 = serverSocket.accept();
//			a.accept("P1: " + p1.getInetAddress() + ":" + p1.getLocalPort());
//
//			Socket p2 = serverSocket.accept();
//			a.accept("P2: " + p1.getInetAddress() + ":" + p1.getLocalPort());
//
//			servGame g = new servGame();
//
//			g.init(p1, p2, a);
//
//			g.start();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


	}
}