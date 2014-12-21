package com.example.dictionaryandroid;

import java.util.ArrayList;

import org.apache.http.impl.entity.StrictContentLengthStrategy;

import logic.ServiceProvider;
import data.Explanation;
import data.UserInfo;
import data.WordEntry;
import android.R.integer;
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
				UserInfo.setLoginStatus(true);
				if (!(WordEntry.getWord()==null)) 
					ServiceProvider.getExplanation(WordEntry.getWord());
				Toast.makeText(MainActivity.getContext(), "注册成功!",
						Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(MainActivity.getContext(), "该用户名已被占用!",
						Toast.LENGTH_LONG).show();
			}
		}
		else if (context[0].equals("Login")){
			if (context[1].equals("Success!")){
				UserInfo.setLoginStatus(true);
				Toast.makeText(MainActivity.getContext(), "登陆成功!",
						Toast.LENGTH_LONG).show();
			}
			else{
				UserInfo.setLoginStatus(false);
				Toast.makeText(MainActivity.getContext(), "用户名/密码错误!",
						Toast.LENGTH_LONG).show();
			}
		}
		else if (context[0].equals("Logout")) {
			UserInfo.setLoginStatus(false);
			Toast.makeText(MainActivity.getContext(), "注销成功!",
					Toast.LENGTH_LONG).show();
		}
		else if (context[0].equals("Like")){
			if (context[1].equals("Success!")){
				Toast.makeText(MainActivity.getContext(), "点赞成功!",
						Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(MainActivity.getContext(), "您已经赞过了!",
						Toast.LENGTH_LONG).show();
			}
		}
		else if (context[0].equals("Cancel")){
			if (context[1].equals("Success!")){
				Toast.makeText(MainActivity.getContext(), "取消赞成功!",
						Toast.LENGTH_LONG).show();
			}
			else{
			}
		}
		else if (context[0].equals("Answer")) {
			if (context[1].equals("NoSuchWord")) {
				WordEntry.setExplanation(0, null);
				WordEntry.setExplanation(1, null);
				WordEntry.setExplanation(2, null);
				MainActivity.flushExplaination();
			}
			else {
				String explanation = messageReceive.substring(7);
				String[] result = explanation.split("###");
				for (int i = 0; i < 3; i ++) {
					if (result[i].startsWith("baidu")) {
						String[] exp = result[i].substring(6).split(";likenumber:");
						String[] like = exp[1].split(";liked:");
						WordEntry.setExplanation(i, new Explanation("baidu", exp[0], Integer.parseInt(like[0])));
						if (like[1].startsWith("true")) {
							WordEntry.getExplanation(i).setLiked(true);
						} else {
							WordEntry.getExplanation(i).setLiked(false);
						}
					}
					else if (result[i].startsWith("bing")) {
						String[] exp = result[i].substring(5).split(";likenumber:");
						String[] like = exp[1].split(";liked:");
						WordEntry.setExplanation(i, new Explanation("bing", exp[0], Integer.parseInt(like[0])));
						if (like[1].startsWith("true")) {
							WordEntry.getExplanation(i).setLiked(true);
						} else {
							WordEntry.getExplanation(i).setLiked(false);
						}
					}
					else {
						String[] exp = result[i].substring(7).split(";likenumber:");
						String[] like = exp[1].split(";liked:");
						WordEntry.setExplanation(i, new Explanation("youdao", exp[0], Integer.parseInt(like[0])));
						if (like[1].startsWith("true")) {
							WordEntry.getExplanation(i).setLiked(true);
						} else {
							WordEntry.getExplanation(i).setLiked(false);
						}
					}
				}
				WordEntry.sortExplanation();
				MainActivity.flushExplaination();
			}
		}
		else if (context[0].equals("User")) {
			String[] userList = messageReceive.substring(5).split("###");
			String []tempuser=userList[0].split(" ");
			String []ans=new String[tempuser.length+1];
			ans[0]="Online Users:";
			for (int i=1;i<ans.length;i++) ans[i]=tempuser[i-1];
			UserInfo.setOnlineUsers(ans);
			if (userList.length > 1) {
				tempuser=userList[1].split(" ");
				ans=new String[tempuser.length+1];
				ans[0]="Offline Users:";
				for (int i=1;i<ans.length;i++) ans[i]=tempuser[i-1];
				UserInfo.setOfflineUsers(ans);
			}
			else {
				UserInfo.setOfflineUsers(null);
			}
			MainActivity.flushUserList();
		}
		else if (context[0].equals("Word")) {
			ArrayList<String> wordList = new ArrayList<String>();
			ArrayList<String> sourceList = new ArrayList<String>();
			for (int i = 1; i < context.length; i ++) {
				String[] parts = context[i].split(":");
				wordList.add(parts[0]);
				sourceList.add(parts[1]);
			}
			String[] wordStringList = new String[wordList.size()];
			String[] sourceStringList = new String[wordList.size()];
			for (int i = 0; i < wordList.size(); i ++) {
				wordStringList[i] = wordList.get(i);
				sourceStringList[i] = sourceList.get(i);
			}
			UserInfo.setOnlineUsers(wordStringList);
			UserInfo.setOfflineUsers(sourceStringList);
			//Set Online Users and Offline Users
			//UImain.mainFrame.flushUserList();
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