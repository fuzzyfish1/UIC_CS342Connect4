/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  This is the Server Code that manages players interactions
 *  between the server and their player partner
 * */


package serverLogic;

import commonCode.CFourInfo;
import commonCode.status;
import serverComs.serverComThread;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class servGame extends Thread {

	serverComThread p1, p2;

	public void init (serverComThread s1, serverComThread s2) {
		this.p1 = s1;
		this.p2 = s2;

		Globals.temp.addString.accept("ServGame Started");

	}

	public Consumer<CFourInfo> getRematchCallback(serverComThread p1s) {

		return g -> {
			if (g.getStatus() == status.REMATCH) {
				Globals.temp.addString.accept("Recieved REMATCH"+ p1s.toString() + g.getStatus());
				System.out.println("rematch" + p1s.toString());
				servThread.getInstance().playerQueue.add(p1s);

				servThread.getInstance().processQueue();
				servThread.getInstance().playerQueue.size();
			}
		};

	}

	public Consumer<CFourInfo> getGameCallback(serverComThread p1, serverComThread p2) {
		return c -> {
			Globals.temp.addString.accept("Received CFourInfo from " + p1.toString() + ": col" + c.getCol() + ", P" + c.getPlayer() + ", Status" + c.getStatus());
			p2.send(c);

			if (c.getStatus() == status.TIE || c.getStatus() == status.WIN || c.getStatus() == status.LOSE) {
				Globals.temp.addString.accept("Received CFourInfo from " + p1.toString() + ": col" + c.getCol() + ", P" + c.getPlayer() + ", Status" + c.getStatus());
				p2.setCallback(getRematchCallback(p2));
				p1.setCallback(getRematchCallback(p1));

			}
		};
	}

	@Override
	public void run() {

		// I removed the Try catch Around this whole thing, when one disconnects it should error
		// hopefully we can catch it and say to restart the server with whoever is still alive
		p1.setCallback(getGameCallback(p1, p2));
		p2.setCallback(getGameCallback(p2, p1));


		p1.send(new CFourInfo(-1, 0, status.START));
		p2.send(new CFourInfo(-1, 0, status.START));

	}

	public serverComThread getP1() {
		return p1;
	}

	public serverComThread getP2() {
		return p2;
	}

}
