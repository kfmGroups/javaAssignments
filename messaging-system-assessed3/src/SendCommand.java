
public abstract class SendCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		return 2;
	}

	@Override
	public String getCommand() {
		return "send";
	}

}
