package assignment;
// Each nickname has a different incomming-message queue.

import java.util.ArrayList;
import java.util.concurrent.*;

public class ClientTable {

	private ConcurrentMap<String, ArrayList<Message>> queueTable = new ConcurrentHashMap<String, ArrayList<Message>>();
	private ConcurrentMap<String, Integer> indexOfUser = new ConcurrentHashMap<String, Integer>();

	// The following overrides any previously existing nickname, and
	// hence the last client to use this nickname will get the messages
	// for that nickname, and the previously existing clients with that
	// nickname won't be able to get messages. Obviously, this is not a
	// good design of a messaging system. So I don't get full marks:

	public void addUser(String nickname) {
		queueTable.put(nickname, new ArrayList<Message>());
		indexOfUser.put(nickname, -1);
	}

	public void addUserMessage(String nickname, Message message) {
		queueTable.get(nickname).add(message);
		int value = indexOfUser.get(nickname);
		indexOfUser.put(nickname, value + 1);
	}

	// Returns null if the nickname is not in the table:
	public ArrayList<Message> getQueue(String nickname) {
		return queueTable.get(nickname);
	}

	public void remove(String myClientsName, ArrayList<Message> clientsQueue) {
		queueTable.remove(myClientsName, clientsQueue);
	}

	public int getIndex(String clientName) {
		return indexOfUser.get(clientName);
	}

	public int getPreviousMessageIndex(String name) {
		int value = indexOfUser.get(name) - 1;
		if (value < 0) {
			return -1;
		} else {
			indexOfUser.put(name, value);
		}
		return indexOfUser.get(name);
	}

	public int getNextMessageIndex(String name) {
		int value = (indexOfUser.get(name)) + 1;
		if (value >= getQueue(name).size()) {
			return getQueue(name).size();
		} else {
			indexOfUser.put(name, value);
		}
		return indexOfUser.get(name);
	}

	public void removeUserMessageIndex(String name) {
		int value = getIndex(name);
		getQueue(name).remove(value);
		if (value >= getQueue(name).size()) {
			indexOfUser.put(name, value - 1);
		}

	}

}
