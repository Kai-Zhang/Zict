package client;

import network.SendMessage;

public class ClickLike {
	public ClickLike() { }
	
	public static void like(String word,String which) {
		String message = "like" + " " + UserInfo.getName()+" "+word + " " + which;
		new Thread(new SendMessage(message)).start();
	}
}
