package praticeTestNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrgTest {

	@Test
	public void createContactWithOrg() throws IOException, InterruptedException {

		//create object for all utility class
		ExcelFileUtility eUtil=new ExcelFileUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		WebDriver driver=null;
		
		// Step 1: Read all the required data
		//common data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		//Test Data
		
		String ORGNAME=eUtil.readDataFromExcelSheet("Organizations", 1, 2)+jUtil.getRandomNumber();
		String LASTNAME=eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		//Step 2: launch browser
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
			System.out.println("Invalid Browser Name");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3: Login Application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 5: click on create organization lookup img
		OrganizationsPage orgp=new OrganizationsPage(driver);
		orgp.clickOnCreateOrgLookUpImg();
		
		//Step 6: Create New Organization with mandatory fields 
		CreateNewOrgPage cNewOrg=new CreateNewOrgPage(driver);
		cNewOrg.createNewOrg(ORGNAME);
		
		//Step 7: Validate
		OrgInfoPage orgInfo=new OrgInfoPage(driver);
		String orgHeader=orgInfo.getOrgHeaderText();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println("Organization Created");
		}
		else
		{
			System.out.println("FAIL");
		}
		//Step 8: Navigate to contacts module
		hp.clickOnContactLnk();
		
		//Step 9: click on create contact lookup img
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Step 10: create contact with orgname
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(driver, ORGNAME, LASTNAME);
		
		//Step 11: Validate
		ContactInfoPage ciPage=new ContactInfoPage(driver);
		String contactHeader = ciPage.getContactHeaderText();
		if(contactHeader.contains(LASTNAME))
		{
			System.out.println(contactHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 12: Logout
		hp.logoutOfApp(driver);
		
		//Step 13: close browser
		driver.quit();
	}

}
