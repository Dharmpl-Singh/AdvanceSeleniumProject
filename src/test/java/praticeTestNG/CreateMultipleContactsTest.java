package praticeTestNG;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateMultipleContactsTest {
	
	ExcelFileUtility eUtil=new ExcelFileUtility();
	PropertyFileUtility pUtil=new PropertyFileUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	WebDriver driver=null;
	
	@BeforeMethod
	public void preCondition() throws Throwable {
		// Step 1: Read all the required data
		// Common Data
		
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		
		
		//Step 2: Launch browser --> PolyMorphism Concept
		
		if(BROWSER.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3: Login Application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4: Navigate to Contact Module
		HomePage hp=new HomePage(driver);
		hp.clickOnContactLnk();
		
	}
	
	@Test(dataProvider="getData")
	public void readData(String LASTNAME)
	{	
		
		
		//Step 5: Click on create contact look up img
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Step 6: create new contact with mandatory fields
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//Step 7: Validate
		ContactInfoPage cip=new ContactInfoPage(driver);
		String contactHeader=cip.getContactHeaderText();
		
		if(contactHeader.contains(LASTNAME))
		{
			System.out.println(contactHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
	}
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		return eUtil.readMultipleContactDataFromExcelSheet("CreateMultipleContact");
	}
	
	@AfterMethod
	public void postcondition() throws Throwable
	{
		//Step 8: Logout
		HomePage hp=new HomePage(driver);
		hp.logoutOfApp(driver);
		
		//Step 9: Close Browser
		driver.quit();
	}

}
