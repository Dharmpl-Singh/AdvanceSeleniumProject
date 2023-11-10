package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {
	
	//Declaration
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement orgNameLookUpImg;
	
	@FindBy(id = "search_txt")
	private WebElement orgSearchBox;
	
	@FindBy(name = "search")
	private WebElement orgSearchBtn;
	
	//Initialization
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getLastName() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getOrgNameLookUpImg() {
		return orgNameLookUpImg;
	}

	public WebElement getSearchBox() {
		return orgSearchBox;
	}

	public WebElement getSearchBtn() {
		return orgSearchBtn;
	}
	
	//Business Library
	
	/**
	 * This method will create new contact with mandatory field
	 * @param LASTNAME
	 */
	public void createNewContact(String LASTNAME)
	{
		lastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create new contact with organization name
	 * @param driver
	 * @param ORGNAME
	 * @param LASTNAME
	 */
	public void createNewContact(WebDriver driver,String ORGNAME,String LASTNAME)
	{
		lastNameEdt.sendKeys(LASTNAME);
		orgNameLookUpImg.click();
		switchToWindow(driver, "Accounts");
		orgSearchBox.sendKeys(ORGNAME);
		orgSearchBtn.click();
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
		switchToWindow(driver, "Contacts");
		saveBtn.click();
		
	}

}
