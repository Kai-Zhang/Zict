package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToClient implements Runnable{
	String content;
	String IP;
	public SendToClient() {
		content=null;
		IP=null;
	}
	public SendToClient(String content,String IP){
		this.content=content;
		this.IP=IP;
	}
	@Override
	public void run() {
		try {
			Socket socket=IPMap.getSocket(IP);
			System.out.println(IP);
			PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
			System.out.println(content);
			printWriter.write(content+"\n");
			printWriter.flush();
			//printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}

}
