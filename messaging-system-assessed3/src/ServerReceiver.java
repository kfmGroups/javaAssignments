package assignment;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import command.Command;
import command.CommandArguments;
import command.ServerCommandArguments;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
	private PrintStream toClient;
	private BufferedReader fromClient;
	private volatile boolean isRunning = true;
	private String clientName;

	public ServerReceiver(PrintStream toClient, BufferedReader fromClient, LoginInfo loginInfo) {
		this.toClient = toClient;
		this.fromClient = fromClient;
	}

	public void run() {
		try {
			while (isRunning) {

				CommandArguments userArguments = new ServerCommandArguments();
				Command userCommand = Command.readCommand(fromClient, true);
				if (userCommand == null) {
					continue;
				}
				userArguments.args = new String[userCommand.getNumberOfArguments()];
				for (int i = 0; i < userArguments.args.length; i++) {

					userArguments.args[i] = fromClient.readLine();

				}
				userArguments.streamToServerandFromServer = toClient;
				userArguments.keepRunning = true;
				if (userCommand.getCommand().equals("login")) {
					if (!ServerCommandArguments.usersLoggedIn.contains(userArguments.args[0])) {
						clientName = userArguments.args[0];
					} else {
						clientName = "";
					}
				}
				userCommand.execute(userArguments, clientName);
				if (!userArguments.keepRunning) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException ex) {
			ServerCommandArguments.usersLoggedIn.removeUser(clientName);
			CommandArguments.usersLoggedIn.removeUser(clientName);
		}

	}

	public void kill() {
		isRunning = false;
	}
}
