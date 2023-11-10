package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleExecuteUpdateJDBC {

	public static void main(String[] args) throws SQLException {
        
		Driver driver=new Driver();
		
		//Step 1: Register the Driver
		DriverManager.registerDriver(driver);
		
		//Step 2: Get Connection with Database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "root", "Ag081992#");
		
		//Step 3: Issue the Create Statement
		Statement state = con.createStatement();
		
		//Step 4: execute a query
		String query="insert into empinfo values('SpiderWomen','London',5);";
		int result = state.executeUpdate(query);
		if(result==1)
		{
			System.out.println("data added successfully");
		}
		
		//Step 5: close database
		con.close();
	}

}
