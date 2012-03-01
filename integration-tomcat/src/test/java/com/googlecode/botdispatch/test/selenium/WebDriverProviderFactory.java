package com.googlecode.botdispatch.test.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.Provider;

public class WebDriverProviderFactory {

	public Provider<WebDriver> firefoxProvider() {
		return new Provider<WebDriver>() {
			@Override
			public WebDriver get() {
				return new FirefoxDriver();
			}

		};
	}

	public Provider<WebDriver> chromeProvider() {
		return new Provider<WebDriver>() {
			@Override
			public WebDriver get() {
				return new ChromeDriver();
			}

		};
	}

}
