
public class QuitServer extends QuitCommand {

	@Override
	public void execute(CommandArguments userInput, String clientName) {

		ServerCommandArguments server = (ServerCommandArguments) userInput;

		server.streamToServerandFromServer.println(Client.QUIT);
		server.keepRunning = false;
	}

}
