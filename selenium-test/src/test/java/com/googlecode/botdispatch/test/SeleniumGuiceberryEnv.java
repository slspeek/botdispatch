package com.googlecode.botdispatch.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestScoped;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumGuiceberryEnv extends GuiceBerryModule {

    @Provides
    @TestScoped
    Selenium getSelenium() {
        FirefoxDriver driver = new FirefoxDriver();//new HtmlUnitDriver();//
        //driver.setJavascriptEnabled(true);
        return new WebDriverBackedSelenium(driver, "http://" + System.getProperty("endpoint"));
    }

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(SeleniumTestWrapper.class);
    }
}
