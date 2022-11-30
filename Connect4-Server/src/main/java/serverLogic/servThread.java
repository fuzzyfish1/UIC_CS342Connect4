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

		serverComThread p1 = null, p2 = null;


		while (true) {

			try {

				// if one is not alive send the other the wait command

				getActivePlayer(p1);
				p1.send(new CFourInfo(-1, 0, status.WAITING));

				getActivePlayer(p2);

				servGame g = new servGame();
				g.init(p1, p2);
				g.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		/*
		try {
			while (true) {
				Socket temp = serverSocket.accept();
				p1 = new serverComThread(temp, c -> {});

				p1.send(new CFourInfo(-1, 0, status.WAITING));

				temp = serverSocket.accept();
				p2 = new serverComThread(temp, c -> {});

				servGame g = new servGame();
				g.init(p1, p2);
				g.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("This should hopefully try and see if the system");

			if (p1.get)

		} catch (Exception e) {
			e.printStackTrace();
		}
*/

	}

	public void getActivePlayer(serverComThread player) {

		while (!player.isAlive()) {

			try {

				Socket temp = serverSocket.accept();
				player = new serverComThread(temp, c -> {});

				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}