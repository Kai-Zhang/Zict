package logic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.UImain;
import data.Explanation;
import data.UserInfo;
import data.WordCard;
import data.WordEntry;

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
				if (!(WordEntry.getWord() == null)) {
					ServiceProvider.getExplanation(WordEntry.getWord());
				}
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
				if (!(WordEntry.getWord() == null)) {
					ServiceProvider.getExplanation(WordEntry.getWord());
				}
				ServiceProvider.getUserList();
				UImain.mainFrame.loginFrameDispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "用户名/密码错误!");
			}
		}
		else if (context[0].equals("Logout")) {
			UserInfo.setLoginStatus(false);
			//JOptionPane.showMessageDialog(null,"注销成功!");
			UImain.mainFrame.flushUserState();
			UImain.mainFrame.flushLikeStatus();
		}
		else if (context[0].equals("Like")){
			if (context[1].equals("Success!")){
				//JOptionPane.showMessageDialog(null, "Like Success!");
			}
			else{
				JOptionPane.showMessageDialog(null, "您已经赞过了");
				int newLikeNumber = WordEntry.getExplanation(2).getLikeNumber() - 1;
				WordEntry.getExplanation(2).setLikeNumber(newLikeNumber);
				UImain.mainFrame.flushLikeStatus();
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
				WordEntry.setExplanation(0, null);
				WordEntry.setExplanation(1, null);
				WordEntry.setExplanation(2, null);
				UImain.mainFrame.flushLikeStatus();
				UImain.mainFrame.flushResultPage();
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
				UImain.mainFrame.flushLikeStatus();
				UImain.mainFrame.flushResultPage();
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
			UserInfo.setLikedWords(wordStringList);
			UserInfo.setLikedWordsSource(sourceStringList);
			UImain.mainFrame.flushLikedList();
		}
		else if (context[0].equals("Share")) {
			String[] cardParts = messageReceive.split("###");
			// cardParts[1] --> Word
			// cardParts[2] --> Source
			// cardParts[3] --> Explanation
			// cardParts[4] --> Sender
			// Draw Word Card
			UserInfo.getReceivedCards().add(new WordCard(cardParts[1], cardParts[2], cardParts[3], cardParts[4]));
			UImain.mainFrame.flushWordCardArea();
			UImain.mainFrame.wordCardContent=cardParts;
			//UImain.mainFrame.wordCard(cardParts);
			JOptionPane.showMessageDialog(null, "有人给您分享了单词卡！");
		}
		else{
			// Unknown Message
		}
	}

}
