package praticeTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class createTestNG {
	
	@Test
	public void createCustomer()
	{
		Assert.fail();//fail
		System.out.println("create");
		
	}
	
	@Test(dependsOnMethods = "createCustomer")//skip
	public void modifyCustomer()
	{
		System.out.println("modify");
	}
	
	@Test
	public void deleteCustomer()//pass
	{
		System.out.println("delete");
	}

}
