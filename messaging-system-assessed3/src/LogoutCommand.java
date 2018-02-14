package command;

public abstract class LogoutCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		return 0;
	}

	@Override
	public boolean expectsResponse() {
		return true;
	}

	@Override
	public String getCommand() {
		return "logout";
	}

}
