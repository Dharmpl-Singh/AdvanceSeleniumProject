package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.HomePage;
import objectRepository.LoginPage;

/**
 * This class Consists of basic configuration annotations of TestNG
 * @author DharmpalSingh 
 */
public class BaseClass {
	
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public PropertyFileUtility pUtil=new PropertyFileUtility();
	public JavaUtility jUtil=new JavaUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public WebDriver driver=null;
	
	public static WebDriver sdriver;
	
	@BeforeSuite(alwaysRun = true)
	public void bsConfig() {
		
		System.out.println("=== DB Connection Successful ===");
	}
	
	//@BeforeTest //--> it is used while doing distributed parallel execution to avoid multiple browser opening so with the help of this we launch browser at test tag 
	@BeforeClass(alwaysRun = true)
	public void bcConfig() throws IOException {
		
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			System.out.println(BROWSER+" browser launched succesfully");
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println(BROWSER+" browser launched succesfully");
		}
		else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println(BROWSER+" browser launched succesfully");
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
		
		sdriver=driver;// it is used for listener in order to get the updated driver 
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException
	{
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("=== Login Successfully ===");
	}
	
	@AfterMethod(alwaysRun = true)
	public void amConfig() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.logoutOfApp(driver);
		System.out.println("=== Logout Successfully ==");
	}
	
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		System.out.println("=== Browser Close Successfully ===");
	}
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("=== DB Disconnection Successfully===");
	}
	

}
