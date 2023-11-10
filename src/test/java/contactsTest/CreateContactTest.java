package contactsTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericUtilities.BaseClass;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

@Listeners(genericUtilities.ListenersImplementation.class)
public class CreateContactTest extends BaseClass{

	@Test
	 public void createContact() throws IOException, InterruptedException{
		
		
		//Step 1: Reading Test Data
		String LASTNAME=eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		
		//Step 2: Navigate to Contact Module
		HomePage hp=new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 3: Click on create contact look up Image
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Assert.fail();
		
		//Step 4: create new contact with mandatory fields
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//Step 5: Validate
		ContactInfoPage cip=new ContactInfoPage(driver);
		String contactHeader=cip.getContactHeaderText();
		
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		
	}

}
