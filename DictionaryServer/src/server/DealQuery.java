package server;

import java.sql.SQLException;
import java.util.ArrayList;

public class DealQuery implements Runnable{
	String nowString;
	String IP;
	String content;
	public DealQuery(String nowString, String IP) {
		this.nowString=nowString;
		this.IP=IP;
	}
	public void setNowString(String nowString,String IP) {
		this.nowString = nowString;
		content=null;
	}
	@Override
	public void run() {
			String []temp=nowString.split(" ");
			if (temp[0].contains("register")){
				String user=temp[1];
				String passwd=temp[2];
				String IP=temp[3];
				try {
					boolean state=User.Adduser(user, passwd,IP);
					if (state){
						content="Register Success!";
					}
					else content="Register Failed";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (temp[0].contains("login")){
				String user=temp[1];
				String passwd=temp[2];
				String IP=temp[3];
				try {
					boolean state=User.Login(user, passwd,IP);
					if (state){
						content="Login Success!";
					}
					else content="Login Failed";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Broadcast
				
			}
			if (temp[0].contains("query")){
				String word=temp[1];
				String st=temp[2];
				try {
					Query query=new Query();
					ArrayList<Answer> answers=query.getExplaination(word, st);
					
					if (answers==null){
						content="No such words!";
					}
					else{
						content="answer:\r\n";
					for (Answer i:answers){
						content=content+i.which+":"+i.explain+"\r\n";
					}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (temp[0].contains("zan")){
				String user=temp[1];
				String word=temp[2];
				String which=temp[3];
				try {
					boolean state=DealZan.ReceiveZan(user,word, which);
					if (state){
						content="Zan Success!";
					}
					else{
						content="Zan Failed";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (temp[0].contains("cancel")){
				String user=temp[1];
				String word=temp[2];
				String which=temp[3];
				try {
					boolean state=DealZan.DeleteZan(user, word, which);
					if (state){
						content="Cancel Success!";
					}
					else{
						content="Cancel Failed";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Thread thread=new Thread(new SendToClient(content, IP));
			thread.start();
	}
	
}
