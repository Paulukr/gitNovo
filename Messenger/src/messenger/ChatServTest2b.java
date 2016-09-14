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

public class ChatServTest2b {

	int clientCount = 0;
	ArrayList<PrintWriter> clientOutputSterams;
	
	public class ClientHandler implements Runnable
	{
		BufferedReader reader;
		Socket socket;	
		int clientNo=-1;
		boolean pureOutput = false;
		
		String clientName; 
		
		public ClientHandler(Socket clientSocket)
		{
			 handleCommand("Hello");
			 ChatServTest2b.this.handleCommand("Hello");
			 ChatServTest2b.this.clientCount = 0;
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
			} catch (IOException e) {e.printStackTrace();}
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
				clientOutputSterams.set(clientNo-1, null);
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
		System.out.println("ChatServTest2a start" + "vx.3.5");
		Thread commandLineHandler = new Thread(new CommandLineHandler());
		commandLineHandler.start();	
		
		clientOutputSterams = new ArrayList<PrintWriter>();

		try(ServerSocket serverSocket = new ServerSocket(20000);) {		//ServerSocket serverSocket = null;		
			while(true)			//serverSocket = new ServerSocket(20000);
			{
				Socket clientSocket =  serverSocket.accept();
				clientCount++;
				PrintWriter writer = new PrintWriter( clientSocket.getOutputStream());
				clientOutputSterams.add(writer);
				writer.println("Hello "+clientCount);
				tellEveryone("Tell everyone: " + clientCount + " is here");
						
				Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket, clientCount));
				clientHandlerThread.start();
				System.out.println("got a connection");
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	public void tellEveryone(String message)
	{
		Iterator<PrintWriter> iterator = clientOutputSterams.iterator();
		while(iterator.hasNext())
		{
			try{
				PrintWriter writer = (PrintWriter) iterator.next();
				writer.println(message);
				writer.flush();	
			}catch(Exception e){/*e.printStackTrace();*/}
			
		}
	}
	public static void main(String[] args) {
			
		ChatServTest2b test2a = new ChatServTest2b();
		test2a.go();
	}

}
