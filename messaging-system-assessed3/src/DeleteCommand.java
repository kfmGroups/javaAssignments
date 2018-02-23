
public abstract class DeleteCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		return 0;
	}

	@Override
	public String getCommand() {
		return "delete";
	}

	@Override
	public boolean mustBeLoggedIn() {
		return true;
	}

}
