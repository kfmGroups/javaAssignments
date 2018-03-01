class LaunchClient {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.err.println("Usage: java LaunchClient hostname");
      System.exit(1);
    }

    Client client = new Client(args[0]);
	System.out.println("Welcome to the chat app");
	System.out.println(" ");
    client.run();
  }
}

