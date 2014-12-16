package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import logic.DealAnswer;


public class Network {
	public static String SERVER_IP = null;
	private static Socket transportSocket = null;
	private static PrintWriter socketWriter;
	private static BufferedReader socketReader;
	
	private static class SendThread implements Runnable {
		private String message;
		
		public SendThread(String message) { this.message = message; }
		
		@Override
		public void run() {
			try {
				socketWriter.write(message + '\n');
				socketWriter.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class ReceiveThread implements Runnable {
		@Override
		public void run() {
			while (true){
				try {
					String line = socketReader.readLine();
					if (line != null){
						System.out.println(line);
						new Thread(new DealAnswer(line)).start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Network() {}
	
	public static void connectToServer(String serverIP) throws UnknownHostException, IOException {
		SERVER_IP = serverIP;
		transportSocket = new Socket(serverIP, 60000);
		socketWriter = new PrintWriter(transportSocket.getOutputStream());
		socketReader = new BufferedReader(new InputStreamReader(transportSocket.getInputStream()));
	}
	
	public static void sendToServer(String message) {
		new Thread(new SendThread(message)).start();
	}
	
	public static void receiveFromServer() {
		new Thread(new ReceiveThread()).start();
	}
}
