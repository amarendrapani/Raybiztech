package com.qa.raybiztech.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.raybiztech.factory.DriverFactory;
import com.qa.raybiztech.pages.ArtificialIntelligencePage;
import com.qa.raybiztech.pages.ContactPage;
import com.qa.raybiztech.pages.HomePage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	
	//------------------
	HomePage home;
	ArtificialIntelligencePage aipPage;
	ContactPage contactPage;
	
	//-------------------
	SoftAssert softAssert;
	
	
	
	
	@BeforeTest
	public void setUp() {
		
	df = new DriverFactory();
	prop = df.init_prop();
	driver = df.init_driver(prop);
	home = new HomePage(driver);
	softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
	driver.quit();
	}
	
	

}
