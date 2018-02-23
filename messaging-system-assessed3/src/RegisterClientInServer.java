
public class RegisterClientInServer extends RegisterCommand{
//This class registers the client in the server.
	@Override
	public void execute(CommandArguments userInput, String clientName) {
		ServerCommandArguments userArguments = (ServerCommandArguments) userInput; //arguments entered by the user the server has and also has reference to the login object.
		
		String clientPassword = Encryptor.decrypt(userArguments.args[1]);
		if (ServerCommandArguments.loginInfo.registerUser(userArguments.args[0], clientPassword)) {
			userArguments.streamToServerandFromServer.println("registered successuflly");
			ServerCommandArguments.clientTable.addUser(userArguments.args[0]);
		} else {
			userArguments.streamToServerandFromServer.println("user entered exists already");
		}
	}
}
