package getnetexplain;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.*;
import java.security.*;

import javax.net.ssl.*;
import javax.net.*;
import javax.swing.InputMap;
import javax.swing.text.AbstractDocument.BranchElement;

import org.xml.sax.InputSource;
public class GetBaidu {
	public static String getBaiduExplaination(String word) throws Exception{
		URL url=new URL("http://dict.baidu.com/s?wd="+word+"&tn=dict");
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(url.openStream()));
		String html="explain: \".*\"";
		Pattern p1=Pattern.compile(html);
		String i;
		while ((i=bufferedReader.readLine())!=null){
			//if (i.contains("explain")) System.out.println(i);	
			Matcher m1=p1.matcher(i);
			while (m1.find()){
				String temp=m1.group(0);
				String explain[]=temp.split("\"");
				if (explain.length<=1) return null;
				explain[1]=explain[1].replaceAll("<[^>]+>", "");
				return explain[1];
			}
			
		}
		return null;
	}
}
