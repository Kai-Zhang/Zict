package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.net.SocketFactory;
public class ReceiveFromClient implements Runnable{
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
			try {
				ServerSocket serverSocket=new ServerSocket(5000);
				while (true){
					Socket socket=serverSocket.accept();
					System.out.println("New connection accepted");
					String IP=socket.getInetAddress().toString();
					BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String nowString=reader.readLine();
					DealQuery dealQuery=new DealQuery(nowString,IP);
					Thread thread=new Thread(dealQuery);
					thread.start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
