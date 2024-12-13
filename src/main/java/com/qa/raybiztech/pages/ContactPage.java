package com.qa.raybiztech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.raybiztech.utils.ElementUtil;

/**
 * This class contains methods and locators for the Contact Page.
 * Dependencies: WebDriver, ElementUtil
 * Preconditions: Contact Page.
 * Expected behavior: User should be able to navigate to different pages by clicking links and buttons.
 * Test cases: 
 * - TC001_VerifyTheRegisterResetFields
 */
public class ContactPage {
	
		// 1. Private member variables
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		// 1.1 Private By locators for web elements on the Contact page
		private By name = By.xpath("//input[@data-val-required='Please enter valid Name']");
		private By email = By.xpath("(//input[@data-val-required='Please enter valid Email'])[1]");
		private By phonnumber = By.xpath("(//input[@id='Phone'])[1]");
		private By country = By.xpath("//select[@id='Country']");
		private By message = By.xpath("(//input[@id='Message'])[1]");
		private By resetbutton = By.xpath("//input[@type='reset']");
		private By submitbutton = By.xpath("//input[@type='submit']");
		
		
		// 2. Page Class Constructor
		
		/**
		 * Constructor for the Contact page class.
		 * @param driver - WebDriver object passed from the test class.
		 */
		public ContactPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
			
		}
		
		//3.public page actions or methods
		
		/**
		 * This method is used to fill the Contact Us form with user data and either submit or reset the form
		 * @param name the name of the user filling the form
		 * @param email the email of the user filling the form
		 * @param phonnumber the phone number of the user filling the form
		 * @param country the country of the user filling the form
		 * @param message the message the user wants to send
		 * @param button either "submit" or "reset"
		 * @return true if the form was reset successfully or if the message field is empty after submission, false otherwise
		 */
		public boolean getInTouchRegisterResetFunction(String name,String email,String phonnumber,String country,String message,String button){
			
			eleUtil.doSendKeys(this.name, name);
			eleUtil.doSendKeys(this.email, email);
			eleUtil.doSendKeys(this.phonnumber, phonnumber);
			eleUtil.selectValueFromDropDown(this.country, country);
			eleUtil.doSendKeys(this.message, message);
		
			if (button.equalsIgnoreCase("submit")){
				eleUtil.doClick(submitbutton);
			}else {
				eleUtil.doClick(resetbutton);
			}
			
			if(eleUtil.doGetText(this.message)== "") {
				return true;
			}
			return false;	
		}

}
