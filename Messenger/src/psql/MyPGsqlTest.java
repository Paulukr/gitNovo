package psql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyPGsqlTest {

	public static void main(String[] args) {
		MyPGsql dbcon = new MyPGsql();
		
		String sql1 = "insert into employee values(5, 'John')";
		String sql2 = "update employee set eid=9, ename = 'Jacob' where eid=5";
//		dbcon.execute(sql1);
		int n =0;
		try (ResultSet rs2 = dbcon.executeQuery("SELECT eid, ename FROM employee")){
			while (rs2.next()) {
				System.out.println(rs2.getInt("eid") + rs2.getString("ename"));
				n++;
			}
		} catch (SQLException e1) {e1.printStackTrace();}// TODO Auto-generated catch block
		System.out.println(n);
		dbcon.close();
	}

}
