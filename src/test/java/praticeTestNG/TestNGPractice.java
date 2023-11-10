package praticeTestNG;



import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPractice {
	
	@Test(enabled=false)
	public void sample1Test()
	{
		System.out.println("Hello");
		Assert.fail();
	}
	
	@Test(priority=-3)
	public void sample2Test()
	{
		System.out.println("Hi");
	}
	
	@Test(priority=0, invocationCount=3)
	public void sample3Test()
	{
		System.out.println("Hey");
	}

}
