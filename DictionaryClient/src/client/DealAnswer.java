package client;

import javax.swing.JOptionPane;

public class DealAnswer implements Runnable {
	String messageReceive = null;
	
	public DealAnswer(String message) {
		messageReceive = message;
	}
	
	// TODO: fill the message handler
	@Override
	public void run() {
		if (messageReceive.contains("Register")){
			if (messageReceive.equals("Register Success!")){
				JOptionPane.showConfirmDialog(null, "Register Success!");
			}
			else{
				JOptionPane.showConfirmDialog(null, "Register Failed!");
			}
		}
		else if (messageReceive.contains("Login")){
			if (messageReceive.equals("Login Success!")){
				JOptionPane.showConfirmDialog(null, "Login Success!");
			}
			else{
				JOptionPane.showConfirmDialog(null, "Login Failed!");
			}
		}
		// Maybe Like doesn't need to echo --> will be deleted eventually
		else if (messageReceive.contains("Zan")){
			if (messageReceive.equals("Zan Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		else if (messageReceive.contains("Cancel")){
			if (messageReceive.equals("Cancel Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		else if (messageReceive.contains("Query")) {
			GetExplaination.explanation = messageReceive.substring(6);
		}
		else if (messageReceive.equals("No such words!")){
			JOptionPane.showConfirmDialog(null, "No such words!");
		}
		else{
			//Set Explaination
		}
	}

}
