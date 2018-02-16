package command.client;

import assignment.Encryptor;
import command.CommandArguments;
import command.SendCommand;

public class SendClient extends SendCommand{

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		userInput.streamToServerandFromServer.println(userInput.args[0]);
		userInput.streamToServerandFromServer.println(Encryptor.encrypt(userInput.args[1]));
	}

}
