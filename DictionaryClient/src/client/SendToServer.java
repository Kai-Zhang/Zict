package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendToServer implements Runnable {
	Socket socket;
	String content;
	public SendToServer(String IP,String content) throws UnknownHostException, IOException{
		socket=new Socket(IP, 5000);
		this.content=content;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.write(content);
			printWriter.flush();
			printWriter.close();
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
