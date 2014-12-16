package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import client.DealAnswer;

public class ReceiveFromServer implements Runnable{
	Socket socket;
	BufferedReader reader;
	public ReceiveFromServer() throws IOException {
		socket = SendMessage.getSocket();
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	@Override
	public void run() {
		while (true){
			try {
				if (socket.isClosed()) {
					socket=new Socket(SendMessage.SERVER_IP, 60000);
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				}
				String line = reader.readLine();
				if (line != null){
					new Thread(new DealAnswer(line)).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
