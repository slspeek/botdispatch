package fspotcloud.botdispatch.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestScoped;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FSCGuiceberryEnv extends GuiceBerryModule {

	@Provides
	@TestScoped
	Selenium getSelenium() {
		HtmlUnitDriver driver = new HtmlUnitDriver();//new FirefoxDriver();
                driver.setJavascriptEnabled(true);
		return new WebDriverBackedSelenium(driver, "http://localhost:8080");
	}

	@Override
	protected void configure() {
		super.configure();
		bind(TestWrapper.class).to(SeleniumTestWrapper.class);
	}
}
