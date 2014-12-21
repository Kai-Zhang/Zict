package logic;

import javax.swing.JOptionPane;

import network.Network;
import data.UserInfo;
import data.WordEntry;

/**
 * ServiceProvider - provides network-relevant service
 */
public class ServiceProvider {
	public static void clickLike(String word, String source) {
		if (!UserInfo.isLogged()) {
			JOptionPane.showConfirmDialog(null, "Please Login First!");
			return;
		}
		String message = "Like" + " " + UserInfo.getName() + " " + word + " " + source;
		Network.sendToServer(message);
	}
	
	public static void cancelLike(String word, String source) {
		if (!UserInfo.isLogged()) {
			JOptionPane.showConfirmDialog(null, "Please Login First!");
			return;
		}
		String message = "Cancel" + " " + UserInfo.getName() + " " + word + " " + source;
		Network.sendToServer(message);
	}
	
	public static void getUserList() {
		Network.sendToServer("User");
	}
	
	public static void getLikedList() {
		String message = "Word" + " " + UserInfo.getName();
		Network.sendToServer(message);
	}
	
	public static void shareWordCard(String toUser, int rating){
		String message = "Share" + " " + toUser + " ###" + WordEntry.getWord()
				+ "###" + WordEntry.getExplanation(rating).getSource()
				+  "###" + WordEntry.getExplanation(rating).getExplanation()
				+ "###" + UserInfo.getName();
		System.out.println(message);
		Network.sendToServer(message);;
	}
	
	public static void getExplanation(String word) {
		WordEntry.setWord(word);
		String queryName = null;
		if (UserInfo.isLogged()) {
			queryName = UserInfo.getName();
		}
		else {
			queryName = "_NULL_";
		}
		String message = "Query" + " " + queryName + " " + word;
		Network.sendToServer(message);
		return;
	}
}
