
package command.server;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import assignment.Message;
import assignment.Report;
import command.CommandArguments;
import command.SendCommand;
import command.ServerCommandArguments;

public class SendInServer extends SendCommand {

	@Override
	public void execute(CommandArguments userInput, String ClientName) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		if (userArguments.args[1] != null) {
			Message msg = new Message(ClientName, userArguments.args[1]);
			ServerCommandArguments.clientTable.addUserMessage(userArguments.args[0], msg); // Matches EEEEE in ServerSender.java
		}
		
		Message recipeientMsg = ServerCommandArguments.clientTable.getQueue(userArguments.args[0]).get(ServerCommandArguments.clientTable.getIndex(userArguments.args[0]));
		userArguments.streamToServerandFromServer.println(recipeientMsg);
	}

}
