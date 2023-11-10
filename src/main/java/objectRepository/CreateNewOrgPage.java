package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;


public class CreateNewOrgPage extends WebDriverUtility {
	
	//Declaration
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(name = "industry")
	private WebElement industrySel;
	
	@FindBy(name = "accounttype")
	private WebElement type;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialization
	public CreateNewOrgPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilization
	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getIndustrySel() {
		return industrySel;
	}

	public WebElement getType() {
		return type;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
		
	//Business Library
	
	/**
	 * This method will help to create new organization with mandatory fields
	 * @param ORGNAME
	 */
	public void createNewOrg(String ORGNAME)
	{
		orgName.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will help to create new organization with industry
	 * @param ORGNAME
	 * @param Value
	 */
	public void createNewOrg(String ORGNAME,String industryValue)
	{
		orgName.sendKeys(ORGNAME);
		handleDropdown(industrySel, industryValue);
		saveBtn.click();
	}
	
	/**
	 * This method will help to create new organization with industry and type.
	 * @param ORGNAME
	 * @param industryValue
	 * @param typeValue
	 */
	public void createNewOrg(String ORGNAME,String industryValue,String typeValue)
	{
		orgName.sendKeys(ORGNAME);
		handleDropdown(industrySel, industryValue);
		handleDropdown(type, typeValue);
		saveBtn.click();
	}

}