package coms;
import game.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class servThread extends Thread {

	private static comThread instance = null;
	int port;

	public static comThread getInstance() {

		if (instance == null) {
			instance = new comThread();
		}

		return instance;
	}

	public void init(int port) {
		this.port = port;
	}

	@Override
	public void run() {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {

			try {

				servGame e = new servGame();
				e.init(serverSocket.accept(), serverSocket.accept());
				e.run();

			} catch (IOException e) {

				System.out.println("I/O error: " + e);
			}
		}

	}
}