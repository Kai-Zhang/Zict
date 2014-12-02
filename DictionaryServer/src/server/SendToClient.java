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
			Socket socket=new Socket(IP, 5451);
			PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
			printWriter.write(content);
			printWriter.flush();
			printWriter.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
