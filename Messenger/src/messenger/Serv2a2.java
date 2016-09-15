package messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Serv2a2 extends Serv2a1 {
	Map<String, Integer> names = new HashMap<String, Integer>();
	ArrayList<String> ocassions = new ArrayList<String>();
	void setOcassions() {
		ocassions.add("Clamed name that was already tacken");
		ocassions.add("Name attached");
	}

	void log(int clientNo, String message ) {};
	void log(int clientNo, String message, String whatHappend) {}; 
	void log(int clientNo, String message, int whatHappend) {}; 
	void log(int clientNo, String message, String whatHappend, String value) {};
	void log(int clientNo, String message, String whatHappend, String...values) {};
	void slog(int clientNo,  String...str) {//String message, String whatHappend, String...values
		switch(str.length) {
		case 0: break;
		case 1: log(clientNo, str[0]);break;
		case 2: log(clientNo, str[0], str[1]);
			tellEveryone(clientNo + str[1]);
			break;
		case 3: log(clientNo, str[0], str[1],str[2]);
			tellEveryone(clientNo + str[1] + str[2]);
			break;
			default:
				log(clientNo , str[0], str[1], str) ; 
				tellEveryone(clientNo + str[1] + str[2]);
			
		}
	}
	@Override
	int handleClientCommand(ClientHandler clientHandler, String command){
		switch(super.handleClientCommand(clientHandler, command)) {//newSuperHandleClientCommand2(clientHandler, command)
		case  0: return 0;
		case -1: return 0;
		case  1://command.substring(0, (command.indexOf(' ') > 0)?command.indexOf(' '):command.length()-1)
			//(command.indexOf(' ') > 0)?command.substring(0,command.indexOf(' ')):command
			int indexOfSpace = command.indexOf(' ');
			String commandHead = (indexOfSpace > 0)?command.substring(0,indexOfSpace):command;
			String commandTail = (indexOfSpace > 0)?command.substring(indexOfSpace + 1):null;
			switch(commandHead) {
			case "/test2": System.out.println(commandHead + "\n" + commandTail);
				break;
			case "/claimName": attachName(clientHandler, commandTail);
			
				default: return 1;
			}
		}
		return 0;
	}
	protected void attachName(ClientHandler clientHandler, String commandTail) {//TODO naming conventions
		if(names.containsKey(commandTail)) {
			slog(clientHandler.clientNo, clientHandler.lastMessage, "Clamed name that was already tacken");
			if(roles.containsValue(commandTail)) slog(clientHandler.clientNo, clientHandler.lastMessage, "Clamed key that was already tacken", commandTail);
		}else {
			names.put(commandTail, clientHandler.clientNo);
			clientHandler.clientName = commandTail;
			slog(clientHandler.clientNo, clientHandler.lastMessage, " Name attached ", commandTail);
			if(roles.containsKey(commandTail)) slog(clientHandler.clientNo, clientHandler.lastMessage, "Name attached", commandTail);
		}
	}
	int newSuperHandleClientCommand2(ClientHandler clientHandler, String command){
		int indexOfSpace = command.indexOf(' ');
		String commandHead = (indexOfSpace > 0)?command.substring(0,indexOfSpace):command;
		String commandTail = (indexOfSpace > 0)?command.substring(indexOfSpace + 1):null;	
	switch(commandHead) {
		case "/pureOutput":
			switch(commandTail) {
			case "On": clientHandler.pureOutput = true; break;
			case "Off": clientHandler.pureOutput = false; break;
			default: System.out.println("Wrog arguments");
				clientHandler.writer.write("Wrog arguments");
			}
			System.out.println("client " + clientHandler.clientNo + "  pure output =  " + clientHandler.pureOutput);
		break;
		case "/close":
			System.out.println("client " + clientHandler.clientNo + "  to close  " + clientHandler.pureOutput);
			clients.set(clientHandler.clientNo-1, null);//TODO
			return 0;
		default:
			switch(super.handleClientCommand(clientHandler, command)) {//newSuperHandleClientCommand2(clientHandler, command)
			case  0: return 0;
			case -1: return -1;
			case  1: 
				System.out.println("client " + clientHandler.clientNo + "  no such command  " + command);
				return 1;
			}
		}
		return 0;
	}
	final static  double version = 3.9 ;//:)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(version);
		new Serv2a2().go();
	}

}
////System.out.println("object: " + "test2a.getClass().getSimpleName()" + "\nclass: " + MethodHandles.lookup().lookupClass());
//System.out.println(version);
//System.out.println(Serv2a1.version);
