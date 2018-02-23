
// Each nickname has a different incomming-message queue.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ClientTable {

	private ConcurrentMap<String, ArrayList<Message>> queueTable = new ConcurrentHashMap<String, ArrayList<Message>>();
	private ConcurrentMap<String, Integer> indexOfUser = new ConcurrentHashMap<String, Integer>();

	private static final String SAVE_PATH_MESSAGES_BACKUP = "messaging-system-backup";
	private static final String SAVE_PATH_USERS_INDEX = "index";
	private static final File messagesDirectory = new File(SAVE_PATH_MESSAGES_BACKUP);
	private static final File indexDirectory = new File(SAVE_PATH_USERS_INDEX);

	public ClientTable() {
		messagesDirectory.mkdir();
		indexDirectory.mkdir();
	}

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

	public void saveRegisteredUserandMessages() {

		PrintWriter writer = null;
		for (String user : queueTable.keySet()) {

			try {
				writer = new PrintWriter(new FileOutputStream(SAVE_PATH_MESSAGES_BACKUP + "/" + user + ".txt", false));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}

			writer.println(user);
			ArrayList<Message> m = queueTable.get(user);
			for (Message a : m) {
				writer.println(a.getSender());
				writer.println(a.getText());
			}

			writer.close();
		}
	}

	public void saveUserIndex() {

		PrintWriter writer = null;
		for (String user : indexOfUser.keySet()) {
			try {
				writer = new PrintWriter(new FileOutputStream(SAVE_PATH_USERS_INDEX + "/" + user + ".txt", false));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}
			writer.println(user);
			writer.println(indexOfUser.get(user));
			writer.close();
		}

	}

	public void loadRegisteredUsers() {
		try {
			File f = new File("./" + SAVE_PATH_MESSAGES_BACKUP);
			if (!f.exists() || !f.isDirectory()) {
				return;
			}
			queueTable.clear();
			for (File file : f.listFiles()) {
				if (file.isFile()) {

					BufferedReader br = new BufferedReader(new FileReader(file));
					String user = br.readLine();
					ArrayList<Message> message = new ArrayList<Message>();

					while (true) {
						String sender = br.readLine();
						String text = br.readLine();
						if (user == null || text == null) {
							break;
						}

						message.add(new Message(sender, text));

					}
					br.close();
					queueTable.put(user, message);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadUserIndex() {
		try {
			File f = new File("./" + SAVE_PATH_USERS_INDEX);

			if (!f.exists() || !f.isDirectory()) {

				return;
			}
			indexOfUser.clear();
			for (File file : f.listFiles()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				if (file.isFile()) {
					while (true) {
						String user = br.readLine();
						String index = br.readLine();
						if (user == null || index == null) {
							break;
						}
						indexOfUser.put(user, Integer.parseInt(index));
					}
				}
				br.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
