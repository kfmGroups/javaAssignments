
import java.io.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
	private PrintStream toClient;
	private BufferedReader fromClient;
	private volatile boolean isRunning = true;

	public ServerReceiver(PrintStream toClient, BufferedReader fromClient) {
		this.toClient = toClient;
		this.fromClient = fromClient;
	}

	public void run() {
		ServerCommandArguments userArguments = new ServerCommandArguments();
		try {
			while (isRunning) {

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

				userCommand.execute(userArguments, userArguments.clientName);
				if (!userArguments.keepRunning) {
					break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException ex) {
			ServerCommandArguments.usersLoggedIn.removeUser(userArguments.clientName);

		}

	}

	public void kill() {
		isRunning = false;
	}
}
