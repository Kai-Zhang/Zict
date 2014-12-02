package sql;
import java.sql.*;
import java.util.ArrayList;

import server.*;
public class SQLManager {
	static Connection con;
	static String url="jdbc:mysql://localhost:3306/dictionary";
	static String username="root";
	static Statement statement;
	public static boolean Addzan (String user,String word,String which) throws SQLException{
		String query="selct * from zanlist where ID='"+user+"' and word='"+word+"';";
		ResultSet resultSet=statement.executeQuery(query);
		if (resultSet.next()) return false;
		String insert="insert into zanlist values('"+user+"','"+word+"');";
		statement.executeUpdate(insert);
		String update="update zan set "+which+"="+which+"+1"+" where ID='"+user
				+"' and word='"+word+"';";
		statement.execute(update);
		return true;
	}
	public static boolean Deletezan (String user,String word,String which) throws SQLException{
		String query="selct * from zanlist where ID='"+user+"' and word='"+word+"';";
		ResultSet resultSet=statement.executeQuery(query);
		if (!resultSet.next()) return false;
		String delete="delete from zanlist where ID='"+user+"' and word='"+word+"';";
		statement.executeUpdate(delete);
		String update="update zan set "+which+"="+which+"-1"+" where ID='"+user
				+"' and word='"+word+"';";
		statement.execute(update);
		return true;
	}
	public static ArrayList<Answer> LocalAnswer(String word) throws SQLException{
		ArrayList<Answer> ans=new ArrayList<>();
		Answer baidu=new Answer(),bing=new Answer(),youdao=new Answer();
		String query="select * from explains where word='"+word+"';";
		ResultSet resultSet=statement.executeQuery(query);
		if (!resultSet.next()){
			return null;
		}
		baidu.explain=resultSet.getString("baidu");
		bing.explain=resultSet.getString("bing");
		youdao.explain=resultSet.getString("youdao");
		query="select * from zan where word='"+word+"';";
		resultSet=statement.executeQuery(query);
		if (!resultSet.next()){
			return null;
		}
		baidu.zan=resultSet.getInt("baidu");
		bing.zan=resultSet.getInt("bing");
		youdao.zan=resultSet.getInt("youdao");
		baidu.which="baidu";
		bing.which="bing";
		youdao.which="youdao";
		ans.add(baidu);
		ans.add(bing);
		ans.add(youdao);
		return ans;
	}
	public static void InsertWord(String word,ArrayList<Answer> ans) throws SQLException{
		String temp[]=new String[3];
		for (int i=0;i<3;i++){
			Answer temps=ans.get(i);
			temp[i]=temps.explain;
			System.out.println(i+" "+temp[i]);
		}
		String insert="insert into explains values('"+word+"','"+temp[0]+"','"+temp[1]+"','"
				+temp[2]+"');";
		statement.execute(insert);
		insert="insert into zan values('"+word+"',"+0+","+0+","+0+");";
		statement.execute(insert);
	}
	public static boolean AddUser(String user,String passwd,String IP) throws SQLException{
		String insert="insert into users values('"+user+"','"+passwd+"','"+IP+"');";
		return statement.execute(insert);
	}
	public static boolean Login(String user,String passwd,String IP) throws SQLException{
		String query="select * from users where ID='"+user+"' and passwd='"+passwd+"';";
		ResultSet rs=statement.executeQuery(query);
		if (!rs.next()) return false;
		else{
			String update="update users set IP='"+IP+"' where ID='"+user+"';";
			statement.executeUpdate(update);
			return true;
		}
	}
	public static void init() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,"tzzcl","qyh758123");
			statement=con.createStatement();
			System.out.println("成功连接");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close() throws SQLException{
		con.close();
	}
}
