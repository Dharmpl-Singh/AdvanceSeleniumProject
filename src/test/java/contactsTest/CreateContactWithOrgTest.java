package contactsTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationsPage;

@Listeners(genericUtilities.ListenersImplementation.class)
public class CreateContactWithOrgTest extends BaseClass{

	@Test
	public void createContactWithOrg() throws IOException, InterruptedException {
		
		
		//Step 1: Read Test Data
		
		String ORGNAME=eUtil.readDataFromExcelSheet("Organizations", 1, 2)+jUtil.getRandomNumber();
		String LASTNAME=eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
		

		//Step 2: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 3: click on create organization lookup img
		OrganizationsPage orgp=new OrganizationsPage(driver);
		orgp.clickOnCreateOrgLookUpImg();
		
		//Step 4: Create New Organization with mandatory fields 
		CreateNewOrgPage cNewOrg=new CreateNewOrgPage(driver);
		cNewOrg.createNewOrg(ORGNAME);
		
		//Step 5: Validate
		OrgInfoPage orgInfo=new OrgInfoPage(driver);
		String orgHeader=orgInfo.getOrgHeaderText();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(orgHeader+"Organization Created");
		
		//Step 6: Navigate to contacts module
		hp.clickOnContactLnk();
		
		//Step 7: click on create contact lookup Image
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Step 8: create contact with OrgName
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(driver, ORGNAME, LASTNAME);
		
		//Step 9: Validate
		ContactInfoPage ciPage=new ContactInfoPage(driver);
		String contactHeader = ciPage.getContactHeaderText();
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		
	}

}
