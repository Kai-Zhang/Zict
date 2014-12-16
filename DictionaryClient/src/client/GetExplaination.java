package client;

import network.SendMessage;

public class GetExplaination {
	static String explanation = null;
	
	public GetExplaination() { }
	public static String[] get(String word, int searchOption) {
		// The query format: query word 111 <-- option: baidu | bing | youdao
		String searchString=String.format("%03d", searchOption);
		int size=0;
		for (int i=0;i<searchString.length();i++)
			if (searchString.charAt(i)=='1') size++;
		String message = "query" + " " + word + " " + searchString + '\n';
		new Thread(new SendMessage(message)).start();
		while (explanation == null){
			System.out.print("");
		}
		System.out.println("unlocked");
		String[] temp=new String[size];
		for (int i=0;i<size;i++)
			temp[i]=explanation;
		explanation = null;
		return temp;
	}
	public static void setExplanation(String explanation) {
		GetExplaination.explanation = explanation;
	}
}
