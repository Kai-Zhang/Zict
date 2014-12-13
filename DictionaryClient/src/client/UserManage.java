package client;

import network.SendMessage;

public class UserManage {
	public UserManage() { }
	
	public static void login(String name, String password) {
		String message = "login" + " " + name + " " + password;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
	}
	
	public static void register(String name, String password) {
		String message = "register" + " " + name + " " + password;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
	}
}
