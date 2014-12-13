package client;

import network.SendMessage;

public class UserManage {
	private static boolean logined;
	public static String name;
	public UserManage() { }
	public static void setLogined(boolean logined) {
		UserManage.logined = logined;
	}
	public static void login(String name, String password) {
		logined=false;
		UserManage.name=name;
		String message = "login" + " " + name + " " + password;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
	}
	
	public static void register(String name, String password) {
		String message = "register" + " " + name + " " + password;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
	}
}
