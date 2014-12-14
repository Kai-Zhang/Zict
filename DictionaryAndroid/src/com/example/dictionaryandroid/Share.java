package com.example.dictionaryandroid;


public class Share {
	public Share(){}
	public static void ClickShare(String touser,String explain){
		String message="share"+" "+touser+" "+explain;
		new Thread(new SendMessage(SendMessage.SERVER_IP, message)).start();
	}
}
