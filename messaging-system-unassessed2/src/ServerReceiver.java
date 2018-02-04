import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
	private String myClientsName;
	private BufferedReader myClient;
	private ClientTable clientTable;
	private volatile boolean isRunning = true;


	public ServerReceiver(String n, BufferedReader c, ClientTable t) {
		myClientsName = n;
		myClient = c;
		clientTable = t;
	}

	public void run() {
	
		try {
			while (isRunning) {
				String recipient = myClient.readLine(); // Matches CCCCC in	ClientSender.java
				if (recipient.equals("quit")) {			
					Message msg = new Message("quit", "quit");
					BlockingQueue<Message> clientsQueue = clientTable.getQueue(myClientsName);
					if (clientsQueue != null) {
						clientsQueue.offer(msg);
						clientTable.remove(myClientsName, clientsQueue);
					}						
					kill();
					continue;
				} 
				String text = myClient.readLine(); // Matches DDDDD in  ClientSender.java
				if (recipient != null && text != null) {
						Message msg = new Message(myClientsName, text);
						BlockingQueue<Message> recipientsQueue = clientTable.getQueue(recipient); 																								// ServerSender.java
						if (recipientsQueue != null) {
							recipientsQueue.offer(msg);
						} else {
							Report.error("Message for unexistent client " + recipient + ": " + text);
						}
				} else
					// No point in closing socket. Just give up.
					return;
			}
		} catch (IOException e) {
			Report.error("Something went wrong with the client " + myClientsName + " " + e.getMessage());
			// No point in trying to close sockets. Just give up.
			// We end this thread (we don't do System.exit(1)).
		} 
	}

	public void kill() {
		isRunning = false;
	}
}
