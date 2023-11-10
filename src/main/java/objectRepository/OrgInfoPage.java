package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
	
	//Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orgHeaderInfo;
	
	//Initialization
	public OrgInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrgHeaderInfo() {
		return orgHeaderInfo;
	}
	
	//Business Library
	
	/**
	 * This method will help to get the organization header and return it to caller
	 * @return
	 */
	public String getOrgHeaderText()
	{
		return orgHeaderInfo.getText();
		
	}
	
	

}
