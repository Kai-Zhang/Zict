package server;

import java.net.Socket;
import java.util.HashMap;

public class IPMap {
	static HashMap<String,Socket> socketMap=new HashMap<>();
	public static void insert(String IP,Socket socket){
		socketMap.put(IP, socket);
	}
	public static Socket getSocket(String IP){
		return socketMap.get(IP);
	}
}
