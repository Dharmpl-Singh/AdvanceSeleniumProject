package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility{
	
	//Declaration
	@FindBy(linkText = "Contacts")
	private WebElement contactsLnk;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	//Initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getContactsLnk() {
		return contactsLnk;
	}

	public WebElement getOrganizationsLnk() {
		return organizationsLnk;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	//Business Library
	/**
	 * This method will click on organization link
	 */
	public void clickOnOrgLnk()
	{
		organizationsLnk.click();
	}
	
	/**
	 * This method will click on contact link
	 */
	public void clickOnContactLnk()
	{
		contactsLnk.click();
	}
	
	/**
	 * This method will logout from the application
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void logoutOfApp(WebDriver driver) throws InterruptedException
	{
		mouseHoverAction(driver, adminImg);
		Thread.sleep(1000);
		signOutLnk.click();
	}
	

}
