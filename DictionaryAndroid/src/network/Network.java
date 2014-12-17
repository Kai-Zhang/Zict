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
	
	
	public Network() {}
	
	public static void connectToServer(String serverIP) throws UnknownHostException, IOException {
		SERVER_IP = serverIP;
		new Thread(new connect(serverIP)).start();		
	}
	static class connect implements Runnable{
		String IP;
		public connect() {
			// TODO Auto-generated constructor stub
		}
		public connect(String serverIP){
			IP=serverIP;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println("First");
				transportSocket = new Socket(IP, 60000);
				System.out.println("First Connect Success!");
				socketWriter = new PrintWriter(transportSocket.getOutputStream());
				System.out.println("Second Connect Success!");
				socketReader = new BufferedReader(new InputStreamReader(transportSocket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public static void sendToServer(String message) {
		new Thread(new SendThread(message)).start();
	}
	
}
