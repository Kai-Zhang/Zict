package getnetexplain;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.*;
import java.security.*;

import javax.net.ssl.*;
import javax.net.*;
public class GetYouDao {
		public static String getYouDaoExplaination(String word) throws Exception{
			URL url=new URL("http://dict.youdao.com/search?q="+word+"&keyfrom=dict.index");
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(url.openStream()));
			String html="<div class=\"trans-container\">.*</div>";
			Pattern p1=Pattern.compile(html);
			String i,temp;
			i=null;
			while ((temp=bufferedReader.readLine())!=null){
				//if (i.contains("explain")) System.out.println(i);	
				i+=temp;
				Matcher m1=p1.matcher(i);
				while (m1.find()){
					String temp1=m1.group(0);
					System.out.println(temp1);
					temp1=temp1.replaceAll("<[^>]+>", "");
					while (temp1.charAt(0)==' ') temp1=temp1.substring(1);
					System.out.println(temp1);
					return temp1;
				}
				
			}
			return null;
		}
}
