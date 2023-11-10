package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	//Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderInfo;
	
	//Initialization
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getContactInfo() {
		return contactHeaderInfo;
	}
	
	//Business Library
	/**
	 * This method will get the contact header info and return it to the caller
	 * @return
	 */
	public String getContactHeaderText()
	{
		String contactHeader=contactHeaderInfo.getText();
		return contactHeader;
	}

}
