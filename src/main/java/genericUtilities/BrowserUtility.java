package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This will launch the browser
 * @author Dharmpal
 */
public class BrowserUtility {
	
	/**
	 * This method will use accept the browser argument and launch the browser
	 * @param driver
	 * @param browser
	 */
	public WebDriver launchBrowser(WebDriver driver,String browser)
	{
		if(browser.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
		return driver;
	}

}
