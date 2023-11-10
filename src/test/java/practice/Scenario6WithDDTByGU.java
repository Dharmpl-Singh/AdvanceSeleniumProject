package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtilities.BrowserUtility;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 1.Launch Browser and Login
 * 2.Navigate to product and create product with mandatory fields
 * 3.Click on vendors loop up image
 * 4.Choose the vendors
 * 5.Save and validate
 * 6.Logout
 * @author Dharmpal
 */
public class Scenario6WithDDTByGU {

	public static void main(String[] args) throws IOException {
		//Create object for all the utility
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		BrowserUtility bUtil=new BrowserUtility();
		WebDriver driver=null;
		
		//Step 1: Read Data from external source
		
		//Common Data
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		
		//Test-Data
		String PRODNAME=eUtil.readDataFromExcelSheet("Products", 1, 2)+jUtil.getRandomNumber();
		String VENDNAME=eUtil.readDataFromExcelSheet("Vendors", 1, 2)+jUtil.getRandomNumber();
		
		//Step 2: Launch Browser and enter url
		
		driver=bUtil.launchBrowser(driver, BROWSER);
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3: Login the Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4: Navigate to more
		WebElement moreModule=driver.findElement(By.linkText("More"));
		wUtil.mouseHoverAction(driver, moreModule);
		
		//Step 5: click on Vendors
		driver.findElement(By.name("Vendors")).click();
		
		//Step 6: Click on create vendor lookup img
		driver.findElement(By.cssSelector("img[alt='Create Vendor...']")).click();
		
		//Step 7: Update mandatory fields and save 
		driver.findElement(By.name("vendorname")).sendKeys(VENDNAME);
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		
		//Step 8: Validation of Vendor
		String venHeader=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(venHeader.contains(VENDNAME))
		{
			System.out.println(venHeader);
			System.out.println("Vendor Successfully Created");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 9: Navigate to Products
		driver.findElement(By.linkText("Products")).click();
		
		//Step 10: click on create product lookup img
		driver.findElement(By.cssSelector("img[title='Create Product...']")).click();
		
		//Step 11: Update mandatory fields 
		driver.findElement(By.name("productname")).sendKeys(PRODNAME);
		
		//Step 12: click on vendor lookup img
		driver.findElement(By.xpath("//img[@alt='Select']")).click();
		
		//Step 13: Switch to Vendor window
		wUtil.switchToWindow(driver, "Vendors");
		System.out.println("Switched to Vendor Window");
		
		//Step 14: Search and Select Vendor 
		driver.findElement(By.id("search_txt")).sendKeys(VENDNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+VENDNAME+"']")).click();
		
		//Step 15: Switch to Product Window
		wUtil.switchToWindow(driver, "Products");
		System.out.println("Switched to Product Window");
		
		//Step 16: Save the Product
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		
		//Step 17: Validate Product
		String prodHeader=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(prodHeader.contains(PRODNAME))
		{
			System.out.println(prodHeader);
			System.out.println("Product Successfully Created");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 18:Logout 
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, ele);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logged out Successfully");

	}

}
