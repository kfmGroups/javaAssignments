
public class LoginClientInServer extends LoginCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {

		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		String clientPassword = Encryptor.decrypt(userArguments.args[1]);

		if (!ServerCommandArguments.loginInfo.isPasswordCorrect(userArguments.args[0], clientPassword)) {
			userArguments.streamToServerandFromServer.println("Error: invalid user or password");
			return;
		}
		if (ServerCommandArguments.usersLoggedIn.contains(userArguments.args[0])) {
			userArguments.streamToServerandFromServer.println("Error: you have already logged in a device");
			return;
		}

		userArguments.clientName = userArguments.args[0];
		ServerCommandArguments.usersLoggedIn.addUser(userArguments.args[0]);
		ServerCommandArguments.userStream.addUserStream(userArguments.args[0],userArguments.streamToServerandFromServer);
		if (ServerCommandArguments.clientTable.getIndex(userArguments.args[0]) != -1) {
			Message clientsMsg = ServerCommandArguments.clientTable.getQueue(userArguments.args[0])
					.get(ServerCommandArguments.clientTable.getIndex(userArguments.args[0]));

			if (clientsMsg != null) {
				userArguments.streamToServerandFromServer.println("logged in successfully");
				userArguments.streamToServerandFromServer.println("current message: ");
				userArguments.streamToServerandFromServer.println(clientsMsg.toEncrypedString());

			}
		} else {

			userArguments.streamToServerandFromServer.println("logged in successfully: " + userArguments.args[0]);

		}

	}

}
