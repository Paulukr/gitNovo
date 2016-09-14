package messenger;

public class ChatCliKaBeaconConsole{


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatClientC client = new ChatClientC();	
		new Thread((new ConsoleChatClient()).new CommandLineHandler(client)).start();
		
		client.go("192.168.10.118", 20000,(String)->{});//(String string)->{if(!string.equals("/ka"))  System.out.println(string);});
		
		for(;;) if(client.socket != null) break;
		client.send("/pureOutputOn");
		new Thread(() -> {
			for(;;) { if(client.socket != null) client.send("/ka");
				try {Thread.sleep(100);} catch (InterruptedException e) {}}}
		).start();
	}


}
