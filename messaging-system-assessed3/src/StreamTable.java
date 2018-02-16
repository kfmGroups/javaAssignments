package assignment;

import java.io.PrintStream;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StreamTable {

	private ConcurrentMap<String, PrintStream> userStream = new ConcurrentHashMap<String, PrintStream>();

	public PrintStream getUserStream(String clientName) {
		if (userStream.containsKey(clientName)) {
			return userStream.get(clientName);
		} else {
			return null;
		}

	}
	
	public void addUserStream(String clientname, PrintStream clientStream){
		userStream.put(clientname, clientStream);
	}
	
	public void removeUserStream(String clientName){
		userStream.remove(clientName);
	}
}
