public class serverThread extends Thread {

	Socket connection;
	ObjectInputStream in;
	ObjectOutputStream out;

	public static instance = null;

	public getInstance() {

		if (instance == null) {
			instance = new serverThread();
		}

		return instance;
	}

	private serverThread;

	@Override
	public void run() {
		//This is Server
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);

			Socket socket = serverSocket.accept();

			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
		}
	}
























	/*

	private Consumer<Serializable> callback;

	Client(Consumer<Serializable> call){

		callback = call;
	}

	public void run() {

		try {
			socketClient= new Socket("127.0.0.1",5555);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}

		while(true) {

			try {
				String message = in.readObject().toString();
				callback.accept(message);
			}
			catch(Exception e) {}
		}

	}

	public void send(String data) {

		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}