/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  This is the Server Code that manages players interactions
 *  between the server and their player partner
 * */


package game;

import coms.CFourInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class servGame extends Thread {

	Socket p1, p2;
	ObjectInputStream p1In;
	ObjectInputStream p2In;

	ObjectOutputStream p1out;
	ObjectOutputStream p2out;

	public void init (Socket s1, Socket s2) throws IOException {
		this.p1 = s1;
		this.p2 = s2;

		p1In = new ObjectInputStream(p1.getInputStream());
		p2In = new ObjectInputStream(p2.getInputStream());

		p1out = new ObjectOutputStream(p1.getOutputStream());
		p2out = new ObjectOutputStream(p2.getOutputStream());
	}


	@Override
	public void run() {
		try {

			p2out.writeObject( new CFourInfo(-1, 0, 3));
						//p2In = new ObjectInputStream(p2.getInputStream());

			while (true) {

				CFourInfo data = (CFourInfo) p2In.readObject();

				if (data != null) {

					System.out.println("Game Obj Recieve2: ");
					System.out.println(data.getPlayer());
					System.out.println(data.getCol());
					System.out.println(data.getStatus());

					p1out.writeObject(data);

				}

				 data = (CFourInfo) p1In.readObject();

				if (data != null) {

					System.out.println("Game Obj Recieve1: ");
					System.out.println(data.getPlayer());
					System.out.println(data.getCol());
					System.out.println(data.getStatus());

					p2out.writeObject(data);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
