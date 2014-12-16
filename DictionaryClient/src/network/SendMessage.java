package network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMessage implements Runnable {
	public static String SERVER_IP;
	private static Socket socket;
	private String message;
	private static PrintWriter printWriter;
	public SendMessage(String message) { this.message = message; }
	
	public static void connect(String IP) {
		try {
			socket = new Socket(IP, 60000);
			SERVER_IP = IP;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			System.out.println(message);
			printWriter = new  PrintWriter(socket.getOutputStream());
			printWriter.write(message+"\n");
			printWriter.flush();
			//printWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Socket getSocket() {
		return socket;
	}
}
