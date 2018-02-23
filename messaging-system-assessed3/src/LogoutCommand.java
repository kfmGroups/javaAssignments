
public abstract class LogoutCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		return 0;
	}

	@Override
	public String getCommand() {
		return "logout";
	}
	
	@Override
	public boolean mustBeLoggedIn() {
		return true;
	}

}
