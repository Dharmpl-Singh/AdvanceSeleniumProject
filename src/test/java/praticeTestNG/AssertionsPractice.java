package praticeTestNG;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractice {
	
	@Test
	public void pratice()
	{
		System.out.println("Step 1");
		System.out.println("Step 2");
		Assert.assertEquals(2, 1);
		System.out.println("Step 3");
	}
	
	@Test
	public void pratice1()
	{
		SoftAssert sa=new SoftAssert();
		
		System.out.println("Step 1");
		sa.assertEquals(true, false);
		System.out.println("Step 2");
		sa.assertEquals("g", "A");
		System.out.println("Step 3");
		
		sa.assertAll();//failure logged -> it has to be written at the end of programs.
	}
	
	

}
