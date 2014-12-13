package client;

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
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		else if (messageReceive.contains("Login")){
			if (messageReceive.equals("Login Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
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
			
		}
		else{
			//Set Explaination
		}
	}

}
