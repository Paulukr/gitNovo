package messenger;

import psql.MyPGsql;

public class Secretary implements StringListener {
	ChatClientC socket;
	MyPGsql sql;
	@Override
	public void stringReceived(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		//logging
		try{sql.execute(getLogQuery(string));}catch(Exception e) {e.printStackTrace();}
		//sending queries
		int expressionStart = string.indexOf("/sql");
		if(expressionStart > 0) {
			string = string.substring(expressionStart + 5);
			System.out.println("Trying to send sql message... \n" +
					string + "\n");
			socket.send("~Trying to send sql message... \n" +
					 string + "\n");
			sql.execute(string);
			socket.send("Sql message sent\n");
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Secretary secretary = new Secretary("192.168.10.118", 20000);
		
	}

	public Secretary(String ip, int port) {
		socket = new ChatClientC();	
		socket.go(ip, port,this);
		sql = new MyPGsql();
	}
	String getLogQuery(String message) {
		return "insert into log1 (message) values('" + message + "')";
	}

}
