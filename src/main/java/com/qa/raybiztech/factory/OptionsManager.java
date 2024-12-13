package com.qa.raybiztech.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;


public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	/**
     * Constructor for the OptionsManager class.
     *
     * @param prop Properties object containing configuration information.
     */
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	/**
     * Returns a ChromeOptions object based on the configuration properties.
     *
     * @return ChromeOptions object with the specified configuration options.
     */
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
	}
	
	/**
     * Returns a FirefoxOptions object based on the configuration properties.
     *
     * @return FirefoxOptions object with the specified configuration options.
     */
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return fo;
	}
	
	/**
     * Returns an EdgeOptions object based on the configuration properties.
     *
     * @return EdgeOptions object with the specified configuration options.
     */
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return eo;
	}
	
	
	

}
