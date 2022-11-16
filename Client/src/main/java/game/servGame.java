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

	public void init (Socket s1, Socket s2) {
		this.p1 = s1;
		this.p2 = s2;
	}


	@Override
	public void run() {
		try {

			p1In = new ObjectInputStream(p1.getInputStream());
			p2In = new ObjectInputStream(p2.getInputStream());

			while (true) {
				CFourInfo data = (CFourInfo) p1In.readObject();

				if (data != null) {
					ObjectOutputStream out = new ObjectOutputStream(p2.getOutputStream());
					out.writeObject(data);
					out.close();
				}

				data = (CFourInfo) p2In.readObject();

				if (data != null) {
					ObjectOutputStream out = new ObjectOutputStream(p1.getOutputStream());
					out.writeObject(data);
					out.close();
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
