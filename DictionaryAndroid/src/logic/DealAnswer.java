package logic;

import com.example.dictionaryandroid.MainActivity;
import com.example.dictionaryandroid.MessageHandler;

import android.os.Bundle;
import android.os.Message;
import data.UserInfo;

public class DealAnswer implements Runnable {
	String messageReceive = null;
	
	public DealAnswer(String message) {
		messageReceive = message;
	}
	
	@Override
	public void run() {
			Bundle bundle=new Bundle();
			bundle.putString("data", messageReceive);
			Message message=new Message();
			message.setData(bundle);
			MainActivity.msghandler.sendMessage(message);
	}

}
