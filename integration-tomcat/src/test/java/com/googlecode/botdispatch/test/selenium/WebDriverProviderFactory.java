package com.googlecode.botdispatch.test.selenium;

import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
