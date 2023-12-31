package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	//Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrgLookUp;
	
	//Initialization
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateOrgLookUp() {
		return createOrgLookUp;
	}
	
	//Business Library
	/**
	 * This method will click on create organization lookup icon
	 */
	public void clickOnCreateOrgLookUpImg()
	{
		createOrgLookUp.click();
	}

}
