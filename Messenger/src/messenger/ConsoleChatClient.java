package messenger;

import java.util.Scanner;

public class ConsoleChatClient {

	public static void main(String[] args) {
		ChatClientC client = new ChatClientC();
		Thread CommandLine = new Thread((new ConsoleChatClient()).new CommandLineHandler(client));
		CommandLine.start();
		client.go("192.168.10.118", 20000, new StringListener() {		
			@Override
			public void stringReceived(String string) {
				System.out.println(string);
			}
		});
	}
	public class CommandLineHandler implements Runnable{
		ChatClientC client;
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
				client.send(line);
				if(line.equals("/close"))
				{
					keyboard.close();
					client.close();
					break;
				}
			}
		}
			CommandLineHandler(ChatClientC client){this.client = client;}
	}//end inner class
}
