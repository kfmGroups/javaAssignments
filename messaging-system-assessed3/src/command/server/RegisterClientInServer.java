package command.server;

import command.CommandArguments;
import command.RegisterCommand;
import command.ServerCommandArguments;

public class RegisterClientInServer extends RegisterCommand{
//This class registers the client in the server.
	@Override
	public void execute(CommandArguments userInput) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput; //arguments entered by the user the server has and also has reference to the login object.
		
		if (userArguments.loginInfo.registerUser(userArguments.args[0], userArguments.args[1])) {
			userArguments.toServer.println("registered");
			userArguments.clientTable.add(userArguments.args[0]);
		} else {
			userArguments.toServer.println("user entered exists already");
		}
	}
}
