package coms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class comThread extends Thread {

	int port;
	boolean server;
	Socket connection;
	ObjectInputStream in;
	ObjectOutputStream out;
	Consumer<CFourInfo> callback;


	private static comThread instance = null;

	public static comThread getInstance() {

		if (instance == null) {
			instance = new comThread();
		}

		return instance;
	}

	public void init(int port, boolean server, Consumer<CFourInfo> callback) {
		this.port = port;
		this.server = server;
		this.callback = callback;
	}

	@Override
	public void run() {

		try {
			if (server) {
				ServerSocket serverSocket = new ServerSocket(port);
				connection = serverSocket.accept();
			} else {
				connection = new Socket("127.0.0.1", port);
				connection.setTcpNoDelay(true);
				this.send(new CFourInfo( 0, 1, 2));
			}

			in = new ObjectInputStream(connection.getInputStream());

			while (true) {

				CFourInfo message = (CFourInfo) in.readObject();
				callback.accept(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(CFourInfo data) {
		try {
			out = new ObjectOutputStream(connection.getOutputStream());
			out.writeObject(data);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}