package assignment;

// Usage:
//        java Client user-nickname server-hostname
//
// After initializing and opening appropriate sockets, we start two
// client threads, one to send messages, and another one to get
// messages.
//
// A limitation of our implementation is that there is no provision
// for a client to end after we start it. However, we implemented
// things so that pressing ctrl-c will cause the client to end
// gracefully without causing the server to fail.
//
// Another limitation is that there is no provision to terminate when
// the server dies.

import java.io.*;
import java.net.*;

import command.Command;
import command.CommandArguments;

class Client {

	public static final String QUIT = "quit";
	

	public static void main(String[] args) {

		// Check correct usage:
		if (args.length != 1) {
			Report.errorAndGiveUp("Usage: java Client server-hostname");
		}

		String hostname = args[0];
		// Open sockets:
		PrintStream toServer = null;
		BufferedReader fromServer = null;
		Socket server = null;

		try {
			server = new Socket(hostname, Port.number); // Matches AAAAA in
														// Server.java
			toServer = new PrintStream(server.getOutputStream());
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
		} catch (UnknownHostException e) {
			Report.errorAndGiveUp("Unknown host: " + hostname);
		} catch (IOException e) {
			Report.errorAndGiveUp("The server doesn't seem to be running " + e.getMessage());
		}
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			CommandArguments userArguments = new CommandArguments();//holds the user arguments and info about the stream to the server.
			Command userCommand = Command.readCommand(user, false);
			if (userCommand == null) {
				System.out.println("invalid command");
				continue;
			}
			userArguments.args = new String[userCommand.getNumberOfArguments()];
			for (int i = 0; i < userArguments.args.length; i++) {
				try {
					userArguments.args[i] = user.readLine(); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			userArguments.streamToServerandFromServer = toServer;
			userArguments.keepRunning = true;
			toServer.println(userCommand.getCommand());
			userCommand.execute(userArguments, "");
			if(!userArguments.keepRunning){
				break;
			}
			
			if(userCommand.expectsResponse()){
				try {
					System.out.println(fromServer.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Wait for them to end and close sockets.
		try {

			toServer.close();
			fromServer.close();
			server.close();
		} catch (IOException e) {
			Report.errorAndGiveUp("Something wrong " + e.getMessage());
		}
	}
}
