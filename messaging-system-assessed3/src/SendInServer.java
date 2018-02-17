
package command.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import assignment.Encryptor;
import assignment.Message;
import assignment.Report;
import command.CommandArguments;
import command.SendCommand;
import command.ServerCommandArguments;

public class SendInServer extends SendCommand {

	@Override
	public void execute(CommandArguments userInput, String ClientName) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		String reciepientMessage = Encryptor.decrypt(userArguments.args[1]);
		if (ServerCommandArguments.usersLoggedIn.contains(ClientName)) {
			if (userArguments.args[1] != null) {

				if ((ServerCommandArguments.usersLoggedIn.contains(userArguments.args[0]))) {
					Message msg = new Message(ClientName, reciepientMessage);
					ServerCommandArguments.clientTable.addUserMessage(userArguments.args[0], msg);
					Message recipeientMsg = ServerCommandArguments.clientTable.getQueue(userArguments.args[0]).get(ServerCommandArguments.clientTable.getIndex(userArguments.args[0]));
					PrintStream reciepientStream = ServerCommandArguments.userStream
							.getUserStream(userArguments.args[0]);
					if (reciepientStream != null) {
						reciepientStream.println(recipeientMsg.toEncrypedString());
					}
				} else {
					Message msg = new Message(ClientName, reciepientMessage);
					ServerCommandArguments.clientTable.addUserMessage(userArguments.args[0], msg);
				}
			}
		} else {
			userInput.streamToServerandFromServer.println("you have to login before you can send messages");
		}

	}
}
