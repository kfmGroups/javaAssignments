
public class LogClientOutServer extends LogoutCommand {

	@Override
	public void execute(CommandArguments userInput, String client) {
		if (ServerCommandArguments.usersLoggedIn.contains(client)) {
			userInput.keepRunning = false;
			ServerCommandArguments.usersLoggedIn.removeUser(client);
			userInput.streamToServerandFromServer.println("logged out successfully");
			userInput.streamToServerandFromServer.println(Client.QUIT);
			ServerCommandArguments.userStream.removeUserStream(client);
		}
		userInput.streamToServerandFromServer.println("to logout you must login in");

	}

}
