package com.qa.raybiztech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.raybiztech.utils.ElementUtil;

/**
 * This class contains methods and locators for the Dynamic365 Page.
 * Dependencies: WebDriver, ElementUtil
 * Preconditions: Dynamic365 Page.
 * Expected behavior: User should be able to navigate to different pages by clicking links and buttons.
 * Test cases: 
 * - TC001_
 */
public class Dynamic365Page {
	
	// 1. Private member variables
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//// 1.1 Private By locators for web elements on the Dynamic365 page
	private By providingheader = By.xpath("//h2[contains(text(),'Providing Microsoft Dynamics 365 Development, Implementation & Technology Consulting to Leading Enterprises')]");
	private By dynamix365 = By.xpath("//a[text()='Dynamics 365']");
	private By microsoftdynamicsAX = By.xpath("//a[text()='Microsoft Dynamics AX']");
	private By microsoftdynamicsnav = By.xpath("//a[text()='Microsoft Dynamics NAV']");
	
	

	// 2. Page Class Constructor
	
	/**
	 * Constructor for the Dynamic365 page class.
	 * @param driver - WebDriver object passed from the test class.
	 */
	public Dynamic365Page(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	

}
