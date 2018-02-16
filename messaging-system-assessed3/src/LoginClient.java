package command.client;

import assignment.Encryptor;
import command.CommandArguments;
import command.LoginCommand;

public class LoginClient extends LoginCommand{

	@Override
	public void execute(CommandArguments userInput, String userName) {
		CommandArguments.usersLoggedIn.addUser(userName);
		userInput.streamToServerandFromServer.println(userInput.args[0]);
		userInput.streamToServerandFromServer.println(Encryptor.encrypt(userInput.args[1]));		
	}

}
