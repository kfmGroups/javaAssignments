package command.client;

import command.CommandArguments;
import command.LoginCommand;

public class LoginClient extends LoginCommand{

	@Override
	public void execute(CommandArguments userInput, String userName) {
		userInput.streamToServerandFromServer.println(userInput.args[0]);
		userInput.streamToServerandFromServer.println(userInput.args[1]);		
	}

}