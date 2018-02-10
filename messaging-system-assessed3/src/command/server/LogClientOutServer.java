package command.server;

import command.CommandArguments;
import command.LogoutCommand;
import command.ServerCommandArguments;

public class LogClientOutServer extends LogoutCommand{

	@Override
	public void execute(CommandArguments userInput) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		userArguments.keepRunning = false;
	}
	
}
