package assignment;
// Each nickname has a different incomming-message queue.

import java.util.concurrent.*;

public class ClientTable {

	private ConcurrentMap<String, BlockingQueue<Message>> queueTable = new ConcurrentHashMap<String, BlockingQueue<Message>>();

	// The following overrides any previously existing nickname, and
	// hence the last client to use this nickname will get the messages
	// for that nickname, and the previously existing clients with that
	// nickname won't be able to get messages. Obviously, this is not a
	// good design of a messaging system. So I don't get full marks:

	public void add(String nickname) {
		queueTable.put(nickname, new LinkedBlockingQueue<Message>());
	}

	// Returns null if the nickname is not in the table:
	public BlockingQueue<Message> getQueue(String nickname) {
		return queueTable.get(nickname);
	}

	public void remove(String myClientsName, BlockingQueue<Message> clientsQueue) {
		queueTable.remove(myClientsName, clientsQueue);

	}
}
