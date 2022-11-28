package serverComs;

import java.net.Socket;
import java.util.function.Consumer;

import commonCode.*;

public class serverComThread extends comThread {

	public serverComThread(Socket connection, Consumer<CFourInfo> callback) {
		this.setFlipped();
		this.setSocket(connection);
		this.setCallback(callback);
		this.start();
	}

}
