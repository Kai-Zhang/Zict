package com.example.dictionaryandroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMessage implements Runnable {
	public static String SERVER_IP;
	private Socket socket;
	private String message;
	
	public SendMessage(String IP,String message) {
		try {
			socket = new Socket(IP, 60001);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.message = message;
	}
	
	@Override
	public void run() {
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.write(message);
			printWriter.flush();
			printWriter.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
