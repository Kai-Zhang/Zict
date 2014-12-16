package logic;

import network.Network;


public class Share {
	public Share(){}
	public static void ClickShare(String toUser,String explain){
		String message = "share" + " " + toUser + " " + explain;
		Network.sendToServer(message);;
	}
}
