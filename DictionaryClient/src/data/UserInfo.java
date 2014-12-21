package data;

import java.util.ArrayList;

import network.Network;

/**
 * UserInfo - stores the current user's information
 */
public class UserInfo {
	private static boolean isLogged = false;
	private static String name = null;
	private static String[] onlineUsers = null;
	private static String[] offlineUsers = null;
	private static String[] likedWords = null;
	private static String[] likedWordsSource = null;
	private static ArrayList<WordCard> receivedWordCards = new ArrayList<>();
	
	public UserInfo() { }
	
	public static void setLoginStatus(boolean isLogged) {
		UserInfo.isLogged = isLogged;
	}
	
	public static boolean isLogged() {
		return isLogged;
	}
	
	public static String getName() {
		return name;
	}

	public static String[] getOnlineUsers() {
		return onlineUsers;
	}

	public static void setOnlineUsers(String[] onlineUsers) {
		UserInfo.onlineUsers = onlineUsers;
	}

	public static String[] getOfflineUsers() {
		return offlineUsers;
	}

	public static void setOfflineUsers(String[] offlineUsers) {
		UserInfo.offlineUsers = offlineUsers;
	}
	
	public static ArrayList<WordCard> getReceivedCards() {
		return receivedWordCards;
	}

	public static String[] getLikedWords() {
		return likedWords;
	}

	public static void setLikedWords(String[] likedWords) {
		UserInfo.likedWords = likedWords;
	}

	public static String[] getLikedWordsSource() {
		return likedWordsSource;
	}

	public static void setLikedWordsSource(String[] likedWordsSource) {
		UserInfo.likedWordsSource = likedWordsSource;
	}
	
	public static void login(String name, String password) {
		isLogged = false;
		UserInfo.name = name;
		String message = "Login" + " " + name + " " + password + '\n';
		Network.sendToServer(message);
		receivedWordCards.clear();
	}
	
	public static void logout() {
		if (!isLogged) {
			return;
		}
		String message = "Logout" + " " + name;
		Network.sendToServer(message);
	}
	
	public static void register(String name, String password) {
		isLogged = false;
		UserInfo.name = name;
		String message = "Register" + " " + name + " " + password;
		Network.sendToServer(message);
	}
}
