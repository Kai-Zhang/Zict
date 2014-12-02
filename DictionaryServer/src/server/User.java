package server;

import java.sql.SQLException;

import sql.SQLManager;

public class User {
	public static boolean Adduser(String user,String passwd,String IP) throws SQLException{
		return SQLManager.AddUser(user, passwd, IP);
	}
	public static boolean Login(String user,String passwd,String IP) throws SQLException{
		return SQLManager.Login(user, passwd, IP);
	}
}
