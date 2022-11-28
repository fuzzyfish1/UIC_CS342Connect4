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

import java.net.Socket;

public class servGame extends Thread {

	serverComThread p1, p2;

	public void init (serverComThread s1, serverComThread s2) {
		this.p1 = s1;
		this.p2 = s2;

		Globals.temp.addString.accept("ServGame Started");
	}

	@Override
	public void run() {
		try {

			p1.setCallback(c -> {
				Globals.temp.addString.accept("Received CFourInfo from P1: col" + c.getCol() + ", P" + c.getPlayer() + ", Status" + c.getStatus());
				p2.send(c);
			});

			p2.setCallback(c -> {
				Globals.temp.addString.accept("Received CFourInfo from P2: col" + c.getCol() + ", P" + c.getPlayer() + ", Status" + c.getStatus());
				p1.send(c);
			});

			p1.send(new CFourInfo(-1, 0, status.START)); // first turn signal
			Globals.temp.addString.accept("Sent CFourInfo to P1: col -1, P0, Status START");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public serverComThread getP1() {
		return p1;
	}

	public serverComThread getP2() {
		return p2;
	}

}
