package command.server;

import java.io.PrintStream;

import command.CommandArguments;
import command.DeleteCommand;
import command.ServerCommandArguments;

public class DeleteMessage extends DeleteCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {

		if (ServerCommandArguments.usersLoggedIn.contains(clientName)) {
			if (!ServerCommandArguments.clientTable.getQueue(clientName).isEmpty()) {
				ServerCommandArguments.clientTable.removeUserMessageIndex(clientName);
				PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(clientName);
				if (reciepientStream != null) {
					reciepientStream.println("current message deleted");
				}
			}else{
				userInput.streamToServerandFromServer.println("there are no messages to delete");
			}
		} else {
			userInput.streamToServerandFromServer.println("You have to login to utilize the next command");
		}
	}

}
