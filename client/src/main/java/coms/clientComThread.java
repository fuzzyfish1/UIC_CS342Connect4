/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  The communication thread for clients to server
 * */
package coms;

import logic.Globals;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class clientComThread extends Thread {

	int port;
	Socket connection;
	ObjectInputStream in;
	ObjectOutputStream out;
	Consumer<CFourInfo> callback;

	static int numRecieved = 0;

	private static clientComThread instance = null;

	public static clientComThread getInstance() {

		if (instance == null) {
			instance = new clientComThread();
		}

		return instance;
	}

	public void setCallback(Consumer<CFourInfo> callback) {
		this.callback = callback;
	}

	public void init(Consumer<CFourInfo> callback) throws IOException {
		this.port = Globals.temp.port;
		this.callback = callback;

		connection = new Socket("localhost", Globals.temp.port);

	}

	@Override
	public void run() {

		try {

			System.out.println("bloop");
			out = new ObjectOutputStream(connection.getOutputStream());
			System.out.println("bleep");
			in = new ObjectInputStream(connection.getInputStream());
			System.out.println("blap");

			while (true) {
				numRecieved++;


				CFourInfo message = (CFourInfo) in.readObject();


				if (message != null) {
					System.out.println("blap");
					System.out.println(numRecieved);
					callback.accept(message);
					System.out.println("blap");
					System.out.println(numRecieved);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(CFourInfo data) {
		try {

			System.out.println(" sending:  ");
			out.writeObject(data);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}