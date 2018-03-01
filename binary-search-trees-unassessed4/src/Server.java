import java.net.*;
import java.util.*;
import java.io.*;

public class Server {
  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    ArrayList<String> wordsList = new ArrayList<String>();
    Boolean insertClient = false;
    Boolean queryClient = false;
	Scanner scanner;
    // Open a server socket:
	System.out.println("Welcome to the chat app");
	System.out.println(" ");
    try {
      serverSocket = new ServerSocket(9877);
    } 
    catch (IOException e) {
      System.err.println("Couldn't listen on port: 4444.");
      System.exit(-1);
    }
    // Listen to the socket, accepting connections from new clients,
    // and running a new thread to serve each new client:
    try {
      Socket clientSocket = serverSocket.accept();
      DataOutputStream toClient;
      DataInputStream  fromClient;

      try {
		    toClient = new DataOutputStream(clientSocket.getOutputStream());
		    fromClient  = new DataInputStream(clientSocket.getInputStream());
			scanner = new Scanner(System.in);
		    String clientInput;
			String clientInput2;
			String listOfWords ="";
			String userInputFromServer;
	    
			
		    while ((clientInput = fromClient.readUTF()) != "quit") {    	
			    	//if(clientInput.equals("insert")){
					//clientInput = fromClient.readUTF();//recieving word to be added to list of words from client.
					System.out.println(" ");
					System.out.println(clientInput);
					System.out.println(" ");
					userInputFromServer = scanner.nextLine();
					
				//	if(wordsList.contains(clientInput2)){
						toClient.writeUTF(userInputFromServer);
						System.out.println(" ");
					/*}else{
						toClient.writeUTF("new word");
						wordsList.add(clientInput2);//inserting word into list of words.	
					}
				} else if(clientInput.equals("query")){
						clientInput2 = fromClient.readUTF();
						
						if(wordsList.contains(clientInput2)){					
							toClient.writeUTF("word present");
						}else{
							toClient.writeUTF("word absent");
						}
					}else if(clientInput.equals("list")){
						for(String s : wordsList){
							listOfWords = listOfWords + ("\n" + s ); 
						}
						toClient.writeUTF(listOfWords);
					}*/
		    }	    
		    toClient.close();
		    fromClient.close();
		    clientSocket.close();	    
       }catch (IOException e) {
        	System.exit(1);
      	}
      }catch (Exception e) {
      try {
        serverSocket.close(); 
      }
      catch (IOException io) {
        System.err.println("Couldn't close server socket" +
                           io.getMessage());
      }
    }
  }
}

