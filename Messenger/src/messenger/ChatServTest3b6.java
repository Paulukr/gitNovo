package messenger;

import java.net.Socket;

public class ChatServTest3b6 extends ChatServTest2b {

	public ChatServTest3b6() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatServTest2b test2a = new ChatServTest3b6();
		test2a.go();
	}
	public class ClientHandler3b6 extends ChatServTest2a.ClientHandler{
		
		public ClientHandler3b6(ChatServTest2a chatServTest2a, Socket clientSocket) {
			chatServTest2a.super(clientSocket);
			// TODO Auto-generated constructor stub
		}

		public ClientHandler3b6(ChatServTest2a chatServTest2a, Socket clientSocket, int clientNo) {
			chatServTest2a.super(clientSocket, clientNo);
			// TODO Auto-generated constructor stub
		}
		
	}

}
