package logic;

import android.os.Bundle;
import android.os.Message;
import android.text.style.SuperscriptSpan;
import network.Network;
import data.Explanation;
import data.UserInfo;
import data.WordEntry;

public class ServiceProvider {
	static String explanation = null;
	
	public static void clickLike(String word, String source) {
		if (!UserInfo.isLogged()) {
			Bundle bundle=new Bundle();
			bundle.putString("data", "Please Login First!");
			Message message=new Message();
			message.setData(bundle);
			//JOptionPane.showConfirmDialog(null, "Please Login First!");
			return;
		}
		String message = "Like" + " " + UserInfo.getName() + " " + word + " " + source;
		Network.sendToServer(message);
	}
	
	public static void cancelLike(String word, String source) {
		if (!UserInfo.isLogged()) {
			Bundle bundle=new Bundle();
			bundle.putString("data", "Please Login First!");
			Message message=new Message();
			message.setData(bundle);
			//JOptionPane.showConfirmDialog(null, "Please Login First!");
			return;
		}
		String message = "Cancel" + " " + UserInfo.getName() + " " + word + " " + source;
		Network.sendToServer(message);
	}
	
	public static void shareWordCard(String toUser,int rating){
		String message = "Share" + " " + toUser + " ###" + WordEntry.getWord() + "###" + WordEntry.getExplanation(rating).getExplanation();
		Network.sendToServer(message);;
	}
	public static void setExplanation(String explanation) {
		ServiceProvider.explanation = explanation;
	}
	public static void getExplanation(String word) {
		explanation = null;
		WordEntry.setWord(word);
		String message = "Query" + " " + word;
		Network.sendToServer(message);
		return;
	}
}
