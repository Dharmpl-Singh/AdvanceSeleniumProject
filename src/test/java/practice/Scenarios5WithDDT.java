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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenarios5WithDDT {

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
				FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData1.xlsx");
				Workbook wb = WorkbookFactory.create(fis);
				String LASTNAME=wb.getSheet("Contacts").getRow(4).getCell(2).getStringCellValue();
				String ORGNAME=wb.getSheet("Contacts").getRow(4).getCell(3).getStringCellValue();
				
				
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
				
				//Step 3: Login to the Application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				//Step 4: Navigate to Contacts link
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				
				//Step 5: Click on Create contact look up image
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//Step 6: Create contact with Mandatory fields
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				
				//Step 7: Select the Organization from organization look up image
				driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
				String parentWindow = driver.getWindowHandle();
				Set<String>childWindow=driver.getWindowHandles();
				for(String child:childWindow)
				{
					if(!child.equals(parentWindow)) {
					driver.switchTo().window(child);
					//driver.findElement(By.id("1")).click();
					//driver.findElement(By.xpath("//a[contains(text(),'ABCD Company')]")).click();
					}
				}
				
				driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(ORGNAME)).click();
				
				driver.switchTo().window(parentWindow);
				
				//Step 8: Save
				driver.findElement(By.name("button")).click();
				
				//Step 9: Verify
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
				
				//Step 10: Logout
				Actions a=new Actions(driver);
				WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				a.moveToElement(ele).perform();
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				System.out.println("Logged out Successfully");
				driver.quit();
	}

}
