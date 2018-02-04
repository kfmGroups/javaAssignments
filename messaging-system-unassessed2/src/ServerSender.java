import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
	private BlockingQueue<Message> clientQueue;
	private PrintStream client;
	private volatile boolean isRunning = true;

	public ServerSender(BlockingQueue<Message> q, PrintStream c) {
		clientQueue = q;
		client = c;
	}

	public void run() {

		try {
			while (isRunning) {
				Message msg = clientQueue.take(); // Matches EEEEE in ServerReceiver
				client.println(msg); // Matches FFFFF in ClientReceiver
				client.flush();
				if (msg.getSender().equals("quit")) {
					kill();
					continue;
				}
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
