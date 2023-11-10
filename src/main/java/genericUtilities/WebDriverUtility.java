package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all generic methods related to WebDriver class
 * @author Dharmpal
 */
public class WebDriverUtility {
	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window 
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait for 10 seconds for the web page to get loaded
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait for 10 seconds for the element to be visible
	 * @param driver
	 * @param element
	 */
	
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for 10 seconds for the element to be clikable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClikable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle drop-down by index
	 * @param element
	 * @param index
	 */
	public void handleDropdown(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);	
	}
	
	/**
	 * This method will handle drop-down by value
	 * @param element
	 * @param value
	 */
	public void handleDropdown(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);	
	}
	/**
	 * This method will handle drop-down by visible-text
	 * @param element
	 * @param value
	 */
	public void handleDropdown(String text,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);	
	}
	
	/**
	 * This method will perform mouse-hover action on web-element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform double-click action on web-element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform right-click action on web-element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop action on web-element
	 * @param driver
	 * @param element1
	 * @param element2
	 */
	public void dragAndDropAction(WebDriver driver,WebElement srcElement,WebElement targetElement)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(srcElement, targetElement).perform();
	}
	
	/**
	 * This method will perform click and hold action on web-element
	 * @param driver
	 * @param element
	 */
	public void clickAndHoldAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.clickAndHold(element).perform();
	}
	
	/**
	 * This method will scroll up by 500 units
	 * @param driver
	 * @param element
	 */
	public void scollUpAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500);","");
	}
	
	/**
	 * This method will scroll down by 500 units
	 * @param driver
	 * @param element
	 */
	public void scollDownAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500);","");
	}
	/**
	 * This method will scroll right by 500 units
	 * @param driver
	 */
	public void scollRightAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(500,0);","");
	}
	/**
	 * This method will scroll left by 500 units
	 * @param driver
	 */
	public void scollLeftAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(-500,0);","");
	}
	
	/**
	 * This method will accept alert pop-up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method will cancel alert pop-up
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method will capture the alert text and return to caller 
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		String text=driver.switchTo().alert().getText();
		return text;
	}
	
	/**
	 * This method will switch to Frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method will switch to Frame based on name or Id
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will switch to Frame based on WebElement
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch to window based on partial window title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWindowTitle)
	{
		// Step 1: Capture all the Window IDs
		Set<String> allWindowIds = driver.getWindowHandles();
		
		//Step 2: Navigate through each Window ID
		for(String windowID:allWindowIds)
		{
			// Step 3: Switch to each Window and Capture the title 
			String actTitle = driver.switchTo().window(windowID).getTitle();
			
			//Step 4: Compare the actual title with expected partial window title
			if(actTitle.contains(partialWindowTitle))
			{
				break;
			}
		}
	}
	
	/**
	 * This method will take screen shot and store in required folder
	 * @param driver
	 * @param scrrenShotName
	 * @return
	 * @throws IOException
	 */
	public String captureScreenShot(WebDriver driver, String scrrenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\Screenshot\\"+scrrenShotName+".png");
		Files.copy(src, dest);//correct
		//FileUtils.copyFile(src, dest); --> this also correct
		
		return dest.getAbsolutePath(); //complete path of screenshot --> extent report
	}
	
	
	
	
	
	

}
