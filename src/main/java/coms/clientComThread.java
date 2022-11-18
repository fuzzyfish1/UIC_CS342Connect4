/* Project 3: Connect 4
 *  CS342 11am T, TH Lec
 *  This project is connect 4
 *  uses sockets to connect to server
 *  this is multiplayer
 *  The communication thread for clients to server
 * */
package coms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class clientComThread extends Thread {

	int port;
	Socket connection;
	ObjectInputStream in;
	ObjectOutputStream out;
	Consumer<CFourInfo> callback;


	private static clientComThread instance = null;

	public static clientComThread getInstance() {

		if (instance == null) {
			instance = new clientComThread();
		}

		return instance;
	}

	public void init(int port, Consumer<CFourInfo> callback) throws IOException {
		this.port = port;
		this.callback = callback;

		connection = new Socket("localhost", 5555);

		out = new ObjectOutputStream(connection.getOutputStream());
		in = new ObjectInputStream(connection.getInputStream());

	}

	@Override
	public void run() {

		try {

			while (true) {

				CFourInfo message = (CFourInfo) in.readObject();

				if (message != null) {
					callback.accept(message);
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