package psql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class PGsql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement statement1 = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/emp", "postgres", "password");
			if (con != null) System.out.println("Connected");
			
			statement1 = con.createStatement();
			String sql1 = "insert into employee values(5, 'John')";
			String sql2 = "update employee set eid=9, ename = 'Jacob' where eid=5";
			statement1.execute(sql1);
			
		    ResultSet rs = statement1.executeQuery("SELECT eid, ename FROM employee");
		    SortedMap<Integer, String> map = new TreeMap<Integer, String>();
		    int i = 0;
		    while (rs.next()) {
		    	map.put(rs.getInt("eid"), rs.getString("ename"));
//		        int x = rs.getInt("eid");
//		        String s = rs.getString("ename");
		    	i++;
		    }
		    System.out.println(i);
		    System.out.println(map);
		    for(Map.Entry<Integer, String> entry:map.entrySet()) System.out.println(entry);
		      rs.close();
		      statement1.close();
		      con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   finally{
			//finally block used to close resources
			try{
				if(statement1!=null)
					statement1.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}

}
