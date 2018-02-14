package command;

public abstract class SendCommand implements Command{

	@Override
	public int getNumberOfArguments() {
		return 2;
	}

	@Override
	public boolean expectsResponse() {
		return false;
	}

	@Override
	public String getCommand() {
		return "send";
	}
	

}
