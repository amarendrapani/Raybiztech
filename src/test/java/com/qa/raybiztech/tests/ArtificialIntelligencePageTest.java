package com.qa.raybiztech.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.raybiztech.utils.Constants;

public class ArtificialIntelligencePageTest extends BaseTest {
	@BeforeClass
	public void aipSetup(){
		aipPage  = home.goToArtificialIntelligencePage();
	}
	
	
	@Test
	public void ArtificialIntelligencePageTitleValidation() {
		String title = aipPage.getArtificialIntelligencePageTitle();
		System.out.println("ArtificialIntelligence Page Title : " + title);
		Assert.assertEquals(title, Constants.ArtificialIntelligence_Page_Title);		
	}
	
	@Test
	public void artificialIntelligenceHeader() {
		Assert.assertTrue(aipPage.isAipheaderExist());
	}
	
	//@Test
    //public void testValidateAllLinks() {
		//aipPage.validateAlllinks(driver,test);
		//aipPage.validateAlllinks();
    //}

}
