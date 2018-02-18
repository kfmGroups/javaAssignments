package assignment;

import java.io.*;
import java.net.*;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

	private BufferedReader server;
	private volatile boolean isRunning = true;

	ClientReceiver(BufferedReader server) {
		this.server = server;
	}

	public void run() {

		// Print to the user whatever we get from the server:
		try {
			while (isRunning) {

				String s = server.readLine(); // Matches FFFFF in
												// ServerSender.java
				if (s != null) {
					String[] sArr = s.split(" ");
					if (s.equals(Client.QUIT)) {
						kill();
						continue;
					}
					if (s.startsWith("From")) {
						int splitIndex = s.indexOf(":") + 2;

						String decryptedMessage = Encryptor.decrypt(s.substring(s.indexOf(":") + 2));
						System.out.println(s.substring(0, splitIndex) + decryptedMessage);
					} else {
						System.out.println(s);
					}
				} else
					Report.errorAndGiveUp("Server seems to have died");
			}
		} catch (IOException e) {
			Report.error("Server seems to have died " + e.getMessage());
		}
	}

	public void kill() {
		isRunning = false;
	}
}

/*
 * 
 * The method readLine returns null at the end of the stream
 * 
 * It may throw IoException if an I/O error occurs
 * 
 * See https://docs.oracle.com/javas
 * e/8/docs/api/java/io/BufferedReader.html#readLine--
 * 
 * 
 */
