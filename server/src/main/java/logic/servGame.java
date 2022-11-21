/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  This is the Server Code that manages players interactions
 *  between the server and their player partner
 * */


package logic;

import coms.CFourInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class servGame extends Thread {

	Socket p1, p2;
	ObjectInputStream p1In;
	ObjectInputStream p2In;

	ObjectOutputStream p1out;
	ObjectOutputStream p2out;

	Consumer<String> a;

	public void init (Socket s1, Socket s2, Consumer<String> a) throws IOException {
		this.p1 = s1;
		this.p2 = s2;
		this.a = a;

		p1In = new ObjectInputStream(p1.getInputStream());
		p2In = new ObjectInputStream(p2.getInputStream());

		p1out = new ObjectOutputStream(p1.getOutputStream());
		p2out = new ObjectOutputStream(p2.getOutputStream());

		a.accept("ServGame Started");
	}


	@Override
	public void run() {
		try {

			p1out.writeObject( new CFourInfo(-1, 0, 3));
			a.accept("Sent CFourInfo to P1: col -1, P0, Status3");

			while (true) {

				CFourInfo data = (CFourInfo) p1In.readObject();

				if (data != null) {

					a.accept("Received CFourInfo from P1: col" + data.getCol() + ", P" + data.getPlayer() + ", Status" + data.getStatus());
					p2out.writeObject(data);
				}

				data = (CFourInfo) p2In.readObject();

				if (data != null) {

					a.accept("Received CFourInfo from P2: col" + data.getCol() + ", P" + data.getPlayer() + ", Status" + data.getStatus());
					p1out.writeObject(data);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket getP1() {
		return p1;
	}

	public Socket getP2() {
		return p2;
	}

}
