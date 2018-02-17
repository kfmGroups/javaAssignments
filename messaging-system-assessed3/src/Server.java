package assignment;
// Usage:

//        java Server
//
// There is no provision for ending the server gracefully.  It will
// end if (and only if) something exceptional happens.

import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {

		// This table will be shared by the server threads:   
		ClientTable clientTable = new ClientTable();

		LoginInfo loginInfo = new LoginInfo();

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(Port.number);
		} catch (IOException e) {
			Report.errorAndGiveUp("Couldn't listen on port " + Port.number);
		}

		try {
			// We loop for ever, as servers usually do.
			while (true) {
				// Listen to the socket, accepting connections from new clients:
				Socket socket = serverSocket.accept(); // Matches AAAAA in
														// Client

				// This is so that we can use readLine():
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				PrintStream toClient = new PrintStream(socket.getOutputStream());
				

				// We create and start a new thread to read from the client:
				Thread serverReciver = new ServerReceiver(toClient, fromClient, loginInfo);

				serverReciver.start();

				Thread cleanResources = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							serverReciver.join();
							fromClient.close();
							toClient.close();
							socket.close();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				cleanResources.start();
			}
		}

		catch (IOException e) {
			// Lazy approach:
			Report.error("IO error " + e.getMessage());
			// A more sophisticated approach could try to establish a new
			// connection. But this is beyond the scope of this simple exercise.
		}
	}
}
