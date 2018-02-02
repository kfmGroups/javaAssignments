import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
	private BlockingQueue<Message> clientQueue;
	private PrintStream client;
	private Socket socket;
	private volatile boolean isRunning = true;

	public ServerSender(BlockingQueue<Message> q, PrintStream c, Socket socket) {
		clientQueue = q;
		client = c;
		this.socket = socket;
	}

	public void run() {

		try {
			while (isRunning) {

				Message msg = clientQueue.take(); // Matches EEEEE in ServerReceiver
				if (msg.getText().equals("quit")) {
					client.println("quit");
					System.out.println("quitting server sender");
					kill();
					continue;
				}
				client.println(msg); // Matches FFFFF in ClientReceiver
				client.flush();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			// Do nothing and go back to the infinite while loop.
		}
	}

	public void kill() {
		isRunning = false;
	}

}

/*
 * 
 * Throws InterruptedException if interrupted while waiting
 * 
 * See
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.
 * html#take--
 * 
 */
