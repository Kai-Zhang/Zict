package com.example.dictionaryandroid;

import logic.ServiceProvider;
import data.UserInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;


public class MessageHandler extends Handler {
	@Override
	public void handleMessage(Message msg){
		super.handleMessage(msg);
		Bundle bundle=msg.getData();
		String messageReceive=bundle.getString("data");
		String[] context = messageReceive.split(" ");
		if (context[0].equals("Register")){
			if (context[1].equals("Success!")){
				Toast.makeText(MainActivity.getContext(), "Register Success!",
						Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(MainActivity.getContext(), "Register Failed!",
						Toast.LENGTH_LONG).show();
			}
		}
		else if (context[0].equals("Login")){
			if (context[1].equals("Success!")){
				UserInfo.setLoginStatus(true);
				Toast.makeText(MainActivity.getContext(), "Login Success!",
						Toast.LENGTH_LONG).show();
			}
			else{
				UserInfo.setLoginStatus(false);
				Toast.makeText(MainActivity.getContext(), "Login Failed!",
						Toast.LENGTH_LONG).show();
			}
		}
		else if (context[0].equals("Logout")) {
			UserInfo.setLoginStatus(false);
			Toast.makeText(MainActivity.getContext(), "Logout Success!",
					Toast.LENGTH_LONG).show();
		}
		else if (context[0].equals("Like")){
			if (context[1].equals("Success!")){
				Toast.makeText(MainActivity.getContext(), "Like Success!",
						Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(MainActivity.getContext(), "Like Failed!",
						Toast.LENGTH_LONG).show();
			}
		}
		else if (context[0].equals("Cancel")){
			if (context[1].equals("Success!")){
				Toast.makeText(MainActivity.getContext(), "Cancel Success!",
						Toast.LENGTH_LONG).show();
			}
			else{
			}
		}
		else if (context[0].equals("Answer")) {
			if (context[1].equals("NoSuchWord")) {
				Toast.makeText(MainActivity.getContext(), "No such words!",
						Toast.LENGTH_LONG).show();
				MainActivity.getEditText1().setText("");
				MainActivity.getEditText2().setText("");
				MainActivity.getEditText3().setText("");
			}
			else {
				ServiceProvider.setExplanation(messageReceive.substring(7));
			}
		}
		else if (context[0].equals("Share")) {
			String[] cardParts = messageReceive.split("###");
			// cardParts[1] --> Word
			// cardParts[2] --> Explanation
			// Draw Word Card
		}
		else if (context[0].equals("Please")){
			Toast.makeText(MainActivity.getContext(), "Please Login First!",
					Toast.LENGTH_LONG).show();
		}
	}
}