package command.server;

import java.io.PrintStream;

import assignment.Encryptor;
import assignment.Message;
import command.CommandArguments;
import command.PreviousCommand;
import command.ServerCommandArguments;

public class PreviousMessage extends PreviousCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {

		if (ServerCommandArguments.usersLoggedIn.contains(clientName)) {

			int previousIndex = ServerCommandArguments.clientTable.getPreviousMessageIndex(clientName);
			PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(clientName);
			if (!((previousIndex) < 0)) {
				Message recipeientMsg = ServerCommandArguments.clientTable.getQueue(clientName).get(previousIndex);

				if (reciepientStream != null) {
					reciepientStream.println(recipeientMsg.toEncrypedString());
				}
			} else {

				reciepientStream.println("You have have no previous messages");
			}
		} else {
			userInput.streamToServerandFromServer.println("You have to login to utilize the previous command");
		}
	}
}
