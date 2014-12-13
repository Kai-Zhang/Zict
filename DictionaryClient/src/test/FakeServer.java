package test;

import java.io.IOException;
import java.net.ServerSocket;

public class FakeServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(60000);
		while (true) {
			System.out.print("Waiting for connections ... ");
			serverSocket.accept();
			System.out.println("New connection accepted");
		}
	}
}
