package lesson01.exam02.multiserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculatorServer {
	private int port;
	
	public CalculatorServer(int port) {
		this.port = port;
	}
	
	public void service() throws Exception {
		ServerSocket serverSocket = new ServerSocket(port);
		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println("CalculatorServer startup:");
		Socket socket;
		
		while(true) {
			try {
				socket = serverSocket.accept();
				System.out.println("connected to client.");
				CalculatorWorker calculatorWorker = new CalculatorWorker(socket);
				executorService.submit(calculatorWorker);
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

