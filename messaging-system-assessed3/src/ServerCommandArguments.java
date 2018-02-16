package command;

import assignment.ClientTable;
import assignment.LoginInfo;
import assignment.StreamTable;
import assignment.UserLoggedIn;

public class ServerCommandArguments extends CommandArguments {
	public static LoginInfo loginInfo = new LoginInfo();
	public static ClientTable clientTable = new ClientTable();
	public static UserLoggedIn usersLoggedIn = new UserLoggedIn();
	public static StreamTable userStream = new StreamTable();

}
