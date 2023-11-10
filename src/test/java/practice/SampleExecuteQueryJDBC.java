package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleExecuteQueryJDBC {

	public static void main(String[] args) throws SQLException {
		
		Driver driver=new Driver();
		
		//Step 1: Register the Driver
		 DriverManager.registerDriver(driver);
		
		//Step 2: Get Connection with Database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "root", "Ag081992#");
		
		//Step 3: Issue the Create Statement
		Statement state = con.createStatement();
		
		//Step 4: execute a query
		ResultSet result = state.executeQuery("select * from empinfo");
		while(result.next())
		{
			String value = result.getString(1)+" "+result.getString(2)+" "+result.getInt(3);
			System.out.println(value);
		}
		
		//Step 5: close Database
		con.close();
		
	}

}
