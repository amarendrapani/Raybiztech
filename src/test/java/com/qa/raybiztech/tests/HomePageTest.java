package com.qa.raybiztech.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.raybiztech.utils.Constants;

public class HomePageTest extends BaseTest{
	
	@Test
	public void rbtLogoValidation() throws InterruptedException {
		Assert.assertTrue(home.isRbtLogoExist());
	}
	
	@Test
	public void homePageTitleValidation() {
		String title = home.getHomePageTitle();
		System.out.println("Home Page Title : " + title);
		Assert.assertEquals(title, Constants.Home_Page_Title);			
	}

}
