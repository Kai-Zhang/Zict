package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.dictionaryandroid.MainActivity;

import android.os.Bundle;
import android.os.Message;


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
		private String serverIP;
		public ReceiveThread(String IP){
			serverIP=IP;
		}
		@Override
		public void run() {
			SERVER_IP = serverIP;
			try{
			transportSocket = new Socket(SERVER_IP, 60000);
			System.out.println("Socket Success");
			socketWriter = new PrintWriter(transportSocket.getOutputStream());
			socketReader = new BufferedReader(new InputStreamReader(transportSocket.getInputStream()));
			}
			catch (Exception e){
				e.printStackTrace();
			}
			while (true){
				try {
					String line = socketReader.readLine();
					if (line != null){
						System.out.println(line);
						Bundle bundle=new Bundle();
						bundle.putString("data", line);
						Message message=new Message();
						message.setData(bundle);
						MainActivity.msghandler.sendMessage(message);
					}
				}
					catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Network() {}
	
	public static void connectToServer(String serverIP) throws UnknownHostException, IOException {
		/*SERVER_IP = serverIP;
		transportSocket = new Socket(serverIP, 60000);
		socketWriter = new PrintWriter(transportSocket.getOutputStream());
		socketReader = new BufferedReader(new InputStreamReader(transportSocket.getInputStream()));
		*/
		new Thread(new ReceiveThread(serverIP)).start();
	}
	
	public static void sendToServer(String message) {
		new Thread(new SendThread(message)).start();
	}
	
	/*public static void receiveFromServer() {
		new Thread(new ReceiveThread()).start();
	}*/
}
