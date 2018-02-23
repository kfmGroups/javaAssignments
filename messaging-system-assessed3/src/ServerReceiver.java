
import java.net.*;
import java.io.*;
import java.util.concurrent.*;


// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
	private PrintStream toClient;
	private BufferedReader fromClient;
	private volatile boolean isRunning = true;
	private String clientName;
	private String client;

	public ServerReceiver(PrintStream toClient, BufferedReader fromClient) {
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

					if ((ServerCommandArguments.loginInfo.isPasswordCorrect(userArguments.args[0],
							Encryptor.decrypt(userArguments.args[1])))) {

						if (!ServerCommandArguments.usersLoggedIn.contains(userArguments.args[0])) {

							clientName = userArguments.args[0];
							client = clientName;

						} else {
							client = userArguments.args[0];
						}

					}

				}

				System.out.println("execute called");
				userCommand.execute(userArguments, client);
				if (!userArguments.keepRunning) {
					break;
				}
				System.out.println("after called");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException ex) {
			ServerCommandArguments.usersLoggedIn.removeUser(client);
			ServerCommandArguments.usersLoggedIn.saveInServer();
		}

	}

	public void kill() {
		isRunning = false;
	}
}
