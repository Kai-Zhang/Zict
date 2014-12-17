package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
				JOptionPane.showConfirmDialog(null, "Register Success!");
				UserInfo.setLoginStatus(true);
				UImain.mainFrame.flushUserState();
			}
			else{
				JOptionPane.showConfirmDialog(null, "Register Failed!");
			}
		}
		else if (context[0].equals("Login")){
			if (context[1].equals("Success!")){
				JOptionPane.showConfirmDialog(null, "Login Success!");
				UserInfo.setLoginStatus(true);
				UImain.mainFrame.flushUserState();
			}
			else{
				JOptionPane.showConfirmDialog(null, "Login Failed!");
				UImain.mainFrame.flushUserState();
			}
		}
		else if (context[0].equals("Logout")) {
			JOptionPane.showConfirmDialog(null, "Logout Success!");
			UserInfo.setLoginStatus(false);
		}
		else if (context[0].equals("Like")){
			if (context[1].equals("Success!")){
				JOptionPane.showConfirmDialog(null, "Like Success!");
			}
			else{
				JOptionPane.showConfirmDialog(null, "Like Failed!");
			}
		}
		else if (context[0].equals("Cancel")){
			if (context[1].equals("Success!")){
				JOptionPane.showConfirmDialog(null, "Cancel Success!");
			}
			else{
			}
		}
		else if (context[0].equals("Answer")) {
			if (context[1].equals("NoSuchWord")) {
				JOptionPane.showConfirmDialog(null, "No such word!");
				ServiceProvider.explanation = "Null";
			}
			else {
				ServiceProvider.explanation = messageReceive.substring(7);
			}
		}
		else if (context[0].equals("Share")) {
			String[] cardParts = messageReceive.split("###");
			// cardParts[1] --> Word
			// cardParts[2] --> Explanation
			// Draw Word Card
			System.out.print(cardParts[1]+"/n"+cardParts[2]);
			UImain.mainFrame.wordCard(cardParts);
		}
		else{
			//Set Explaination
		}
	}

}
