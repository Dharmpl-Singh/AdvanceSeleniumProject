package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {//Rule 1
	
	//Declarations
	
	@FindBy(name="user_name")
	private WebElement userNameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Initialization
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	
	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Business Library -> Generic method using the web elements in current page
	
	/**
	 * This method will login to the application
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username,String password)
	{
		userNameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	

}
