import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	Socket server;// client socket(network connection) to speak to the server.
	DataInputStream fromServer;// gets info from the server.
	DataOutputStream toServer;// sends info to the server.
	BufferedReader fromUser;
	Scanner inputObject;
	

	public Client(String serverName) {
		try {
			server = new Socket(serverName, 9877);
			fromServer = new DataInputStream(server.getInputStream());
			toServer = new DataOutputStream(server.getOutputStream());
		} catch (UnknownHostException e) {
			error("Unknown host: " + serverName);
		} catch (IOException e) {
			error("Couldn't get I/O for the connection to " + serverName);
		}

		fromUser = new BufferedReader(new InputStreamReader(System.in));
		inputObject = new Scanner(System.in);
	}

	private void error(String message) {
		System.err.println(message);
		System.exit(1); // Don't do this in practice! (Why?)
	}

	public void closeEverything() {
		try {
			// Let server know we are done.
			// Our convention is to send "0" to indicate this.

			toServer.writeUTF("quit");

			// Close the streams:

			toServer.close();
			fromServer.close();
			fromUser.close();
			server.close();
		} catch (IOException e) {
			error("Something went wrong ending the client");
			System.exit(-1); // Don't do this in practice.
		}
	}

	public void run() {
		try {
			String userInput;
			String userInputFromScanner;
			String serverResponse;
			while ((userInput = fromUser.readLine()) != null) {

				// We stipulate that a blank line tell the client to end:
				if(userInput.equals("quit")){		
						break;
					}
					
				if (userInput.equals("insert")) {
	
				  toServer.writeUTF(userInput);//write to server that i want to inset a word.
					userInputFromScanner = inputObject.nextLine();
					toServer.writeUTF(userInputFromScanner);//write word entered to server.
					//System.out.println("sent "+userInputFromScanner+" to server");
					serverResponse = fromServer.readUTF();//read response from server.
					System.out.println(serverResponse);//print out server's response.
   
				}else if (userInput.equals("query")) {
				
				  toServer.writeUTF(userInput);//write to server that i want to make a query.
					userInputFromScanner = inputObject.nextLine();
					toServer.writeUTF(userInputFromScanner);//write query entered to server.
					serverResponse = fromServer.readUTF();//read response from server.
					System.out.println(serverResponse);//print out server's response.
					
				}else if(userInput.equals("list")){
				
				  toServer.writeUTF(userInput);//write to server that i want the lists of words entered.
					serverResponse = fromServer.readUTF();//read response from server.
					System.out.println(serverResponse);//print out server's response.
					
				}
			
			}

		}catch (IOException e) {
			error("Socket commmunication broke");
		} finally {
			closeEverything();
		}

	}



}
