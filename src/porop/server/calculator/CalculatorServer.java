package porop.server.calculator;

import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
	private int port;

	public CalculatorServer(int port) {
		this.port = port;
	}
	
	@SuppressWarnings("resource")
  public void service() throws Exception {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("CalculatorServer startup:");
		
		Socket socket = null;
		while(true) {
			try {
				System.out.println("waiting client...");
				socket = serverSocket.accept();
				System.out.println("connected to client.");
				new CalculatorWorker(socket).start();
				System.out.println("closed client.");
			} catch (Throwable e) {
				System.out.println("connection error!");
			}
		}
	}
	public static void main(String[] args) throws Exception {
		CalculatorServer app = new CalculatorServer(8888);
		app.service();
	}

}
