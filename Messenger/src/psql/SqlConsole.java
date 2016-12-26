package psql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlConsole {

	Statement statement;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlConsole sqlConsole = new SqlConsole();
		String sql1 = "insert into employee values(5, 'John')";
		String sql2 = "update employee set eid=9, ename = 'Jacob' where eid=5";
		// TODO Auto-generated method stub
//		try {
//			sqlConsole.statement.execute(sql1);
//			sqlConsole.statement.execute(sql2);
//		} catch (SQLException e) {e.printStackTrace();}	// TODO Auto-generated catch block
		sqlConsole.runConsole();
//		new Thread(() -> sqlConsole.runConsole()).start();
//		Thread consoleThread = new Thread(() -> sqlConsole.runConsole());
//		consoleThread.start();
//		consoleThread.stop();

	}
	SqlConsole(){
		setUpSql();
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
		protected void runConsole() {
			Scanner keyboard = new Scanner(System.in);
			while(keyboard.hasNext()){
				try {
					String query = keyboard.nextLine();
					System.out.println(query);
					statement.execute(query);
				} catch (SQLException e) {e.printStackTrace();}
			}
			keyboard.close();
			
	}

}
