package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Serv2a1 {
	final static  double version = 3.8 ;//:)
//v3.7 this version is about versioning, not inheritance, but uses architecture of 2b, and maybe deeping it.//not an object of right en
	//threads
	Thread commandLineHandler = new Thread(new CommandLineHandler());
	Thread hostingThread = new Thread(() -> host());
	
	//roles
//	int sql;
//	int beacon;//Beacon
	Map<String, Integer> roles = new HashMap<String, Integer>();
	
	int clientCount = 0;
	ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();;
	
	public class ClientHandler implements Runnable
	{
		String role;
		String lastMessage;
		String clientName;
		PrintWriter writer;
		Thread clientHandlerThread;
		
		BufferedReader reader;
		Socket socket;	
		int clientNo=-1;
		boolean pureOutput = false;


		
		public ClientHandler(Socket clientSocket)
		{
//			 handleCommand("Hello");
//			 Serv2a1.this.handleCommand("Hello");
//			 Serv2a1.this.clientCount = 0;
			socket = clientSocket;
			
			try {
				InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
				reader = new BufferedReader(isReader);
			} catch (IOException e) {e.printStackTrace();}
		}
		public ClientHandler(Socket clientSocket,int clientNo)
		{
			socket = clientSocket;
			this.clientNo = clientNo;
			try {
				InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
				reader = new BufferedReader(isReader);
				writer = new PrintWriter( clientSocket.getOutputStream());
			} catch (IOException e) {e.printStackTrace();}
			clientHandlerThread = new Thread(this);
			clientHandlerThread.start();
		}
		@Override
		public void run()
		{
			String message;		
				try {
					while ((message = reader.readLine())!=null)
					{
						lastMessage = message;
						System.out.println("read " + clientNo + " " + message);
						if(!pureOutput) tellEveryone(clientNo + ": " + message);
							else tellEveryone(message);
						if( message.startsWith("/") ) if(handleClientCommand(this,message) == -1) break;
					}
				} catch (IOException e) {e.printStackTrace();}			
		}	
	}//end inner class
	int handleClientCommand(ClientHandler clientHandler, String command){
		if( command.equalsIgnoreCase("/pureOutputOn") ){
			clientHandler.pureOutput = true;
			System.out.println("client " + clientHandler.clientNo + "  pure output =  " + clientHandler.pureOutput);
			return 0;
		}
		if( command.equalsIgnoreCase("/pureOutputOff") ){
			clientHandler.pureOutput = false;
			System.out.println("client " + clientHandler.clientNo + "  pure output =  " + clientHandler.pureOutput);
			return 0;
		}
		if( command.equalsIgnoreCase("/close") ){
			System.out.println("client " + clientHandler.clientNo + "  to close  " + clientHandler.pureOutput);
			clients.set(clientHandler.clientNo-1, null);
			return 0;
		}
		System.out.println("client " + clientHandler.clientNo + "  no such command  " + command);
		return 1;
	}
	public class CommandLineHandler implements Runnable
	{

		@Override
		public void run() {
			System.out.println("command line start");
			Scanner keyboard = new Scanner(System.in);
			String line;
			do{
				line=keyboard.nextLine();
			}while(handleConsoleCommand(line) != -1);
			keyboard.close();
		}
		
	}//end inner class
	protected int handleConsoleCommand(String command) {
		switch(command) {
		case "close": close(); return 0;//TODO in test version close don't stop console
		case "test": System.out.println("test");
			break;
			default:
				//System.out.println("There is no such a command.");
				return 1;
		}
		return 0;
		//return -1 - close, 0 - command done, 1 - There is no such a command. 1 is for cascading parsers.
	}
	public void go() 
	{
		try {System.out.println("ChatServer " + getClass().getSimpleName() + " with go() version " + getClass().newInstance().version + " start ");
		} catch (Exception e) {e.printStackTrace();}
		commandLineHandler.start();	
		hostingThread.start();	 
	}
	public void host() {
		try(ServerSocket serverSocket = new ServerSocket(20000);) {	
			while(true)			
			{
				Socket clientSocket =  serverSocket.accept();
				clientCount++;			
				clients.add(new ClientHandler(clientSocket, clientCount));
				
				clients.get(clientCount - 1).writer.println("Hello "+clientCount);
				tellEveryone("Tell everyone: " + clientCount + " is here");
						
				System.out.println("got a connection");
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	public void tellEveryone(String message)
	{
		Iterator<ClientHandler> iterator = clients.iterator();
		while(iterator.hasNext())
		{
			try{
				ClientHandler handler = (ClientHandler) iterator.next();
				handler.writer.println(message);
				handler.writer.flush();	
			}catch(Exception e){/*e.printStackTrace();*/}
			
		}
	}
	public static void main(String[] args) {
			
		Serv2a1 test2a = new Serv2a1();
		System.out.println("object: " + test2a.getClass().getSimpleName() + "\nclass: " + MethodHandles.lookup().lookupClass());
		test2a.go();
//		String[] formain = {"Yahoo", "calabunga"};
//		main(formain);
		
	}

	void close() {
		System.out.println("Server is going to close");
		//TODO
	}
	
}
