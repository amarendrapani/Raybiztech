package com.qa.raybiztech.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.qa.raybiztech.factory.DriverFactory;
import com.qa.raybiztech.listeners.ExtentReportListener;


/**
 * This class contains utility methods for interacting with web elements using Selenium WebDriver.
 */

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtils jsUtil;
	public ExtentReportListener extent;
	public ExtentTest test ;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtils(driver);
	}

	/**
	 * Returns a By object based on the given locator type and value.
	 * 
	 * @param locatorType the type of locator (id, name, classname, etc.)
	 * @param locatorValue the value of the locator
	 * @return the By object for the specified locator
	 */
	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			break;
		}

		return locator;
	}

	/**
	 * Returns a WebElement for the specified locator.
	 * 
	 * @param locator the locator for the desired element
	 * @return the WebElement for the specified locator
	 */
	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}
	
	/**
	 * Clicks on the element for the specified locator.
	 * 
	 * @param locator the locator for the element to click on
	 */
	public void doClick(By locator) {
		System.out.println("click on : " + locator);
		getElement(locator).click();

	}

	/**
	 * Clicks on the element specified by the given locator type and value.
	 * 
	 * @param locatorType the type of locator (id, name, classname, etc.)
	 * @param locatorValue the value of the locator
	 */
	public void doClick(String locatorType, String locatorValue) {
		getElement(getBy(locatorType, locatorValue)).click();
	}
	

	/**
	 * Enters the given text into the element specified by the given locator.
	 * 
	 * @param locator the locator for the element to enter text into
	 * @param value the text to enter into the element
	 */
	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	/**
	 * Enter the specified value into the web element identified by the given locator.
	 * 
	 *@param locatorType the type of locator used to identify the web element
	 *@param locatorValue the value of the locator used to identify the web element
	 *@param value the value to be typed into the web element
	 *
	 */
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public void printElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
		}
	}

	/**
	 * This method will return the list of element's text
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	/**
	 * This method will return the list of element's attribute value
	 * 
	 * @param locator
	 * @param attrName
	 * @return
	 */
	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrVal = e.getAttribute(attrName);
			eleAttrList.add(attrVal);
		}
		return eleAttrList;
	}

	/**
	 * This method will Click on any link with providing the text only
	 * 
	 * @param locator
	 * @param linkText
	 * 
	 */
	public void clickOnLink(By locator, String linkText) {
		List<WebElement> langList = getElements(locator);
		System.out.println(langList.size());
		for (WebElement e : langList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(linkText)) {
				e.click();
				break;
			}
		}
	}

	/******************************* Drop Down Utils ***************************/
	 /**
     * Selects an option from a dropdown by its index
     *
     * @param locator the locator of the dropdown element
     * @param index the index of the option to select
     */
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	

	/**
     * Selects an option from a dropdown by its visible text
     *
     * @param locator the locator of the dropdown element
     * @param visibleText the visible text of the option to select
     */
	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
		
	}

	
	/**
     * Selects an option from a dropdown by its value attribute
     *
     * @param locator the locator of the dropdown element
     * @param value the value attribute of the option to select
     */
	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	 /**
     * Gets the number of options in a dropdown
     *
     * @param locator the locator of the dropdown element
     * @return the number of options in the dropdown
     */
	public int getDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	/**
     * Selects an option from a dropdown by its visible text
     *
     * @param locator the locator of the dropdown element
     * @param value the visible text of the option to select
     */
	public void selectValueFromDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/**
     * Gets a list of all options in a dropdown
     *
     * @param locator the locator of the dropdown element
     * @return a list of all options in the dropdown
     */
	public List<String> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}

	/******************* Actions methods **********************/
	
	/**
	 * Send the data to given element by Actions class. 
	 * 
	 * @param value
	 * @param locator
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	/**
	 * Clicks in the middle of the given element. Equivalent to:
	 * Actions.moveToElement(onElement).click()
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/************************* Wait Utils **************************/

	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * default polling time = 500 ms
	 * @param locator
	 * @param timeOut
	 * @return
	 * @author Amarendra
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * default polling time = customized time
	 * @param locator
	 * @param timeOut
	 * @return
	 * @author Amarendra
	 */
	public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, 
					Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 * @author Amarendra
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
//============================non web elements: title, url, alert================================================================

	/**
	 * Wait for the page title to contain a given string.
	 * 
	 * @param titleVal The string to search for within the page title.
	 * @param timeOut  The maximum amount of time to wait for the page title to contain the given string.
	 * @return true if the page title contains the given string within the specified time limit, false otherwise.
	 */
	public boolean waitForPageTitle(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleVal));
	}

	/**
	 * Wait for the page title to exactly match a given string.
	 * 
	 * @param actTitle The string to match exactly with the page title.
	 * @param timeOut  The maximum amount of time to wait for the page title to match the given string.
	 * @return true if the page title exactly matches the given string within the specified time limit, false otherwise.
	 */
	public boolean waitForPageActTitle(String actTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(actTitle));
	}

	/**
	 * Get the page title if it contains a given string within a specified time limit.
	 * 
	 * @param titleVal The string to search for within the page title.
	 * @param timeOut  The maximum amount of time to wait for the page title to contain the given string.
	 * @return The page title if it contains the given string within the specified time limit, null otherwise.
	 */
	public String doGetPageTitleContains(String titleVal, int timeOut) {
		if (waitForPageTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

	/**
	 * Get the page title if it exactly matches a given string within a specified time limit.
	 * 
	 * @param titleVal The string to match exactly with the page title.
	 * @param timeOut  The maximum amount of time to wait for the page title to match the given string.
	 * @return The page title if it exactly matches the given string within the specified time limit, null otherwise.
	 */
	public String doGetPageTitleIs(String titleVal, int timeOut) {
		if (waitForPageActTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

	
	/**
	 * Wait for the URL to contain a given string within a specified time limit.
	 * 
	 * @param urlFraction The string to search for within the page URL.
	 * @param timeOut     The maximum amount of time to wait for the URL to contain the given string.
	 * @return The page URL if it contains the given string within the specified time limit, null otherwise.
	 */
	public String waitForUrlContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			return null;

		}
		return null;
	}

	/**
	 * Waits for the current URL to be the given URL within the specified time limit.
	 * 
	 * @param url The URL to wait for.
	 * @param timeOut The time limit to wait for the URL in seconds.
	 * @return The current URL if the given URL is reached within the time limit, null otherwise.
	 */
	public String waitForUrlToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(url))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			return null;

		}
		return null;
	}

	/**
	 * Waits for an alert to be present within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the alert in seconds.
	 * @return The alert if it is present within the time limit.
	 */
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Gets the text of the current alert within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the alert in seconds.
	 * @return The text of the current alert if it is present within the time limit.
	 */
	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	/**
	 * Accepts the current alert within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the alert in seconds.
	 */
	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	/**
	 * Dismisses the current alert within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the alert in seconds.
	 */
	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	/**
	 * Waits for a frame with the specified index to be available and switches to it within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the frame in seconds.
	 * @param frameIndex The index of the frame to wait for.
	 * @return The WebDriver instance with the switched frame if it is available within the time limit.
	 */
	public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	/**
	 * Waits for a frame with the specified locator to be available and switches to it within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the frame in seconds.
	 * @param frameLocator The locator of the frame to wait for.
	 * @return The WebDriver instance with the switched frame if it is available within the time limit.
	 */
	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/**
	 * Waits for a frame with the specified element to be available and switches to it within the specified time limit.
	 * 
	 * @param timeOut The time limit to wait for the frame in seconds.
	 * @param frameElement The element of the frame to wait for.
	 * @return The WebDriver instance with the switched frame if it is available within the time limit.
	 */
	public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	/**
	 * Waits for an element to be present on the page using FluentWait.
	 * 
	 * @param locator the By locator for the element
	 * @param timeOut the maximum time to wait for the element, in seconds
	 * @param pollingTime the time to wait between polls, in seconds
	 * @return the WebElement once it is present on the page
	 */
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(locator + " is not found within the given time......");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
	}
	
	
	/**
	 * Waits for an element to be present on the page using WebDriverWait.
	 * 
	 * @param locator the By locator for the element
	 * @param timeOut the maximum time to wait for the element, in seconds
	 * @param pollingTime the time to wait between polls, in seconds
	 * @return the WebElement once it is present on the page
	 */
	public WebElement waitForElementPresenceWithWait(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(locator + " is not found within the given time......");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
		
		
	}
	
	//===============================WindowHandle=========================================================
	
	/**
	*
	*This method returns the handle of the current window.
	*@param driver The instance of WebDriver.
	*@return The string value of the window handle.
	*
	*/
    public static String getCurrentWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    /**
    *
    *This method returns a set of string values representing the handles of all windows opened by the driver.
    *@param driver The instance of WebDriver.
    *return The set of string values representing the handles of all windows.
    *
    */
    public static Set<String> getAllWindowHandles(WebDriver driver) {
        return driver.getWindowHandles();
    }
	
   /**
    *
    *This method switches the focus of the driver to the window with the specified window handle.
    *@param windowHandle The string value representing the handle of the window to switch to.
    */
	 public void switchToWindow(String windowHandle) {
	        driver.switchTo().window(windowHandle);
	    }
	 
	/**
	 *
	 *This method closes the currently focused window.
	 */
	 public void closeCurrentWindow() {
	        driver.close();
	    }
	 

	/**
	 *
	 *This method closes all windows except for the currently focused window.
	 */
	 public void closeAllWindowsExceptCurrent() {
	        String currentHandle = driver.getWindowHandle();
	        Set<String> handles = driver.getWindowHandles();
	        for (String handle : handles) {
	            if (!handle.equals(currentHandle)) {
	                driver.switchTo().window(handle);
	                driver.close();
	            }
	        }
	        driver.switchTo().window(currentHandle);
	    }
	 
	/**
	 *
	 *This method waits for a new window to be opened by the driver for a specified amount of time.
	 *@param timeout The integer value representing the maximum amount of time to wait for a new window.
	 */
	 public void waitForNewWindow(int timeout) {
	        int timeElapsed = 0;
	        while (timeElapsed < timeout) {
	            if (driver.getWindowHandles().size() > 1) {
	                break;
	            }
	            try {
	                TimeUnit.SECONDS.sleep(1);
	                timeElapsed++;
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	/**
	 *
	 *This method switches the focus of the driver to the window with the specified window title.
	 *@param driver The instance of WebDriver.
	 *@param windowTitle The string value representing the title of the window to switch to.
	 */
	    public static void switchToWindow(WebDriver driver, String windowTitle) {
	        Set<String> windowHandles = driver.getWindowHandles();
	        for (String handle : windowHandles) {
	            driver.switchTo().window(handle);
	            if (driver.getTitle().contains(windowTitle)) {
	                return;
	            }
	        }
	        throw new IllegalArgumentException("Window not found: " + windowTitle);
	    }
	    
	    /**
	    *
	    *This method switches the focus of the driver to the last window opened by the driver.
	    *@param driver The instance of WebDriver.
	    */
	    public static void switchToLastOpenedWindow(WebDriver driver) {
	        Set<String> windowHandles = driver.getWindowHandles();
	        String lastWindowHandle = "";
	        for (String handle : windowHandles) {
	            lastWindowHandle = handle;
	        }
	        driver.switchTo().window(lastWindowHandle);
	    }
	    
	   /**
	    *
	    *This method closes the currently focused window and switches the focus of the driver to the parent window.
	    *@param driver The instance of WebDriver.
	    */
	    public static void closeCurrentWindowAndSwitchToParent(WebDriver driver) {
	        String currentWindowHandle = driver.getWindowHandle();
	        Set<String> windowHandles = driver.getWindowHandles();
	        if (windowHandles.size() == 1) {
	            driver.close();
	            return;
	        }
	        for (String handle : windowHandles) {
	            if (!handle.equals(currentWindowHandle)) {
	                driver.switchTo().window(handle);
	                driver.close();
	                driver.switchTo().window(currentWindowHandle);
	                return;
	            }
	        }
	    }
	    
	   /**
	    *
	    *This method closes all the child Windows apart from the parent window
	    *@param driver The instance of WebDriver.
	    */
	    public static void closeAllChildWindows(WebDriver driver) {
	        String parentWindowHandle = driver.getWindowHandle();
	        Set<String> windowHandles = driver.getWindowHandles();
	        for (String handle : windowHandles) {
	            if (!handle.equals(parentWindowHandle)) {
	                driver.switchTo().window(handle);
	                driver.close();
	            }
	        }
	        driver.switchTo().window(parentWindowHandle);
	    }
	    
	    /**
	    *
	    *This method focus the driver to the window which you want by providing the index if you know
	    *@param driver The instance of WebDriver.
	    */
	    public static void switchToWindow(WebDriver driver, int windowIndex) {
	        Set<String> windowHandles = driver.getWindowHandles();
	        if (windowIndex >= 0 && windowIndex < windowHandles.size()) {
	            driver.switchTo().window(windowHandles.toArray()[windowIndex].toString());
	        } else {
	            throw new IllegalArgumentException("Invalid window index: " + windowIndex);
	        }
	    }

	    /**
	    *
	    *This method Switch the driver to the parent window by providing the title
	    *@param driver The instance of WebDriver.
	    */
	    public static void switchToParentWindow(WebDriver driver,String parentWindowTitle) {
	        Set<String> windowHandles = driver.getWindowHandles();
	        for (String handle : windowHandles) {
	            driver.switchTo().window(handle);
	            if (driver.getTitle().contains(parentWindowTitle)) {
	                break;
	            }
	        }
	    }
	   
	 
	// ===========================================Link validation========================================================================
			
	    public  void validateAllLinkInAPage (WebDriver driver , ExtentTest test) {
	    	List<WebElement> links = driver.findElements(By.tagName("a"));
			
			System.out.println("No of links are --- "+ links.size());
			List<String> urllist = new ArrayList<String>();
			
			
			for(WebElement e : links) {
				
				String url = e.getAttribute("href");
				urllist.add(url);
				//checkbrokenlink(url);
			} 
			
			long stTime = System.currentTimeMillis();
			urllist.parallelStream().forEach(e -> checkBrokenLinkAndImage(e,test));
			
			long endTime = System.currentTimeMillis();
			System.out.println("total time taken : "+ (endTime-stTime));
	    }
	    
	   
	    public  void checkBrokenLinkAndImage ( String linkurl, ExtentTest test) {
	    	
			
			try {
				URL url = new URL(linkurl);
				HttpURLConnection  httpurlconnection = (HttpURLConnection) url.openConnection();
				httpurlconnection.setConnectTimeout(5000);
				httpurlconnection.connect();
				
				if (httpurlconnection.getResponseCode() >=400) {
					//System.out.println(linkurl + "--->" + httpurlconnection.getResponseMessage() + "is a broken link"+ "-->" + httpurlconnection.getResponseCode());
					String message = String.format(linkurl + "--->" + httpurlconnection.getResponseMessage() + "is a broken link"+ "-->" + httpurlconnection.getResponseCode());
					test.log(Status.FAIL, message);
				}
				else {
					
					//System.out.println(linkurl + "--->" + httpurlconnection.getResponseMessage()+ "-->" + httpurlconnection.getResponseCode());
					String message = String.format(linkurl + "--->" + httpurlconnection.getResponseMessage()+ "-->" + httpurlconnection.getResponseCode());
					test.log(Status.PASS, message);
				}
				
			}catch (Exception e) {
				
			}
			
		}
	 
	 
	 
}
