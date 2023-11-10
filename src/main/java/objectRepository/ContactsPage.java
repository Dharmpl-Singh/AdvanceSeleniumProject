package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	//Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContLookUp;
	
	//Initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getCreateContLookUp() {
		return createContLookUp;
	}
	
	//Business Library
	/**
	 * This method will click on create contact look up
	 */
	public void clickOnCreateContactLookUpIcon()
	{
		createContLookUp.click();
	}

}
