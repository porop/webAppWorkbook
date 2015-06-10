package porop.server.calculator;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorWorker extends Thread {
	static int count;
	Socket socket;
	Scanner in;
	PrintStream out;
	int workerId;
	
	public CalculatorWorker(Socket socket) throws Exception {
		workerId = ++count;
		this.socket = socket;
		in = new Scanner(socket.getInputStream());
		out = new PrintStream(socket.getOutputStream());
	}
	public void run() {
		System.out.println("[thried-"+workerId+"] processing the client request.");
		String operator = null;
		double a, b, r;
		
		while(true) {
			try {
				operator = in.nextLine();
				if (operator.equals("goodbye")) {
					out.println("goodbye");
					break;
				} else {
					a = Double.parseDouble(in.nextLine());
					b = Double.parseDouble(in.nextLine());
					r = 0;
					
					switch (operator) {
					case "+": r = a + b; break;
					case "-": r = a - b; break;
					case "*": r = a * b; break;
					case "%": r = a % b; break;
					case "/": 
						if(b == 0) throw new Exception("Cannot divide with 0!");
						r = a / b; break;
					default:
						throw new Exception("Not supported operation.");
							
					}
					out.println("success");
					out.println(r);
				}
			} catch (Exception err) {
				out.println("failure");
				out.println(err.getMessage());
			}
		}
		try{out.close();} catch(Exception e) {}
		try{in.close();} catch(Exception e) {}
		try{socket.close();} catch(Exception e) {}
		System.out.println("[thried-"+workerId+"] closed the client.");
	}
}
