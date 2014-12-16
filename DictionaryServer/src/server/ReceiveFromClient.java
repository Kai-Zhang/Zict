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

import sql.SQLManager;
public class ReceiveFromClient {
	public static void main(String[] args) throws ClassNotFoundException{
		SQLManager.init();
			try {
				ServerSocket serverSocket=new ServerSocket(60000);
				while (true){
					Socket socket=serverSocket.accept();
					System.out.println("New connection accepted");
					String IP=socket.getInetAddress().toString();
					IPMap.insert(IP, socket);
					new Thread(new SocketHandler(socket,IP)).start();
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
