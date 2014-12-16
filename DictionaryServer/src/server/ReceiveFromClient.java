package server;

import java.net.ServerSocket;
import java.net.Socket;


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
