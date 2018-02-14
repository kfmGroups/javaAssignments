package assignment;

import java.io.PrintStream;
import java.util.ArrayList;

public class ServerSender {
	private ArrayList<Message> clientQueue;
	private PrintStream client;
	private ArrayList<Message> q;

	public ServerSender(PrintStream c) {
		client = c;   
	   
	  }


}
