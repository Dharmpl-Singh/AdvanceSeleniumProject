package practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5 {

	public static void main(String[] args) {
		//Step 1: Launch browser
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		//Step 2: Login Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//Step 4 Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5: Create contact with Mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("LastName");
		
		//Step 6: Select the Organization from organization look up image
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String>childWindow=driver.getWindowHandles();
		for(String child:childWindow)
		{
			if(!child.equals(parentWindow)) {
			driver.switchTo().window(child);
			//driver.findElement(By.id("1")).click();
			driver.findElement(By.xpath("//a[contains(text(),'ABCD Company')]")).click();
			}
		}
		
		driver.switchTo().window(parentWindow);
		
		//Step 7: Save
		driver.findElement(By.name("button")).click();
		
		//Step 8: Verify
		String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains("LastName"))
		{
			System.out.println(orgHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 9: Logout
		Actions a=new Actions(driver);
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logged out Successfully");
		driver.quit();
	}

}
