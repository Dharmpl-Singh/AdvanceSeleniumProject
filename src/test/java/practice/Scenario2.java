package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario2 {

	public static void main(String[] args) throws Throwable{
		//Step1: Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		//Step 2: Login to Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to Organization Link
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//step 4: click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 5: Create organization with Mandatory Field 
		driver.findElement(By.name("accountname")).sendKeys("ABC Company");
		
		//Step 6: Save
		driver.findElement(By.name("button")).click();
		
		//Validate
		String organizationHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationHeader.contains("ABC Company"))
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
