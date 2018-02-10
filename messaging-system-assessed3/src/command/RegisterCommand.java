package command;

import command.Command;


public abstract class RegisterCommand implements Command {

	@Override
	public String getCommand() {
		
		return "register";
	}

	@Override
	public int getNumberOfArguments() {
		return 2;
	}

	@Override
	public boolean expectsResponse() {
		return true;
	}
	
}
