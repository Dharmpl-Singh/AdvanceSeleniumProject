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
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrgWithIndtryAndTypeTest {

	@Test
	public void createOrgWithIndAndTyp() throws IOException, InterruptedException {
	
		//Created object for all the utility
		ExcelFileUtility eUtil=new ExcelFileUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		WebDriver driver=null;

		// Step 1: Read all the required data
		//common data
		
		String URL=pUtil.readDataFromPropertyFile("url");
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		//Test Data
		String ORGNAME=eUtil.readDataFromExcelSheet("Organizations",7,2)+jUtil.getRandomNumber();
		String INDUSTRYNAME=eUtil.readDataFromExcelSheet("Organizations",7,3);
		String TYPE=eUtil.readDataFromExcelSheet("Organizations", 7, 4);
		
		
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
		
		//Step 5: Click on create organization look up image
		OrganizationsPage orgPg=new OrganizationsPage(driver);
		orgPg.clickOnCreateOrgLookUpImg();
		
		//Step 6: create organization with industry
		CreateNewOrgPage createOrg=new CreateNewOrgPage(driver);
		createOrg.createNewOrg(ORGNAME, INDUSTRYNAME, TYPE);
		
		//Step 7: Validate
		OrgInfoPage orgInfo=new OrgInfoPage(driver);
		String orgHeader=orgInfo.getOrgHeaderText();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println(orgHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 8: Logout
		hp.logoutOfApp(driver);
		//Step 9: close browser
		driver.quit();	

	}

}
