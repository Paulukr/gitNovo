package messenger;

import java.util.Scanner;

public class ConsoleChatClient {
	static ChatClientC client = new ChatClientC();
	public static void main(String[] args) {
		
		Thread CommandLine = new Thread(() -> CommandLineHandle());
		CommandLine.start();
		client.go("192.168.10.117", 20000, (String) -> System.out.println());
	}
	public static void CommandLineHandle() {
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
}
