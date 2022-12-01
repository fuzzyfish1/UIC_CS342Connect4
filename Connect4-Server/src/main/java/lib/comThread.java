package lib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class comThread extends Thread {
	private Socket connection;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private boolean flipped = false;
	private Consumer<CFourInfo> callback = c -> {
		throw new RuntimeException("comThread callback not set");
	};
	private Consumer<CFourInfo> sendCallback = c->{
		System.out.println("sending Stuff");
	};
	private int numReceived = 0;

	public void setCallback(Consumer<CFourInfo> callback) {
		this.callback = callback;
	}

	public Consumer<CFourInfo> getCallback() {
		return this.callback;
	}

	public void setSocket(Socket s) {
		this.connection = s;
	}

	public Socket getSocket () {
		return this.connection;
	}

	public int getNumReceived() {
		return numReceived;
	}

	public void setFlipped() {
		this.flipped = true;
	}

	public void setSendCallback(Consumer<CFourInfo> callback) {
		this.sendCallback = callback;
	}

	public void init() throws Exception {

		if (this.flipped) {
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());
		} else {
			in = new ObjectInputStream(connection.getInputStream());
			out = new ObjectOutputStream(connection.getOutputStream());
		}

	}

	@Override
	public void run() {

		try {
			while (true) {
				numReceived++;

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
			sendCallback.accept(data);
			out.writeObject(data);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
