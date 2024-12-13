package com.qa.raybiztech.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class contains utility methods for executing JavaScript code in the browser using Selenium WebDriver.
 */
public class JavaScriptUtils {
	
	/**
     * Constructor for initializing the WebDriver instance to be used for executing JavaScript code.
     *
     * @param driver the WebDriver instance to use
     */
	WebDriver driver;

	public JavaScriptUtils(WebDriver driver) {
		this.driver = driver;
	}

	 /**
     * Highlights an element on the page by changing its background color to green and then back to its original color.
     * This is done by executing JavaScript code in the browser using the WebDriver instance.
     *
     * @param element the WebElement to highlight
     */
	public void flash(WebElement element) {
		 // get a JavascriptExecutor instance to execute JavaScript code in the browser
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// get the current background color of the element
		String bgcolor = element.getCssValue("backgroundColor");
		 // flash the element by changing its background color to green and then back to its original color several times
		for (int i = 0; i < 15; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}
	
	 /**
     * Changes the background color of an element to the specified color by executing JavaScript code in the browser.
     *
     * @param color   the color to change the background to, in CSS format (e.g. "rgb(255, 0, 0)")
     * @param element the WebElement to change the background color of
     */
	private void changeColor(String color, WebElement element) {
		 // get a JavascriptExecutor instance to execute JavaScript code in the browser
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// execute JavaScript code to change the background color of the element
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		} // do nothing
	}

	
	 /**
     * Gets the title of the current page by executing JavaScript code in the browser.
     *
     * @return the title of the current page
     */
	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}
	
	
	 /**
     * Refreshes the current page by executing JavaScript code in the browser.
     */
	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	/**
     * Generates an alert with the specified message by executing JavaScript code in the browser.
     *
     * @param message the message to display in the alert
     */
	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	 /**
     * Gets the text content of the entire page by executing JavaScript code in the browser.
     *
     * @return the text content of the entire page
     */
	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	/**
	 * Clicks the web element using JavascriptExecutor
	 * @param element the web element to click
	 */
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Sends keys to the web element identified by id using JavascriptExecutor
	 * @param id the id of the web element
	 * @param value the value to send to the web element
	 */
	public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//document.getElementById('input-email').value = 'Amarendra@gmail.com'
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	/**
	 * Scrolls the page down to a specified height.
	 * 
	 * @param height The height in pixels to scroll to.
	 */
	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Scrolls the page down from the top using JavascriptExecutor
	 */

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	

	/**
	 * Scrolls the web page up to the top using JavascriptExecutor
	 */
	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	/**
	 * Scrolls the page to bring an element into view.
	 * 
	 * @param element The WebElement to scroll to.
	 */
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Draws a red border around a WebElement.
	 * 
	 * @param element The WebElement to draw a border around.
	 */

	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

}
