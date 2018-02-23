
public abstract class QuitCommand implements Command {

	@Override
	public int getNumberOfArguments() {

		return 0;
	}

	@Override
	public String getCommand() {
		return "quit";
	}

	@Override
	public boolean mustBeLoggedIn() {
		return false;
	}

}
