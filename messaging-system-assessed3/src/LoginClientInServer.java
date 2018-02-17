package command.server;

import assignment.Encryptor;
import assignment.Message;
import command.CommandArguments;
import command.LoginCommand;
import command.ServerCommandArguments;

public class LoginClientInServer extends LoginCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		String clientPassword = Encryptor.decrypt(userArguments.args[1]);
		if (!(ServerCommandArguments.usersLoggedIn.contains(userArguments.args[0]))) {
			if (ServerCommandArguments.loginInfo.isPasswordCorrect(userArguments.args[0], clientPassword)) {

				ServerCommandArguments.usersLoggedIn.addUser(userArguments.args[0]);
				ServerCommandArguments.userStream.addUserStream(clientName, userArguments.streamToServerandFromServer);
				if (ServerCommandArguments.clientTable.getIndex(userArguments.args[0]) != -1) {
					Message clientsMsg = ServerCommandArguments.clientTable.getQueue(userArguments.args[0])
							.get(ServerCommandArguments.clientTable.getIndex(userArguments.args[0]));
					if (clientsMsg != null) {
						userArguments.streamToServerandFromServer.println(clientsMsg.toEncrypedString());
					}
				} else {
					userArguments.streamToServerandFromServer.println("logged in successfully");
				}

			} else {
				userArguments.streamToServerandFromServer.println("invalid user or password");
			}
		} else {
			userArguments.streamToServerandFromServer.println("you have logged in another device");

		}
	}

}
