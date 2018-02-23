
import java.io.PrintStream;

public class PreviousMessage extends PreviousCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		System.out.println(ServerCommandArguments.usersLoggedIn.contains(clientName));
		if (!ServerCommandArguments.usersLoggedIn.contains(clientName)) {
			userInput.streamToServerandFromServer.println("You have to login to utilize the previous command");
			return;
		}

		int previousIndex = ServerCommandArguments.clientTable.getPreviousMessageIndex(clientName);
		PrintStream reciepientStream = ServerCommandArguments.userStream.getUserStream(clientName);
		if (!((previousIndex) < 0)) {
			Message recipeientMsg = ServerCommandArguments.clientTable.getQueue(clientName).get(previousIndex);

			if (reciepientStream != null) {
				reciepientStream.println(recipeientMsg.toEncrypedString());
			}

		} else {

			reciepientStream.println("You have have no previous messages");
		}
	}

}
