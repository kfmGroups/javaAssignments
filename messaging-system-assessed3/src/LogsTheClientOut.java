package command.client;

import assignment.*;
import command.CommandArguments;
import command.LogoutCommand;
import command.ServerCommandArguments;

public class LogsTheClientOut extends LogoutCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {

		if (CommandArguments.usersLoggedIn.contains(clientName)) {
			userInput.keepRunning = false;
			CommandArguments.usersLoggedIn.removeUser(clientName);
		}
	}

}
