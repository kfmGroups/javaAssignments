package command;

public abstract class LoginCommand implements Command{

	@Override
	public int getNumberOfArguments() {
		return 2;
	}

	@Override
	public boolean expectsResponse() {
		return true;
	}

	@Override
	public String getCommand() {
		return "login";
	}
	
	
}
