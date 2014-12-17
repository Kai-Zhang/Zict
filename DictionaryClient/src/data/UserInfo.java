package data;

import network.Network;

public class UserInfo {
	private static boolean isLogged = false;
	private static String name = null;
	
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
	
	public static void login(String name, String password) {
		isLogged = false;
		UserInfo.name = name;
		String message = "Login" + " " + name + " " + password + '\n';
		Network.sendToServer(message);
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
