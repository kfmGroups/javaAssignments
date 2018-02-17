package command.server;

import java.io.PrintStream;

import command.CommandArguments;
import command.DeleteCommand;
import command.ServerCommandArguments;

public class DeleteMessage extends DeleteCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {

		if (ServerCommandArguments.usersLoggedIn.contains(clientName)) {
			ServerCommandArguments.clientTable.removeUserMessageIndex(clientName);
			PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(clientName);
			if (reciepientStream != null) {
				reciepientStream.println("current message deleted");
			}
		} else {
			PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(clientName);
			if (reciepientStream != null) {
				reciepientStream.println("you must login to utilize the delete command");
			}
		}
	}

}
