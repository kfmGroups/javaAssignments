package command;

import java.io.BufferedReader;

import java.io.IOException;


import command.client.LoginClient;
import command.client.LogsTheClientOut;
import command.client.RegisterClient;
import command.client.SendClient;
import command.server.DeleteMessage;
import command.server.LogClientOutServer;
import command.server.LoginClientInServer;
import command.server.NextMessage;
import command.server.PreviousMessage;
import command.server.RegisterClientInServer;
import command.server.SendInServer;

public interface Command {
	int getNumberOfArguments();//for each command entered it knows the number of arguments to expect
	void execute(CommandArguments userInput, String clientName); //based on the command entered it knows what to execute
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

 String getCommand();
	
	
}
