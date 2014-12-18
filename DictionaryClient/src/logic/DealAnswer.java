package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.CheckOnlineList;
import client.UImain;
import data.UserInfo;

public class DealAnswer implements Runnable {
	String messageReceive = null;
	
	public DealAnswer(String message) {
		messageReceive = message;
	}
	
	@Override
	public void run() {
		String[] context = messageReceive.split(" ");
		if (context[0].equals("Register")){
			if (context[1].equals("Success!")){
				JOptionPane.showMessageDialog(null, "注册成功!");
				UserInfo.setLoginStatus(true);
				UImain.mainFrame.flushUserState();
			}
			else{
				JOptionPane.showMessageDialog(null, "该用户名已被占用");
			}
		}
		else if (context[0].equals("Login")){
			if (context[1].equals("Success!")){
				JOptionPane.showMessageDialog(null, "登陆成功!");
				UserInfo.setLoginStatus(true);
				UImain.mainFrame.flushUserState();
			}
			else{
				JOptionPane.showMessageDialog(null, "用户名/密码错误!");
				UImain.mainFrame.flushUserState();
			}
		}
		else if (context[0].equals("Logout")) {
			UserInfo.setLoginStatus(false);
			JOptionPane.showMessageDialog(null,"注销成功!");
			UImain.mainFrame.flushUserState();
		}
		else if (context[0].equals("Like")){
			if (context[1].equals("Success!")){
				//JOptionPane.showMessageDialog(null, "Like Success!");
			}
			else{
				JOptionPane.showMessageDialog(null, "您已经赞过了");
			}
		}
		else if (context[0].equals("Cancel")){
			if (context[1].equals("Success!")){
				//JOptionPane.showMessageDialog(null, "Cancel Success!");
			}
			else{
			}
		}
		else if (context[0].equals("Answer")) {
			if (context[1].equals("NoSuchWord")) {
				//JOptionPane.showMessageDialog(null, "No such word!");
				ServiceProvider.explanation = "Null";
			}
			else {
				ServiceProvider.explanation = messageReceive.substring(7);
			}
		}
		else if (context[0].equals("User")) {
			String[] userList = messageReceive.substring(5).split("###");
			UserInfo.setOnlineUsers(userList[0].split(" "));
			if (userList.length > 1) {
				UserInfo.setOfflineUsers(userList[1].split(" "));
			}
			else {
				UserInfo.setOfflineUsers(null);
			}
			UImain.mainFrame.flushUserList();
		}
		else if (context[0].equals("Share")) {
			String[] cardParts = messageReceive.split("###");
			JOptionPane.showMessageDialog(null, "有人给您分享了单词卡！");
			//System.out.println(cardParts[1]);
			//System.out.println(cardParts[2]);
			// cardParts[1] --> Word
			// cardParts[2] --> Explanation
			// Draw Word Card
			//System.out.print(cardParts[1]+"\n"+cardParts[2]);
			UImain.mainFrame.wordCard(cardParts);
		}
		else{
			// Unknown Message
		}
	}

}
