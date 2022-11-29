package serverComs;

import java.net.Socket;
import java.util.function.Consumer;

import commonCode.*;
import serverLogic.Globals;

public class serverComThread extends comThread {

	public serverComThread(Socket connection, Consumer<CFourInfo> callback) throws Exception {

		this.setSendCallback( e -> {
			Globals.temp.addString.accept(this.toString() + "Sent: col" + e.getCol() + " status " + e.getStatus());
		});

		this.setFlipped();
		this.setSocket(connection);
		this.setCallback(callback);

		Globals.temp.addString.accept(this.toString() + connection.getInetAddress() + ": " + connection.getLocalPort());
		this.init();
		this.start();


	}

}
