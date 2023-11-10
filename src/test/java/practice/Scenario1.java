package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Scenario1 {

	public static void main(String[] args) throws Throwable {
		
		//Step1: Launch the browser
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		//Step 2: Login to Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to Contact Link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//Step 4: click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5: create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Singh");
		
		//Step 6: Save
		driver.findElement(By.name("button")).click();
		
		//Step 7: Validate
		String contactHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains("Singh"))
		{
			System.out.println(contactHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		// Step 8:Logout
		Actions a=new Actions(driver);
		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
	}

}
