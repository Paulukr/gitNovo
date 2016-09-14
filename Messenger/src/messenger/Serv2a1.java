package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Serv2a1 {
//v3.7 this version is about versioning, not inheritance, but uses architecture of 2b, and maybe deeping it.//not an object of right en
	//roles
	int sql;
	int beacon;//Beacon
	
	int clientCount = 0;
	ArrayList<ClientHandler> clients;
	
	public class ClientHandler implements Runnable
	{
		String clientName;
		PrintWriter writer;
		Thread clientHandlerThread;
		
		BufferedReader reader;
		Socket socket;	
		int clientNo=-1;
		boolean pureOutput = false;


		
		public ClientHandler(Socket clientSocket)
		{
			 handleCommand("Hello");
			 Serv2a1.this.handleCommand("Hello");
			 Serv2a1.this.clientCount = 0;
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
						if( message.startsWith("/") ) handleCommand(message);
						
						System.out.println("read " + clientNo + " " + message);
						if(!pureOutput) tellEveryone(clientNo + ": " + message);
						else tellEveryone(message);
					}
				} catch (IOException e) {e.printStackTrace();}			
		}
		void handleCommand(String command){
			
			if( command.equalsIgnoreCase("/pureOutputOn") ){
				pureOutput = true;
				System.out.println("client " + clientNo + "  pure output =  " + pureOutput);
			}
			if( command.equalsIgnoreCase("/pureOutputOff") ){
				pureOutput = false;
				System.out.println("client " + clientNo + "  pure output =  " + pureOutput);
			}
			if( command.equalsIgnoreCase("/close") ){
				System.out.println("client " + clientNo + "  to close  " + pureOutput);
				clients.set(clientNo-1, null);
			}
			System.out.println("client " + clientNo + "  no such command  " + command);
		}
		
	}//end inner class
	void handleCommand(String command){System.out.println("Outer");}
	public class CommandLineHandler implements Runnable
	{

		@Override
		public void run() {
			System.out.println("command line start");
			Scanner keyboard = new Scanner(System.in);
			String line;
			while(true)
			{
				line=keyboard.nextLine();
				//System.out.println(line);
				//line.startsWith("c");
				if(line.equals("close"))
				{
					keyboard.close();
					break;
				}
			}
			
		}
		
	}//end inner class
	public void go()
	{
		System.out.println("ChatServTest2a start" + "vx.3.7");
		Thread commandLineHandler = new Thread(new CommandLineHandler());
		commandLineHandler.start();	
		Thread hostingThread = new Thread(() -> host());
		hostingThread.start();	
		
		clients = new ArrayList<ClientHandler>();
	}
	public void host() {
		try(ServerSocket serverSocket = new ServerSocket(20000);) {		//ServerSocket serverSocket = null;		
			while(true)			//serverSocket = new ServerSocket(20000);
			{
				Socket clientSocket =  serverSocket.accept();
				clientCount++;
				
				ClientHandler clientHandler = new ClientHandler(clientSocket, clientCount);				
				clients.add(clientHandler);
				
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
		test2a.go();
	}

}
