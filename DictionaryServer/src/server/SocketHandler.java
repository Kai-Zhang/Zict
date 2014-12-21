package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * SocketHandler - handles all the request from a particular socket
 */
public class SocketHandler implements Runnable{
	Socket socket;
	String IP;
	public SocketHandler(Socket socket,String IP) {
		this.socket=socket;
		this.IP=IP;
	}
	
	@Override
	public void run() {
		while (true){
			BufferedReader reader;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String nowString;
				nowString = reader.readLine();
				if (nowString.equals("Bye!")){
					socket.close();
					return ;
				}
				if (nowString!=null){
					System.out.println(nowString);
					new Thread(new DealQuery(nowString, IP)).start();;
				}
			} 
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}
