package praticeTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerPractice {
	
	@Test(retryAnalyzer = genericUtilities.RetryAnalyzerImplementation.class)
	public void sample1()
	{
		Assert.fail();
		System.out.println("Hi");
	}
	
	@Test
	public void sample2()
	{
		System.out.println("Hello");
	}

}
