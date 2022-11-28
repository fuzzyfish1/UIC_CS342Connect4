package commonCode;

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

	@Override
	public void run() {

		try {

			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());

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
			System.out.println(" sending:  ");
			out.writeObject(data);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
