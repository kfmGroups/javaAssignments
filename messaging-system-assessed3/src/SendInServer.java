

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;


public class SendInServer extends SendCommand {

	@Override
	public void execute(CommandArguments userInput, String ClientName) {
 		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		String reciepientMessage = Encryptor.decrypt(userArguments.args[1]);
		if (!ServerCommandArguments.usersLoggedIn.contains(userArguments.clientName)) {
			userInput.streamToServerandFromServer.println("you have to login before you can utilize the send command");
			return;
		}
	
		if ((ServerCommandArguments.clientTable.getQueue(userArguments.args[0]) == null)) {
			userInput.streamToServerandFromServer.println("You cannot send a message to an unregistered user");
			return;
		}

		if (userArguments.args[1] != null) {

			if ((ServerCommandArguments.usersLoggedIn.contains(userArguments.args[0]))) {
				Message msg = new Message(userArguments.clientName, reciepientMessage);
				ServerCommandArguments.clientTable.addUserMessage(userArguments.args[0], msg);
				Message recipeientMsg = ServerCommandArguments.clientTable.getQueue(userArguments.args[0]).get(ServerCommandArguments.clientTable.getQueue(userArguments.args[0]).size() - 1);
				PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(userArguments.args[0]);
				if (reciepientStream != null) {
					reciepientStream.println(recipeientMsg.toEncrypedString());
				}
			} else {
				Message msg = new Message(userArguments.clientName, reciepientMessage);
				ServerCommandArguments.clientTable.addUserMessage(userArguments.args[0], msg);
			}
		}
	}

}

