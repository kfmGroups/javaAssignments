package command.client;

import assignment.Encryptor;
import command.CommandArguments;
import command.RegisterCommand;

public class RegisterClient extends RegisterCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		userInput.streamToServerandFromServer.println(userInput.args[0]);
		userInput.streamToServerandFromServer.println(Encryptor.encrypt(userInput.args[1]));		
	}

}
