

public class LogsTheClientOut extends LogoutCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		Client.setUserName(null);

	}

}
