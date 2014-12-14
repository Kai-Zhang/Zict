package com.example.dictionaryandroid;

import java.io.IOException;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton userButton1,userButton2,userButton3;
	ImageButton zanButton1,zanButton2,zanButton3;
	ImageButton homeButton1,homeButton2,homeButton3;
	CheckBox checkBoxBaidu,checkBoxYoudao,checkBoxBing;
	static EditText editText1,editText2,editText3;
	EditText inputText;
	final String IP="";
	Button searchButton;
	private View home,user,zan;
	boolean zanfirst,userfirst;
	public static MessageHandler msghandler=new MessageHandler();
	public static Context getContext(){
		return MainActivity.getContext();
	}
	public static EditText getEditText1() {
		return editText1;
	}
	public static EditText getEditText2() {
		return editText2;
	}
	public static EditText getEditText3() {
		return editText3;
	}
	class HomeClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(home);
		}
		
	}
	class UserClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(user);
			findUser();
		}
		
	}
	class ZanClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(zan);
			findZan();
			
		}
		
	}
	void findZan(){
		if (!zanfirst){
			zanfirst=true;
			zanButton3=(ImageButton) findViewById(R.id.zan3);
			userButton3=(ImageButton) findViewById(R.id.user3);
			homeButton3=(ImageButton) findViewById(R.id.home3);
			userButton3.setOnClickListener(new UserClick());
			homeButton3.setOnClickListener(new HomeClick());
			editText1=(EditText) findViewById(R.id.editText1);
			editText2=(EditText) findViewById(R.id.editText2);
			editText3=(EditText) findViewById(R.id.editText3);
		}
	}
	void findUser(){
		if (!userfirst){
			userfirst=true;
			zanButton2=(ImageButton) findViewById(R.id.zan2);
			userButton2=(ImageButton)findViewById(R.id.user2);
			homeButton2=(ImageButton)findViewById(R.id.home2);
			zanButton2.setOnClickListener(new ZanClick());
			homeButton2.setOnClickListener(new HomeClick());
			
		}
	}
	void init(){
		LayoutInflater inflater=LayoutInflater.from(this);
		home=inflater.inflate(R.layout.home, null);
		user=inflater.inflate(R.layout.user, null);
		zan=inflater.inflate(R.layout.zan, null);
		setContentView(home);
		userButton1=(ImageButton) findViewById(R.id.user1);
		zanButton1=(ImageButton) findViewById(R.id.zan1);
		homeButton1=(ImageButton) findViewById(R.id.home1);
		searchButton=(Button) findViewById(R.id.searchbutton);
		checkBoxBaidu=(CheckBox) findViewById(R.id.checkBoxbaidu);
		checkBoxYoudao=(CheckBox) findViewById(R.id.checkBoxyoudao);
		checkBoxBing=(CheckBox) findViewById(R.id.checkBoxbing);
		inputText=(EditText) findViewById(R.id.inputText);
		homeButton1.setClickable(false);
		zanButton1.setOnClickListener(new ZanClick());
		userButton1.setOnClickListener(new UserClick());
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int option=0;
				if (checkBoxBaidu.isSelected()) option+=100;
				if (checkBoxBing.isSelected()) option+=10;
				if (checkBoxBing.isSelected()) option+=1;
				String st=String.format("%03d", option);
				String word=inputText.getText().toString();
				String message = "query" + " " + word + " " + st;
				new Thread(new SendMessage(IP, message)).start();
			}
		});
		try {
			new Thread(new ReceiveFromServer(IP));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
