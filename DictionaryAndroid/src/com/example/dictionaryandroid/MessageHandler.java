package com.example.dictionaryandroid;

import java.util.ArrayList;

import logic.ServiceProvider;
import data.Explanation;
import data.UserInfo;
import data.WordEntry;
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
				messageReceive=messageReceive.substring(7);
				System.out.println(messageReceive);
				String[] result = messageReceive.split("###");
				ServiceProvider.setExplanation(messageReceive.substring(7));
				for (int i = 0; i < 3; i ++) {
					if (result[i].startsWith("baidu")) {
						String[] exp = result[i].substring(6).split(";likenumber:");
						WordEntry.setExplanation(i, new Explanation("baidu", exp[0], Integer.parseInt(exp[1])));
					}
					else if (result[i].startsWith("bing")) {
						String[] exp = result[i].substring(5).split(";likenumber:");
						WordEntry.setExplanation(i, new Explanation("bing", exp[0], Integer.parseInt(exp[1])));
					}
					else {
						String[] exp = result[i].substring(7).split(";likenumber:");
						WordEntry.setExplanation(i, new Explanation("youdao", exp[0], Integer.parseInt(exp[1])));
					}
				}
				WordEntry.sortExplanation();
				ArrayList<Explanation> outputList = new ArrayList<Explanation>();
				for (int i = 0; i < 3; i ++) {
					String source = WordEntry.getExplanation(i).getSource();
					if (source.equals("baidu")) {
						if (MainActivity.getCheckBoxBaidu().isSelected()) {
							outputList.add(WordEntry.getExplanation(i));
						}
					}
					else if (source.equals("bing")) {
						if (MainActivity.getCheckBoxBing().isSelected()) {
							outputList.add(WordEntry.getExplanation(i));
						}
					}
					else {
						if (MainActivity.getCheckBoxYoudao().isSelected()) {
							outputList.add(WordEntry.getExplanation(i));
						}
					}
				}
				if (outputList.size() >= 1) {
					MainActivity.getEditText1().setText(outputList.get(0).getExplanation());
				}
				if (outputList.size() >= 2) {
					MainActivity.getEditText2().setText(outputList.get(1).getExplanation());
				}
				else MainActivity.getEditText2().setText("");
				if (outputList.size() == 3) {
					MainActivity.getEditText3().setText(outputList.get(2).getExplanation());
				}
				else MainActivity.getEditText3().setText("");
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