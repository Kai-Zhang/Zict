package com.example.dictionaryandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import logic.ServiceProvider;
import data.Explanation;
import data.UserInfo;
import data.WordEntry;
import network.Network;
import android.R.anim;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.style.BackgroundColorSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton userButton1,userButton2,userButton3;
	ImageButton zanButton1,zanButton2,zanButton3;
	ImageButton homeButton1,homeButton2,homeButton3;
	ImageButton clickzan1,clickzan2,clickzan3;
	CheckBox checkBoxBaidu,checkBoxYoudao,checkBoxBing;
	static TextView editText1,editText2,editText3;
	static Context context;
	Button loginButton,registerButton,returnButton,userlookButton;
	static ListView OnlineUser,OfflineUser;
	EditText inputid,inputpasswd;
	EditText inputText;
	static String IP="";
	Button searchButton;
	private View home,user,zan,userlist;
	boolean zanfirst,userfirst,userlistfirst;
	static boolean baiduSelect,bingSelect,youdaoSelect;
	public static MessageHandler msghandler=new MessageHandler();
	public static Context getContext(){
		return context;
	}
	public static void flushExplaination(){
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		if (WordEntry.getExplanation(0) == null) {
			
			return;
		}
		else{
			ArrayList<Explanation> outputList = new ArrayList<Explanation>();
			for (int i = 0; i < 3; i ++) {
				String source = WordEntry.getExplanation(i).getSource();
				if (source.equals("baidu")) {
					if (baiduSelect) {
						outputList.add(WordEntry.getExplanation(i));
					}
				}
				else if (source.equals("bing")) {
					if (bingSelect) {
						outputList.add(WordEntry.getExplanation(i));
					}
				}
				else {
					if (youdaoSelect) {
						outputList.add(WordEntry.getExplanation(i));
					}
				}
			}
			if (outputList.size()>=1){
				editText1.setText(outputList.get(0).getExplanation());
			}
			if (outputList.size()>=2){
				editText2.setText(outputList.get(1).getExplanation());
			}
			if (outputList.size()>=3){
				editText3.setText(outputList.get(2).getExplanation());
			}
		}
	}
	public static void flushUserList() {
		// TODO Auto-generated method stub
		OnlineUser.setAdapter(new ArrayAdapter<String>(MainActivity.getContext(),android.R.layout.simple_list_item_1,UserInfo.getOnlineUsers()));
		OfflineUser.setAdapter(new ArrayAdapter<String>(MainActivity.getContext(),android.R.layout.simple_list_item_1,UserInfo.getOfflineUsers()));
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
	@SuppressLint("ShowToast")
	void findZan(){
		if (!zanfirst){
			zanfirst=true;
			zanButton3=(ImageButton) findViewById(R.id.zan3);
			userButton3=(ImageButton) findViewById(R.id.user3);
			homeButton3=(ImageButton) findViewById(R.id.home3);
			userButton3.setOnClickListener(new UserClick());
			homeButton3.setOnClickListener(new HomeClick());
			editText1=(TextView) findViewById(R.id.editText1);
			editText2=(TextView) findViewById(R.id.editText2);
			editText3=(TextView) findViewById(R.id.editText3);
			editText1.setFocusable(false);
			editText2.setFocusable(false);
			editText3.setFocusable(false);
			zanButton3.setClickable(false);
			homeButton3.setOnClickListener(new HomeClick());
			userButton3.setOnClickListener(new UserClick());
			clickzan1=(ImageButton) findViewById(R.id.clickzan1);
			clickzan1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (UserInfo.isLogged())
						ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(0).getSource());
					else{
						Toast.makeText(MainActivity.getContext(), "Please Login First", Toast.LENGTH_LONG);
					}
				}
			});
			clickzan2=(ImageButton) findViewById(R.id.clickzan2);
			clickzan2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (UserInfo.isLogged())
						ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(1).getSource());
					else{
						Toast.makeText(MainActivity.getContext(), "Please Login First", Toast.LENGTH_LONG);
					}
				}
			});
			clickzan3=(ImageButton) findViewById(R.id.clickzan3);
			clickzan3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					if (UserInfo.isLogged())
						ServiceProvider.clickLike(WordEntry.getWord(), WordEntry.getExplanation(2).getSource());
					else{
						Toast.makeText(MainActivity.getContext(), "Please Login First", Toast.LENGTH_LONG);
					}
				}
			});
		}
	}
	void findUserList(){
		if (!userlistfirst){
			userlistfirst=true;
			OnlineUser=(ListView) findViewById(R.id.OnlineUser);
			OfflineUser=(ListView) findViewById(R.id.OfflineUser);
			returnButton=(Button) findViewById(R.id.returnButton);
			ServiceProvider.getUserList();
			returnButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					setContentView(R.layout.user);
				}
			});
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
			inputid=(EditText) findViewById(R.id.inputid);
			inputpasswd=(EditText) findViewById(R.id.inputpasswd);
			loginButton=(Button) findViewById(R.id.loginbutton);
			loginButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String userID=inputid.getText().toString();
					String password=inputpasswd.getText().toString();
					if (userID==null||password==null) return ;
					UserInfo.login(userID, password);
				}
			});
			registerButton=(Button) findViewById(R.id.registerbutton);
			registerButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String userID=inputid.getText().toString();
					String password=inputpasswd.getText().toString();
					if (userID==null||password==null) return ;
					if (userID.matches("\\w{1,16}") && password.matches("\\w{6,}")) {
						UserInfo.register(userID, password);
					}
				}
			});
			userlookButton=(Button) findViewById(R.id.userlookbutton);
			userlookButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					setContentView(userlist);
					findUserList();
				}
			});
		}
	}
	void init() throws UnknownHostException, IOException{
		/*File configFile = new File("config.txt");
		Scanner configScanner = new Scanner(configFile);
		String serverIP = configScanner.nextLine();*/
		context=getBaseContext();
		String serverIP="10.0.0.3";
		System.out.println(serverIP);
		System.out.println("Before Connect");
		Network.connectToServer(serverIP);
		System.out.println("Before receive");
		LayoutInflater inflater=LayoutInflater.from(this);
		home=inflater.inflate(R.layout.home, null);
		user=inflater.inflate(R.layout.user, null);
		zan=inflater.inflate(R.layout.zan, null);
		userlist=inflater.inflate(R.layout.userlist,null);
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
				baiduSelect=checkBoxBaidu.isChecked();
				bingSelect=checkBoxBing.isChecked();
				youdaoSelect=checkBoxYoudao.isChecked();
				System.out.println("baidu:"+baiduSelect);
				System.out.println("bing:"+bingSelect);
				System.out.println("youdao:"+youdaoSelect);
				setContentView(zan);
				findZan();
				String currentWord =inputText.getText().toString();
				ServiceProvider.getExplanation(currentWord);
				
			}
		});
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
