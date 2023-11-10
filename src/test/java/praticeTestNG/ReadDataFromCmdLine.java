package praticeTestNG;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine {
	
	@Test
	public void readData()
	{
		String UN=System.getProperty("username");
		String PW=System.getProperty("password");
		System.out.println(UN);
		System.out.println(PW);
	}

}
