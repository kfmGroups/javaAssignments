

public class LogsTheClientOut extends LogoutCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		
		if (clientName != null) {
			System.out.println("This is my name: "+clientName);
			userInput.keepRunning = false;
		}

	}

}
