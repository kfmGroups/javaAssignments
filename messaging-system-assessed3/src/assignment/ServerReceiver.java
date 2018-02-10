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
	private LoginInfo loginInfo;

	public ServerReceiver(PrintStream toClient, BufferedReader fromClient, LoginInfo loginInfo) {
		this.toClient = toClient;
		this.fromClient = fromClient;
		this.loginInfo = loginInfo;
	}

	public void run() {

		while (isRunning) {
			
			
			CommandArguments userArguments = new ServerCommandArguments();
			Command userCommand = Command.readCommand(fromClient, true);
			if (userCommand == null) {
				continue;
			}
			userArguments.args = new String[userCommand.getNumberOfArguments()];
			System.out.println(userCommand.getNumberOfArguments());
			for (int i = 0; i < userArguments.args.length; i++) {
				try {
					userArguments.args[i] = fromClient.readLine();
					System.out.println(userArguments.args[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			userArguments.toServer = toClient;
			userArguments.keepRunning = true;
			userCommand.execute(userArguments);
			if (!userArguments.keepRunning) {
				break;
			}

			
		}
	}
	
	public void kill() { 
		isRunning = false; 
		}
}
