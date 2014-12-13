package client;

import network.SendMessage;

public class GetExplaination {
	static String explanation = null;
	
	public GetExplaination() { }
	public static String[] get(String word) {
		// TODO: Alien `st' variable here in server
		String message = "query" + " " + word;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
		while (explanation == null);
		// TODO: Split the Explanation
		String[] temp = explanation.split(" ");
		explanation = null;
		return temp;
	}
}
