package command.server;


import assignment.Message;
import command.CommandArguments;
import command.LoginCommand;
import command.ServerCommandArguments;

public class LoginClientInServer extends LoginCommand{

	@Override
	public void execute(CommandArguments userInput) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput; 
		Message clientsMsg = null;
		if(userArguments.loginInfo.isPasswordCorrect(userArguments.args[0], userArguments.args[1])){
			try {
				clientsMsg = userArguments.clientTable.getQueue(userArguments.args[0]).take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(clientsMsg != null){
				userArguments.toServer.println(clientsMsg);
				
			}else{
				userArguments.toServer.println("logged in successfully");
			}
			
			
			
			
			
			
		}else{
			userArguments.toServer.println("incorrect password");
		}
		
	}

}
