package com.qa.raybiztech.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.raybiztech.utils.Constants;
import com.qa.raybiztech.utils.ElementUtil;


/**
 * This class contains methods and locators for the Home page.
 * Dependencies: WebDriver, ElementUtil
 * Preconditions: User is on the Home page.
 * Expected behavior: User should be able to navigate to different pages by clicking links and buttons.
 * Test cases: 
 * - TC001_VerifyHomePageTitle
 * - TC002_VerifyHomePageUrl
 * - TC003_VerifyRbtLogoExist
 * - TC004_GoToArtificialIntelligencePage
 * - TC005_GoToContactPage
 */
public class HomePage {
	
	// 1. Private member variables
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1.1 Private By locators for web elements on the Home page
	private By rbtlogo = By.xpath("//img[position()=1]");
	private By rbt13yearscelebrationlogo = By.xpath("//img[@alt='Ray Business Technologies 13 years celebration']");
	private By wearehiringlogo = By.xpath("//img[@class='we-are-hiring-img sm-img hidden-xs']");
	private By searchbutton = By.xpath("//input[@id='search_global']");
	private By mainmenuicon = By.xpath("//button[@id='js_main-menu-icon']");
	private By kenticoxperience13linkheader = By.xpath("//h1[@class='banner_heading2 hidden-xs wow fadeInLeft animated']");
	private By artificialintelligencelink = By.xpath("//a[text()='ARTIFICIAL INTELLIGENCE']/.");
	private By dynamics365link = By.xpath("//a[text()='Dynamics 365']");
	private By kenticoxperience13link = By.xpath("//a[text()='KENTICO XPERIENCE 13']");
	private By careerslink = By.xpath("//a[text()='CAREERS ']");
	private By contactlink = By.xpath("//a[normalize-space()='CONTACT']");
	
	
	// 2. Page Class Constructor
	
	/**
	 * Constructor for the Home page class.
	 * @param driver - WebDriver object passed from the test class.
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.public page actions or methods
	
	/**
	 * Method to get the title of the Home page.
	 * @return - String value of the page title.
	 */
	public String getHomePageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.Home_Page_Title, Constants.Default_Time_Out);
	}
	
	/**
	 * Method to get the current URL of the Home page.
	 * @return - String value of the current URL.
	 */
	public String getHomePageUrl() {
		return driver.getCurrentUrl();
	}
	
	/**
	 * Method to check if the Rbt logo is displayed on the Home page.
	 * @return - Boolean value of logo's existence.
	 */
	public boolean isRbtLogoExist()  {
		return eleUtil.doIsDisplayed(rbtlogo);
		
	}
	
	/**
	 * Method to navigate to the Artificial Intelligence page by clicking the link on the Home page.
	 * @return - Object of the ArtificialIntelligencePage class.
	 */
	public ArtificialIntelligencePage goToArtificialIntelligencePage() {
		eleUtil.doClick(artificialintelligencelink);
		return new ArtificialIntelligencePage(driver);
	}
	
	/**
	 * Method to navigate to the Contact page by clicking the link on the Home page.
	 * @return - Object of the ContactPage class.
	 */
	public ContactPage goToContactPage() {
		eleUtil.doClick(contactlink);
		return new ContactPage(driver);
	}
	
	
	
	
}
