package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario3WithDDT2ByGU {

public static void main(String[] args) throws Throwable{
	        
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
			String ORG=eUtil.readDataFromExcelSheet("Organizations",4,2);
			String INDUSTRYNAME=eUtil.readDataFromExcelSheet("Organizations",4,3);
			int ran=jUtil.getRandomNumber();
			String ORGNAME=ORG+ran;
			
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
			
			//Step 2: Login to the Application
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			//Step 3: Navigate to the Organization link
			driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
			
			//step 4: Click on create organization look up image
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			//Step 5: Create Organization with Mandatory fields
			driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
			
			//Step 6: Select "Chemicals" in the industry drop down
			WebElement industry=driver.findElement(By.name("industry"));
			Select s=new Select(industry);
			s.selectByValue(INDUSTRYNAME);
			
			//Step 7: Save
			driver.findElement(By.name("button")).click();
			
			//step 8: Validate
			String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(orgHeader.contains(ORGNAME))
			{
				System.out.println(orgHeader);
				System.out.println("PASS");
			}
			else
			{
				System.out.println("FAIL");
			}
			//Step 9: Logout
			
			WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			wUtil.mouseHoverAction(driver, ele);
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			System.out.println("Logout Successfully");

  }

}
