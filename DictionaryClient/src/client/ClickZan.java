package client;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClickZan {
	public void clickZan(String word,String which) throws UnknownHostException, IOException{
		String user=Main.getUser();
		String content="zan";
		content=content+" "+word+" "+which;
		Thread thread=new Thread(new SendToServer(Main.getIP(), content));
		thread.start();
	}
}
