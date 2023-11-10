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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario2WithDDT {

	public static void main(String[] args) throws Throwable{
		// Step 1: Read all the required data
		//common data
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p=new Properties();
		p.load(fisp);
		String URL=p.getProperty("url");
		String BROWSER=p.getProperty("browser");
		String USERNAME=p.getProperty("username");
		String PASSWORD=p.getProperty("password");
		//Test Data
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String ORGNAME=wb.getSheet("Organizations").getRow(1).getCell(2).getStringCellValue();
		
		//Step 2: launch browser
		WebDriver driver=null;
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Step 3: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4: Navigate to Organization Link
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//step 5: click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 6: Create organization with Mandatory Field 
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 6: Save
		driver.findElement(By.name("button")).click();
		
		//Validate
		String organizationHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationHeader.contains(ORGNAME))
		{
			System.out.println(organizationHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		Thread.sleep(2000);
		
		//Step 7: Logout
		Actions a=new Actions(driver);
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		

	}

}
