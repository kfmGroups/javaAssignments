
import java.io.BufferedReader;

import java.io.IOException;



public interface Command {
	int getNumberOfArguments();//for each command entered it knows the number of arguments to expect
	void execute(CommandArguments userInput, String clientName); //based on the command entered it knows what to execute
	boolean mustBeLoggedIn();
	//to know whether it will respond or not.
	public static Command readCommand(BufferedReader userInputStream, boolean isServer){
		try {
			String command = userInputStream.readLine();
			if(command.equals("register")){
				return isServer ? new RegisterClientInServer() : new RegisterClient();//RegisterClient sends user's input to the server.
			}else if(command.equals("login")){
				return isServer ? new LoginClientInServer() : new LoginClient();
			}else if(command.equals("logout")){
				return isServer ? new LogClientOutServer() : new LogsTheClientOut(); 
			}else if(command.equals("send")){
				return isServer ? new SendInServer() : new SendClient(); 
			}else if(command.equals("previous")){
				return new PreviousMessage(); 
			}else if(command.equals("next")){
				return new NextMessage();
			}else if(command.equals("delete")){
				 return new DeleteMessage();
			}else if(command.equals("quit")){
				return isServer? new QuitServer() : new QuitClient();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

 String getCommand();
	
	
}
