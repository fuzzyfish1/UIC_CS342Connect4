/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  This thread listens for new players starts their games
 *  and matches players to Games
 * */
package serverLogic;

import lib.CFourInfo;
import lib.status;
import serverComs.serverComThread;

import java.util.*;

public class servThread {

	private static servThread instance = null;
	int port;
	Queue<serverComThread> playerQueue = new LinkedList<>();

	serverComThread waitingDude;

	public static servThread getInstance() {

		if (instance == null) {
			instance = new servThread();
		}

		return instance;
	}

	public void init(int port) {
		playerAccThread.getInstance().init(port);
		playerAccThread.getInstance().start();
	}

	public void processQueue() {
		while (!playerQueue.isEmpty()) {

			if (waitingDude == null && !playerQueue.isEmpty()) {

				waitingDude = playerQueue.poll();
				waitingDude.send(new CFourInfo(-1, 0, status.WAITING));

			} else if (!playerQueue.isEmpty()) {

				servGame g = new servGame();

				g.init(waitingDude, playerQueue.poll());
				g.start();
				waitingDude = null;
			}
		}
	}
}