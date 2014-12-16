package client;

import network.SendMessage;

public class Share {
	public Share(){}
	public static void ClickShare(String touser,String explain){
		String message="share"+" "+touser+" "+explain;
		new Thread(new SendMessage(message)).start();
	}
}
