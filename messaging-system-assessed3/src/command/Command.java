package command;

import java.io.BufferedReader;
import java.io.IOException;


import command.client.LoginClient;
import command.client.RegisterClient;
import command.server.LogClientOutServer;
import command.server.LoginClientInServer;
import command.server.RegisterClientInServer;

public interface Command {
	int getNumberOfArguments();//for each command entered it knows the number of arguments to expect
	void execute(CommandArguments userInput); //based on the command entered it knows what to execute
	boolean expectsResponse(); //to know whether it will respond or not.
	public static Command readCommand(BufferedReader userInputStream, boolean isServer){
		try {
			String command = userInputStream.readLine();
			if(command.equals("register")){
				return isServer ? new RegisterClientInServer() : new RegisterClient();//RegisterClient sends user's input to the server.
			}else if(command.equals("login")){
				return isServer ? new LoginClientInServer() : new LoginClient();
			}else if(command.equals("logout")){
				return new LogClientOutServer(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
 String getCommand();
	
	
}
