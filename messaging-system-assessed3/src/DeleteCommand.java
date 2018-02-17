package command;

public abstract class DeleteCommand implements Command {

	@Override
	public int getNumberOfArguments() {
		return 0;
	}

	@Override
	public String getCommand() {
		return "delete";
	}

}
