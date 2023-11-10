package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario4 {

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
		
		//Step 3: Navigate to organization link
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//Step 4: click on organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 5: Create organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("ABCFFHI Company");
		
		//Step 6: Select "Energy" in the industry drop down
		WebElement industry=driver.findElement(By.name("industry"));
		
		//Step 7: Select "Customer" in the Type Drop down
		WebElement type=driver.findElement(By.name("accounttype"));
		Select s=new Select(industry);
		s.selectByValue("Energy");
		Select s1=new Select(type);
		s1.selectByValue("Customer");
		
		//Step 8: Save
		driver.findElement(By.name("button")).click();
		
		//step 9: Validate
		String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains("ABCFFHI Company"))
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

	}

}
