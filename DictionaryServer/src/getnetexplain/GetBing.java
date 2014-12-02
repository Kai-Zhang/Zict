package getnetexplain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetBing {
	public static String getBingexplaination(String word) throws Exception{
		URL url=new URL("http://cn.bing.com/dict/search?q="+word);
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(url.openStream()));
		String html="<meta name=\"description\" content=\".*\"/>";
		Pattern p1=Pattern.compile(html);
		String i,temp;
		i=null;
		while ((temp=bufferedReader.readLine())!=null){
			//if (i.contains("explain")) System.out.println(i);	
			i=temp;
			Matcher m1=p1.matcher(i);
			while (m1.find()){
				String temp1=m1.group(0);
				System.out.println(temp);
				temp1=temp1.substring(temp1.indexOf('，')+1);
				temp1=temp1.replaceAll("\"/>", "");
				if (temp1.equals("<meta name=\"description\" content=\"词典"))
						return null;
				System.out.println(temp1);
				return temp1;
			}
			
		}
		return null;
	}
}
