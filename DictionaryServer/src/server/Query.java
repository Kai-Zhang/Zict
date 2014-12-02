package server;
import getnetexplain.GetBaidu;
import sql.*;
import getnetexplain.GetBing;
import getnetexplain.GetYouDao;

import java.sql.*;
import java.util.ArrayList;
public class Query {
	Answer baiduAnswer=new Answer();
	Answer bingAnswer=new Answer();
	Answer youdaoAnswer=new Answer();
	public ArrayList<Answer> Sort(ArrayList<Answer> unsorted,String st){
		for (int i=0;i<3;i++){
			if (st.charAt(i)=='0') unsorted.remove(i);
		}
		for (int i=0;i<unsorted.size();i++)
			for (int j=i+1;j<unsorted.size();j++)
			{
				Answer t1=unsorted.get(i);
				Answer t2=unsorted.get(j);
				if (t1.zan<t2.zan) {
					Answer temp=t1;t1=t2;t2=temp;
				}
			}
		return unsorted;
	}
	public ArrayList<Answer> getLocalResult(String word) throws SQLException{
		return SQLManager.LocalAnswer(word);
	}
	public ArrayList<Answer> getNetResult(String word) throws Exception{
		baiduAnswer.which="baidu";
		baiduAnswer.explain=GetBaidu.getBaiduExplaination(word);
		baiduAnswer.zan=0;
		bingAnswer.which="bing";
		bingAnswer.explain=GetBing.getBingexplaination(word);
		bingAnswer.zan=0;
		youdaoAnswer.which="youdao";
		youdaoAnswer.explain=GetYouDao.getYouDaoExplaination(word);
		youdaoAnswer.zan=0;
		ArrayList<Answer> now=new ArrayList<>();
		now.add(baiduAnswer);
		now.add(bingAnswer);
		now.add(youdaoAnswer);
		SQLManager.InsertWord(word, now);
		//Do Network Query 
		//Caching in Local Result
		return now;
	} 
	public  ArrayList<Answer> getExplaination(String word,String st) throws Exception{
		ArrayList<Answer> ans1=getLocalResult(word);
		if (ans1!=null) return Sort(ans1,st);
		else return Sort(getNetResult(word),st);
	}
}
