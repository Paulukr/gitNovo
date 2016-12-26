package psql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyPGsql {
	Connection con = null;
	Statement statement1 = null;
	public Statement getStatement1() {
		return statement1;
	}
	public MyPGsql(){
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/emp", "postgres", "postgres");//password
			if (con != null) System.out.println("Connected");	
			
			statement1 = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	public boolean execute(String sql) {
		try {
			return	statement1.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	public ResultSet executeQuery(String sql) {
		try {
			return	statement1.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void close() {
		      try {
					if(statement1!=null) statement1.close();						
					if(con!=null) con.close();						
			} catch (SQLException e) {
				e.printStackTrace();
			}  
	}
	
}

