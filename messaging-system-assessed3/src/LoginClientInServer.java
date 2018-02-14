package command.server;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import assignment.Message;
import command.CommandArguments;
import command.LoginCommand;
import command.ServerCommandArguments;

public class LoginClientInServer extends LoginCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput;
		// Message clientsMsg = null;
		if (ServerCommandArguments.loginInfo.isPasswordCorrect(userArguments.args[0], userArguments.args[1])) {
			
			
			if(ServerCommandArguments.clientTable.getIndex(userArguments.args[0]) != -1){
				Message clientsMsg = ServerCommandArguments.clientTable.getQueue(userArguments.args[0]).get(ServerCommandArguments.clientTable.getIndex(userArguments.args[0]));
				if (clientsMsg != null) {
					userArguments.streamToServerandFromServer.println(clientsMsg);
				}
			}else{
				userArguments.streamToServerandFromServer.println("logged in successfully");
			}		
		} else {
			userArguments.streamToServerandFromServer.println("invalid user or password");
		}

	}

}
