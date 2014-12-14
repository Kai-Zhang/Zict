package com.example.dictionaryandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Message;

public class ReceiveFromServer extends Thread{
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
					Message msg=new Message();
					Bundle bundle=new Bundle();
					bundle.putString("data", line);
					msg.setData(bundle);
					MainActivity.msghandler.sendMessage(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
