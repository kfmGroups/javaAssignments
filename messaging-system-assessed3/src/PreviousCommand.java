
public abstract class PreviousCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		return "previous";
	}

	@Override
	public boolean mustBeLoggedIn() {
		return true;
	}

}
