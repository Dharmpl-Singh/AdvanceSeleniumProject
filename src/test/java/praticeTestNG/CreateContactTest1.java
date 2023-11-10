package praticeTestNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateContactTest1 extends BaseClass{

	@Test(groups = "SmokeSuite")
	public void createContactTest() throws IOException, InterruptedException
	{
		
		//Step 1: Test Data
		String LASTNAME=eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		//Step 2: Navigate to Contact Module
		HomePage hp=new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 3: Click on create contact look up img
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Step 4: create new contact with mandatory fields
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//Step 5: Validate
		ContactInfoPage cip=new ContactInfoPage(driver);
		String contactHeader=cip.getContactHeaderText();
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);

	}

}
