
import java.io.PrintStream;

public class NextMessage extends NextCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		if (!ServerCommandArguments.usersLoggedIn.contains(clientName)) {
			userInput.streamToServerandFromServer.println("You have to login to utilize the next command");
			return;
		}

		int nextIndex = ServerCommandArguments.clientTable.getNextMessageIndex(clientName);
		PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(clientName);
		if (!((nextIndex) >= ServerCommandArguments.clientTable.getQueue(clientName).size())) {
			Message recipeientMsg = ServerCommandArguments.clientTable.getQueue(clientName).get(nextIndex);

			if (reciepientStream != null) {
				reciepientStream.println(recipeientMsg.toEncrypedString());
			}
		} else {

			reciepientStream.println("You have have no more next messages");
		}
	}

}
