package command;

public abstract class NextCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		return 0;
	}

	@Override
	public String getCommand() {
		return "next";
	}

}
