
import java.util.Timer;

import java.util.TimerTask;

public class ServerCommandArguments extends CommandArguments {

	public static LoginInfo loginInfo = new LoginInfo();
	public static ClientTable clientTable = new ClientTable();
	public static UserLoggedIn usersLoggedIn = new UserLoggedIn();
	public static StreamTable userStream = new StreamTable();
	public static Timer myTimer = new Timer();

	public static TimerTask task = new TimerTask() {

		public void run() {

			ServerCommandArguments.loginInfo.saveInServer();
			ServerCommandArguments.usersLoggedIn.saveInServer();
			ServerCommandArguments.clientTable.saveRegisteredUserandMessages();
			ServerCommandArguments.clientTable.saveUserIndex();

		}

	};

}
