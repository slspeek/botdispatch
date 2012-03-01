package com.googlecode.botdispatch.test.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.google.inject.Provider;
import com.thoughtworks.selenium.Selenium;

public class SeleniumITestCase extends WebDriverITestCase {

	protected Selenium selenium;
	
	public SeleniumITestCase(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}
	
	public SeleniumITestCase() {
		super();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		selenium = new WebDriverBackedSelenium(driver, baseURL);
	}

	@Override
	protected void tearDown() throws Exception {
		selenium.stop();
		super.tearDown();
	}
	
}
