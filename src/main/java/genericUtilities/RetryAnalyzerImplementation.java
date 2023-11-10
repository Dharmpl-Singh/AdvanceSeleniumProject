package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * This is a implementation class for IRetryAnalyzer interface of TestNG
 * @author DharmpalSingh
 */
public class RetryAnalyzerImplementation implements IRetryAnalyzer{

	int count=0;
	int retyrCount=3;
	@Override
	public boolean retry(ITestResult result) {
		
		while(count<retyrCount)
		{
			count++;
			return true; // if it return true mean it will retry
		}
		return false; //if it return false means stop the retry.
	}

}
