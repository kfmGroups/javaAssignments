package command.client;

import command.CommandArguments;
import command.LoginCommand;

public class LoginClient extends LoginCommand{

	@Override
	public void execute(CommandArguments userInput) {
		userInput.toServer.println(userInput.args[0]);
		userInput.toServer.println(userInput.args[1]);		
	}

}
