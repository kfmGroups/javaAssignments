package command.client;

import command.CommandArguments;
import command.RegisterCommand;

public class RegisterClient extends RegisterCommand {

	@Override
	public void execute(CommandArguments userInput) {
		userInput.toServer.println(userInput.args[0]);
		userInput.toServer.println(userInput.args[1]);		
	}

}
