package command.client;

import assignment.Encryptor;
import command.CommandArguments;
import command.LoginCommand;
import command.ServerCommandArguments;

public class LoginClient extends LoginCommand {

	@Override
	public void execute(CommandArguments userInput, String userName) {
		if (ServerCommandArguments.loginInfo.isPasswordCorrect(userInput.args[0], userInput.args[1])) {
			CommandArguments.usersLoggedIn.addUser(userName);
			userInput.streamToServerandFromServer.println(userInput.args[0]);
			userInput.streamToServerandFromServer.println(Encryptor.encrypt(userInput.args[1]));
		} else {
			userInput.streamToServerandFromServer.println(userInput.args[0]);
			userInput.streamToServerandFromServer.println(Encryptor.encrypt(userInput.args[1]));
		}
	}

}
