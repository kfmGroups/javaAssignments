
public class QuitClient extends QuitCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {
		userInput.keepRunning = false;

	}

}
