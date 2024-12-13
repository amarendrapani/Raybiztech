package com.qa.raybiztech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.qa.raybiztech.utils.Constants;
import com.qa.raybiztech.utils.ElementUtil;


/**
 * This class contains methods and locators for the Artificial Intelligence Page.
 * Dependencies: WebDriver, ElementUtil
 * Preconditions: Artificial Intelligence Page.
 * Expected behavior: User should be able to navigate to different pages by clicking links and buttons.
 * Test cases: 
 * - TC001_VerifyArtificialIntelligencePageTitle
 * - TC002_VerifyArtificialIntelligencePageHeader
 */
public class ArtificialIntelligencePage {
	
	// 1. Private member variables
	private WebDriver driver;
	private ElementUtil eleUtil;
	private ExtentTest test;
	
	// 1.1 Private By locators for web elements on the Artificial Intelligence Page
	private By technologypeopleprocessheader = By.xpath("//div[contains(.,'Technology + People + Process')]");
	private By enterpriseresourceplanninglink = By.xpath("//a[text()='Enterprise Resource Planning']");
	private By enterpriseportalcontentmanagementlink = By.xpath("//a[text()='Enterprise Portal & Content Management']");
	private By ecommercesociallink = By.xpath("//a[text()='E-Commerce & Social']");
	private By customerrelationshipmanagementlink = By.xpath("//a[text()='Customer Relationship Management']");
	
	
	// 2. Page Class Constructor
	
		/**
		 * Constructor for the Artificial Intelligence Page
		 * @param driver - WebDriver object passed from the test class.
		 */
	public ArtificialIntelligencePage(WebDriver driver) {
		this.driver = driver;
		this.test = test;
		eleUtil = new ElementUtil(driver);
		
	}
	//3.public page actions or methods
	
	/**
     * Get the page title of the Artificial Intelligence page
     * 
     * @return The page title as a String
     */
	public String getArtificialIntelligencePageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.ArtificialIntelligence_Page_Title, Constants.Default_Time_Out);
	}
	
	/**
     * Check if the Technology + People + Process header is displayed on the page
     * 
     * @return True if the header is displayed, false otherwise
     */
	public boolean isAipheaderExist(){
		return eleUtil.doIsDisplayed(technologypeopleprocessheader);
		
	}
	
	public  void validateAlllinks() {
		//return eleUtil.doGetPageTitleIs(Constants.ArtificialIntelligence_Page_Title, Constants.Default_Time_Out);
		eleUtil.validateAllLinkInAPage(driver,test);
		
	}
	
	
	
	

}
