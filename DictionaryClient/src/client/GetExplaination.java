package client;

import network.SendMessage;

public class GetExplaination {
	static String explanation = null;
	
	public GetExplaination() { }
	public static String[] get(String word, int searchOption) {
		// The query format: query word 111 <-- option: baidu | youdao | bing
		String message = "query" + " " + word + " " + searchOption;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
		while (explanation == null);
		String[] temp = explanation.split(" ");
		explanation = null;
		return temp;
	}
}
