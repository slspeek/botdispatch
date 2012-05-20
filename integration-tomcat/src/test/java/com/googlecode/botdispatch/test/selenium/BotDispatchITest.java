package com.googlecode.botdispatch.test.selenium;

import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;

public class BotDispatchITest extends SeleniumITestCase {

    public BotDispatchITest(Provider<WebDriver> provider, String baseURL) {
        super(provider, baseURL);
    }

    public BotDispatchITest() {
    }

    public void runTest() throws Exception {
        testSimpleAction();
    }

    public void testSimpleAction() throws Exception {
        selenium.open("/test?name=David");
        selenium.waitForPageToLoad("30000");
        sleepShort(4);
        selenium.open("/test?name=Joshua");
        assertTrue(selenium.isTextPresent("Hello to you, David"));
        selenium.waitForPageToLoad("30000");
        sleepShort(4);
        selenium.open("/test?second=gNu");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("Hello to you, Joshua"));
        sleepShort(4);
        selenium.open("/test");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("GNU"));
    }
}
