package com.googlecode.botdispatch.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestScoped;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;
import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumGuiceberryEnv extends GuiceBerryModule {

    @Provides
    @TestScoped
    Selenium getSelenium() {
        FirefoxDriver driver = new FirefoxDriver();//new HtmlUnitDriver();//
        //driver.setJavascriptEnabled(true);
        String baseUrl = "http://" + System.getProperty("endpoint");
//        System.out.println(">>>>>>>>>>>>b>>>>>>>>>>>>>Selenium baseURL:" + baseUrl);
        return new WebDriverBackedSeleniumExt(driver, baseUrl);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(SeleniumTestWrapper.class);
    }
}
