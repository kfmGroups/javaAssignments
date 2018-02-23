
public class LogClientOutServer extends LogoutCommand {

	@Override
	public void execute(CommandArguments userInput, String client) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		if (ServerCommandArguments.usersLoggedIn.contains(userArguments.clientName)) {

			ServerCommandArguments.usersLoggedIn.removeUser(userArguments.clientName);
			userInput.streamToServerandFromServer.println("logged out successfully");
			ServerCommandArguments.userStream.removeUserStream(userArguments.clientName);
			userArguments.clientName = null;
		} else {
			userInput.streamToServerandFromServer.println("to logout you must login");
		}
	}

}
