package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import client.DealAnswer;

public class ReceiveFromServer implements Runnable{
	Socket socket;
	public ReceiveFromServer(String IP) throws UnknownHostException, IOException {
		socket = new Socket(IP, 60000);
	}
	@Override
	public void run() {
		while (true){
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
