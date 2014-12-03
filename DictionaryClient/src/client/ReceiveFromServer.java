package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReceiveFromServer implements Runnable{
	Socket socket;
	public ReceiveFromServer(String IP) throws UnknownHostException, IOException {
		socket=new Socket("127.0.0.1",5451);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
			try {
				BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line=reader.readLine();
				if (line!=null){
					DealAnswer dealAnswer=new DealAnswer();
					dealAnswer.deal(line);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
