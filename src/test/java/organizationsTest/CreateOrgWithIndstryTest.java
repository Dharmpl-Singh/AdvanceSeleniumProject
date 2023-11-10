package organizationsTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import genericUtilities.BaseClass;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrgWithIndstryTest extends BaseClass{
	
	@Test
    public void createOrgWithIndstry() throws IOException, InterruptedException {

	//Step 1: Read Test Data
	String ORGNAME=eUtil.readDataFromExcelSheet("Organizations",4,2)+jUtil.getRandomNumber();
	String INDUSTRYNAME=eUtil.readDataFromExcelSheet("Organizations",4,3);
	
	
	//Step 2: Navigate to organization module
	HomePage hp=new HomePage(driver);
	hp.clickOnOrgLnk();
	
	//Step 3: Click on create organization look up image
	OrganizationsPage orgPg=new OrganizationsPage(driver);
	orgPg.clickOnCreateOrgLookUpImg();
	
	//Step 4: create organization with industry
	CreateNewOrgPage createOrg=new CreateNewOrgPage(driver);
	createOrg.createNewOrg(ORGNAME, INDUSTRYNAME);
	
	//Step 5: Validate
	OrgInfoPage orgInfo=new OrgInfoPage(driver);
	String orgHeader=orgInfo.getOrgHeaderText();
	Assert.assertTrue(orgHeader.contains(ORGNAME));
	System.out.println(orgHeader);
	
}	

}
