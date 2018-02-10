package command;

import java.io.PrintStream;
// This interface holds the user's argument and stream(from the client to server and backwards.
public class CommandArguments {
	
	public String[] args;
	public boolean keepRunning;
	public PrintStream toServer;
	
}
