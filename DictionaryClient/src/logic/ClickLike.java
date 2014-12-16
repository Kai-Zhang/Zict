package logic;

import javax.swing.JOptionPane;

import data.UserInfo;
import network.Network;

public class ClickLike {
	public ClickLike() { }
	
	public static void like(String word,String which) {
		if (!UserInfo.isLogged()) {
			JOptionPane.showConfirmDialog(null, "Please Login First!");
			return;
		}
		String message = "like" + " " + UserInfo.getName() + " " + word + " " + which;
		Network.sendToServer(message);
	}
}
