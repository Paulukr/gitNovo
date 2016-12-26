package psql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

import messenger.ChatClientC;
import messenger.StringListener;

public class SqlMessenger {
	ChatClientC client;
	Statement statement;
	public static void main(String[] args) {
		SqlMessenger sqlMessenger = new SqlMessenger();
		String sql1 = "insert into employee values(5, 'John')";
		String sql2 = "update employee set eid=9, ename = 'Jacob' where eid=5";
		// TODO Auto-generated method stub
		try {
			sqlMessenger.statement.execute(sql1);
//			sqlMessenger.statement.execute(sql2);
		} catch (SQLException e) {e.printStackTrace();}	// TODO Auto-generated catch block
		
	}
	
	SqlMessenger(){
		setUpSql();
		setUpConnection();
	}
	protected void setUpSql() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/emp", "postgres", "password");
			if (con != null) System.out.println("Connected");
			statement = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void setUpConnection() {
		client = new ChatClientC();
		client.go("192.168.10.118", 20000, new StringListener() {
			Statement statement;
			@Override
			public void stringReceived(String string) {
				System.out.println(string);
				try {
					int expressionStart = string.indexOf("/sql");
					if(expressionStart > 0) {
						string = string.substring(expressionStart + 5);
						System.out.println("Trying to send sql message... \n" +
								string + "\n");
						client.send("~Trying to send sql message... \n" +
								 string + "\n");
						statement.execute(string);
						client.send("Sql message sent\n");
					}
				} catch (SQLException e) {e.printStackTrace();}	// TODO Auto-generated catch block
			}
			StringListener init(Statement statement){this.statement = statement; return this;}
		}.init(statement));

	}
	protected void sendSql(String sql) {
			try {
				statement.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
