package contactsTest;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import genericUtilities.BaseClass;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;


public class CreateMultipleContactsTest extends BaseClass{
			
	@Test(dataProvider="getData")
	public void readData(String LASTNAME)
	{	
		//Step 4: Navigate to Contact Module
		HomePage hp=new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 5: Click on create contact look up Image
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Step 6: create new contact with mandatory fields
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//Step 7: Validate
		ContactInfoPage cip=new ContactInfoPage(driver);
		String contactHeader=cip.getContactHeaderText();
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		
	}
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		return eUtil.readMultipleContactDataFromExcelSheet("CreateMultipleContact");
	}
	

}
