package com.example.dictionaryandroid;

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
		String data=bundle.getString("data");
		if (data.contains("Register")){
			if (data.equals("Register Success!")){
				Toast.makeText(MainActivity.getContext(), "Register Success!",Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(MainActivity.getContext(), "Register Failed!",Toast.LENGTH_LONG).show();
			}
		}
		else if (data.contains("Login")){
			if (data.equals("Login Success!")){
				//UserManage.setLogined(true);
				//UserInfo.setName(UserManage.name);
				Toast.makeText(MainActivity.getContext(), "Login Success!!",Toast.LENGTH_LONG).show();
			}
			else{
				//UserManage.setLogined(false);
				Toast.makeText(MainActivity.getContext(), "Login Failed!!",Toast.LENGTH_LONG).show();
			}
		}
		// Maybe Like doesn't need to echo --> will be deleted eventually
		else if (data.contains("Zan")){
			if (data.equals("Zan Success!")){
				Toast.makeText(MainActivity.getContext(), "Zan Success!",Toast.LENGTH_LONG).show();
			}
		}
		else if (data.contains("Cancel")){
			if (data.equals("Cancel Success!")){
				Toast.makeText(MainActivity.getContext(), "Cancel Success!",Toast.LENGTH_LONG).show();
			}
		}
		else if (data.contains("Query")) {
			String [] explain=data.split(" ");
			String option=explain[4];
			if (option.charAt(0)=='1'){
				MainActivity.getEditText1().setText(explain[1]);
			}
			if (option.charAt(1)=='1'){
				MainActivity.getEditText2().setText(explain[2]);
			}
			if (option.charAt(2)=='1'){
				MainActivity.getEditText3().setText(explain[3]);
			}
		}
		else if (data.equals("No such words!")){
			Toast.makeText(MainActivity.getContext(), "No such words!",Toast.LENGTH_LONG).show();
		}
		else{
			//Set Explaination
		}
	}
}