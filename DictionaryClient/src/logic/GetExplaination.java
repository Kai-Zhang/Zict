package logic;

import data.Explanation;
import data.WordEntry;
import network.Network;


public class GetExplaination {
	static String explanation = null;
	
	public GetExplaination() { }
	public static void get(String word) {
		WordEntry.setWord(word);
		String message = "query" + " " + word;
		Network.sendToServer(message);
		while (explanation == null){
			System.out.print("");
		}
		String[] result = explanation.split("###");
		for (int i = 0; i < 3; i ++) {
			if (result[i].startsWith("baidu")) {
				String[] exp = result[i].substring(6).split(";likenumber:");
				WordEntry.setExplanation(i, new Explanation("baidu", exp[0], Integer.parseInt(exp[1])));
			}
			else if (result[i].startsWith("bing")) {
				String[] exp = result[i].substring(5).split(";likenumber:");
				WordEntry.setExplanation(i, new Explanation("bing", exp[0], Integer.parseInt(exp[1])));
			}
			else {
				String[] exp = result[i].substring(7).split(";likenumber:");
				WordEntry.setExplanation(i, new Explanation("youdao", exp[0], Integer.parseInt(exp[1])));
			}
		}
		explanation = null;
		return;
	}
	public static void setExplanation(String explanation) {
		GetExplaination.explanation = explanation;
	}
}
