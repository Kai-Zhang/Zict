package server;

import java.sql.SQLException;
import java.util.ArrayList;

import sql.SQLManager;

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
			if (temp[0].equals("Register")){
				String user=temp[1];
				String passwd=temp[2];
				try {
					boolean state=User.Adduser(user, passwd,IP);
					System.out.println(state);
					if (state){
						content="Register Success!";
					}
					else content="Register Failed";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (temp[0].equals("Login")){
				String user=temp[1];
				String passwd=temp[2];
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
			if (temp[0].equals("Query")){
				String word=temp[1];
				try {
					Query query=new Query();
					ArrayList<Answer> answers=query.getExplaination(word);
					if (answers==null){
						content="Answer NoSuchWord";
					}
					else{
						content="Answer ";
						
					for (Answer i:answers){
						content=content+i.which+":"+i.explain+";likenumber:"+i.zan+"###";
					}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (temp[0].equals("Like")){
				String user=temp[1];
				String word=temp[2];
				String which=temp[3];
				try {
					boolean state=DealZan.ReceiveZan(user,word, which);
					if (state){
						content="Like Success!";
					}
					else{
						content="Like Failed";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (temp[0].equals("Cancel")){
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
			if (temp[0].equals("Share")){
				String touser=temp[1];
				try {
					String userIP=SQLManager.QueryIP(touser);
					new Thread(new SendToClient(nowString,userIP)).start();
					return ;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (temp[0].equals("Logout")){
				try {
					SQLManager.Logout(temp[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				content="Logout";
			}
			if (temp[0].equals("User")){
				try {
					content="User "+SQLManager.QueryUser();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			new Thread(new SendToClient(content,IP)).start();
	}
	
}
