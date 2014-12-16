package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler implements Runnable{
	Socket socket;
	String IP;
	public SocketHandler(Socket socket,String IP) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		this.IP=IP;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
