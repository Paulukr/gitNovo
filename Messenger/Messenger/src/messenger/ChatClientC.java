package messenger;//it works!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientC {
	
	Thread readerThread;
	Socket socket;
	String ip = null;
	int port = -1;
	BufferedReader reader;
	PrintWriter writer;	
	boolean toCloseFlag = false;
	public void setUpNetworking()
	{
		boolean notConnected = true;
		do{
			try {	
				System.out.println("Trying establish networking ");
				socket = new Socket(ip, port);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream());
				System.out.println("Networking established");
				notConnected = false;
				if(readerThread != null)
					if(readerThread.isAlive()) {
						System.out.println("readerThread is alive ");
						readerThread.interrupt();}
			}catch (IOException e) {e.printStackTrace();
				if(!toCloseFlag){
					try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
					notConnected = true;//setUpNetworking(ip, port);
					//setUpNetworking(ip, port);
				}
			}
		}while(notConnected);
	}
	
	public class IncomingReader implements Runnable {
		StringListener stringListener;
		public void run()
		{
			String message;
			
				try {
					while ((message = reader.readLine())!=null)
					{
						System.out.println("read: " + message);
						stringListener.stringReceived(message);
						if(Thread.interrupted()){
							throw new InterruptedException("planned interrupt") ;
						}
					}//System.out.println("Socket closed");
				}catch (SocketException e){
					e.printStackTrace();
					if(!toCloseFlag)setUpNetworking();
					try {
						//new Object().wait();
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("Reader can now go");
						run();
						
					}
				}catch (InterruptedException e) {e.printStackTrace();
				}catch (IOException e) {e.printStackTrace();} 
		}
		IncomingReader(StringListener stringListener){
			this.stringListener = stringListener;
		}
	}//end inner class
	public void send(String message)
	{
		writer.println(message);
		writer.flush();
	}
	public void go(String ip, int port, StringListener stringListener)
	{
		this.ip = ip;
		this.port = port;
		setUpNetworking();
		readerThread = new Thread(new IncomingReader(stringListener));
		readerThread.start();
	}
	public void close()
	{
		
		//writer.println("/close");
		//writer.flush();
		//TODO seems need to restart readerThread
		toCloseFlag = true;
		if(readerThread != null) readerThread.interrupt();//and seems this don't work; try inner watchdog
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Thread.yield();
		//Thread.currentThread().stop();
//		Thread.currentThread().destroy();
	}
}
