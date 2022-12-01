package serverLogic;

import serverComs.serverComThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class playerAccThread extends Thread {
	ServerSocket serverSocket;
	int port;

	private playerAccThread() {

	}
	private static playerAccThread instance = null;

	public static playerAccThread getInstance() {

		if (instance == null) {
			instance = new playerAccThread();
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

		while (true) {
			try {

				Socket temp = serverSocket.accept();
				servThread.getInstance().playerQueue.add(new serverComThread(temp, c -> {}));

				servThread.getInstance().processQueue();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
