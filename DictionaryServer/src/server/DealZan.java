package server;

import java.sql.SQLException;

import sql.SQLManager;

public class DealZan {
	public static boolean ReceiveZan(String user,String word,String which) throws SQLException{
		//Update SQL Zan
		return SQLManager.Addzan(user, word, which);
	}
	public static boolean DeleteZan(String user,String word,String which) throws SQLException{
		return SQLManager.Deletezan(user, word, which);
	}
}
