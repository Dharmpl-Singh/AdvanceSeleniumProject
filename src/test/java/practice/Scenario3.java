package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario3 {

	public static void main(String[] args) {
		//Step 1: Launch Browser
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		//Step 2: Login to the Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to the Organization link
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//step 4: Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 5: Create Organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("ABCD Company");
		
		//Step 6: Select "Chemicals" in the industry drop down
		WebElement industry=driver.findElement(By.name("industry"));
		Select s=new Select(industry);
		s.selectByValue("Chemicals");
		
		//Step 7: Save
		driver.findElement(By.name("button")).click();
		
		//step 8: Validate
		String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains("ABCD Company"))
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
		

	}

}
