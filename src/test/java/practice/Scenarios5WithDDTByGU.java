package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenarios5WithDDTByGU {

public static void main(String[] args) throws Throwable{
	    ExcelFileUtility eUtil=new ExcelFileUtility();
	    JavaUtility jUtil=new JavaUtility();
	    PropertyFileUtility pUtil=new PropertyFileUtility();
	    WebDriverUtility wUtil=new WebDriverUtility();
	    WebDriver driver=null;
		// Step 1: Read all the required data
		//common data
		
		String URL=pUtil.readDataFromPropertyFile("url");
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		//Test Data
	
		String LASTNAME=eUtil.readDataFromExcelSheet("Contacts",4,2)+jUtil.getRandomNumber();
		String ORGNAME=eUtil.readDataFromExcelSheet("Organizations", 4, 2)+jUtil.getRandomNumber();
		
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
		
		//Step 3: Login to the Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

	    //Step 4: Navigate to Organization Link
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//step 5: click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 6: Create organization with Mandatory Field 
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 7: Save
		driver.findElement(By.name("button")).click();
		
		//Step 8: Validate
		String organizationHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationHeader.contains(ORGNAME))
		{
			System.out.println(organizationHeader);
			System.out.println("Organization Created");
		}
		else
		{
			System.out.println("FAIL");
		}

		//Step 9: Navigate to Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//Step 10: Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 11: Create contact with Mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step 12: Select the Organization from organization look up image
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		wUtil.switchToWindow(driver, "Accounts");
		System.out.println("Switched to child window");
		
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
		
		wUtil.switchToWindow(driver, "Contacts");
		System.out.println("Switched to parent window");
		
		//Step 13: Save
		driver.findElement(By.name("button")).click();
		
		//Step 14: Validating contact
		String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains(LASTNAME))
		{
			System.out.println(orgHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 15: Logout
	
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, ele);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logged out Successfully");
		
	}

}
