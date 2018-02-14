package command.server;

import command.CommandArguments;
import command.LogoutCommand;
import command.ServerCommandArguments;

public class LogClientOutServer extends LogoutCommand{

	@Override
	public void execute(CommandArguments userInput, String Client) {
		if(ServerCommandArguments.usersLoggedIn.contains(Client)){
			userInput.keepRunning = false;
			userInput.streamToServerandFromServer.println("logged out successfully");
		}else{
			userInput.streamToServerandFromServer.println("to logout you must login in");
		};
	}
	
}
